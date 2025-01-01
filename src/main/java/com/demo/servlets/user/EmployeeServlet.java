package com.demo.servlets.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Douutien;
import com.demo.entities.Nhanvien;
import com.demo.entities.Yeucau;
import com.demo.helpers.UploadHelper;
import com.demo.models.DoUuTienModel;
import com.demo.models.NhanVienModel;
import com.demo.models.YeuCauModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.jsp.tagext.TryCatchFinally;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AdminServlet
 */
/* Cấu hình để Upload File*/
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10, // 10MB
		fileSizeThreshold = 1024 * 1024 * 10 // 10MB
)
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmployeeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			doGet_Index(request, response);
		}else {
			if(action.equalsIgnoreCase("create-request")) {
				doGet_CreateRequest(request, response);
			}else if(action.equalsIgnoreCase("profile")) {
				doGet_Profile(request, response);
			}else if(action.equalsIgnoreCase("edit-profile")) {
				doGet_EditProfile(request, response);
			}else if(action.equalsIgnoreCase("logout")) {
				doGet_Logout(request, response);
			}else if(action.equalsIgnoreCase("change-password")) {
				doGet_ChangePass(request, response);
			}else if(action.equalsIgnoreCase("search-by-dates")) {
				doGet_SearchByDates(request, response);
			}else if(action.equalsIgnoreCase("search-by-priority")) {
				doGet_SearchByPriority(request, response);
			}
		}
	}
	
	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		YeuCauModel yeuCauModel = new YeuCauModel();
		request.setAttribute("yeucaus", yeuCauModel.findByEmployeeUsername(request.getSession().getAttribute("username").toString()));
		
		request.getRequestDispatcher("WEB-INF/views/user/employee/requestHistory.jsp").forward(request, response);
	}
	
	protected void doGet_CreateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Gửi từ doPost_CreateRequest*/
		String msgNullHeader = request.getParameter("msgNullHeader");
		String msgNullContent = request.getParameter("msgNullContent");
		String msgCreateSuccess = request.getParameter("msgCreateSuccess");
		String msgCreateFailed = request.getParameter("msgCreateFailed");


		request.setAttribute("date", new Date());
		request.setAttribute("msgNullHeader", msgNullHeader);
		request.setAttribute("msgNullContent", msgNullContent);
		request.setAttribute("msgCreateSuccess", msgCreateSuccess);
		request.setAttribute("msgCreateFailed", msgCreateFailed);

		
		request.getRequestDispatcher("WEB-INF/views/user/employee/createRequest.jsp").forward(request, response);
	}
	
	protected void doGet_Profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		NhanVienModel nhanVienModel = new NhanVienModel();
		Nhanvien nhanvien = nhanVienModel.findByUsername(request.getSession().getAttribute("username").toString());
		
		if(nhanvien != null) {
			request.setAttribute("nhanvien", nhanvien);
		}
		
		request.setAttribute("msgUpdateSuccess", request.getParameter("msgSuccess"));
		request.setAttribute("msgChangePass", request.getParameter("msgChangePass"));

		request.getRequestDispatcher("WEB-INF/views/user/employee/profile.jsp").forward(request, response);
	}
	
	
	protected void doGet_EditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		Nhanvien nhanvien = nhanVienModel.findByUsername(request.getSession().getAttribute("username").toString());

		if(nhanvien != null) {
			request.setAttribute("nhanvien", nhanvien);
		}
		request.getRequestDispatcher("WEB-INF/views/user/employee/editProfile.jsp").forward(request, response);
	}
	
	protected void doGet_Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("fullname");
		request.getSession().removeAttribute("photo");
		response.sendRedirect("auth");
	}
	
	protected void doGet_ChangePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String error = request.getParameter("error"); // Từ doPost_ChangePass
		String msgChangePass = request.getParameter("msgChangePass");
		
		request.setAttribute("error", error);
		request.setAttribute("msgChangePass", msgChangePass);
		request.getRequestDispatcher("WEB-INF/views/user/employee/changePassword.jsp").forward(request, response);

	}
	
protected void doGet_SearchByDates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String from = request.getParameter("startDate");
			String to = request.getParameter("endDate");
			
			if(from == null || from.isEmpty() || to == null | to.isEmpty()) {
				response.sendRedirect("employee");
				return;
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = simpleDateFormat.parse(from);
			Date endDate = simpleDateFormat.parse(to);
			
			YeuCauModel yeuCauModel = new YeuCauModel();
			
			String employeeUsername = request.getSession().getAttribute("username").toString();
			
			request.setAttribute("yeucaus", yeuCauModel.searchBy_Dates_And_EmployeeUsername(startDate, endDate, employeeUsername));
			
			request.getRequestDispatcher("WEB-INF/views/user/employee/requestHistory.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	protected void doGet_SearchByPriority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int priorityId = Integer.parseInt(request.getParameter("priorityId"));
		
		YeuCauModel yeuCauModel = new YeuCauModel();
		
		String employeeUsername = request.getSession().getAttribute("username").toString();

		request.setAttribute("yeucaus", yeuCauModel.searchBy_PriorityId_And_EmployeeUsername(priorityId, employeeUsername));
		
		request.getRequestDispatcher("WEB-INF/views/user/employee/requestHistory.jsp").forward(request, response);	
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("update-profile")) {
			doPost_UpdateProfile(request,response);
		}else if(action.equals("change-password")) {
			doPost_ChangePassword(request, response);
		}else if(action.equals("create-request")) {
			doPost_CreateRequest(request, response);
		}
	}
	
	protected void doPost_UpdateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String fullname = request.getParameter("fullname");
			String dob = request.getParameter("dob");
			
			NhanVienModel nhanVienModel = new NhanVienModel();
			Nhanvien nhanvien = nhanVienModel.findByUsername(request.getSession().getAttribute("username").toString());
	        // Kiểm tra fullname và cập nhật nếu khác null hoặc rỗng
	        if (fullname != null && !fullname.trim().isEmpty()) {
	            nhanvien.setHoTen(fullname);
	        }
	        
	        // Kiểm tra dob và cập nhật nếu khác null hoặc rỗng
	        if (dob != null && !dob.trim().isEmpty()) {
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            nhanvien.setNgaySinh(simpleDateFormat.parse(dob));
	        }
	        
	        /* Upload File */
	        Part file = request.getPart("file");
	        if(file != null && file.getSize() > 0) {
	        	String filename = UploadHelper.uploadFile("assets/img", request, file);
		        nhanvien.setHinhAnh(filename);
	        }
	        
	        
			/* Create */
			boolean result = nhanVienModel.Update(nhanvien);
			if(result) {
				
				/* Set lại các session sau khi đã thay đổi */
				request.getSession().setAttribute("fullname", nhanvien.getHoTen());
				request.getSession().setAttribute("photo", nhanvien.getHinhAnh());
				
				response.sendRedirect("employee?action=profile&msgSuccess=Update-Successfully");
			}else {
				request.setAttribute("msgFailed", "Update-Failed");
				request.getRequestDispatcher("WEB-INF/views/user/employee/editProfile.jsp").forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_ChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String currentPassword = request.getParameter("currentPassword");
			String newPassword = request.getParameter("newPassword");
			String confirmPassword = request.getParameter("confirmPassword");
			
			NhanVienModel nhanVienModel = new NhanVienModel();
			Nhanvien nhanvien = nhanVienModel.findByUsername(request.getSession().getAttribute("username").toString());
			
			if(nhanvien == null) {
				response.sendRedirect("auth");
				return;
			}
			
			/* Kiểm tra mật khẩu hiện tại*/
			if(!BCrypt.checkpw(currentPassword, nhanvien.getPassword())) {
			
				response.sendRedirect("employee?action=change-password&error=Your currrent Password do not match")	;
				return;
			}
			
			/* Kiểm tra mật khẩu mới và mật khẩu xác nhận*/
			if(!newPassword.equals(confirmPassword)) {
				response.sendRedirect("employee?action=change-password&error=New Password vs Confirm Password do not match")	;
				return;
			}
			
			/* Nếu không bị dính mấy lỗi trên thì cập nhật mật khẩu mới */
			String newPasswordHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
			nhanvien.setPassword(newPasswordHash);
			boolean result = nhanVienModel.Update(nhanvien);
			
			if(result) {
				response.sendRedirect("employee?action=change-password&msgChangePass=Change Password Successfully");	
			}else {
				response.sendRedirect("employee?action=change-password&msgChangePass=Change Password Failed");
			}
		}catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
	
	protected void doPost_CreateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String header = request.getParameter("header");
			String content = request.getParameter("content");
			String date = request.getParameter("date");

			int maDoUuTien = Integer.parseInt(request.getParameter("priority"));
			String maNVGui = request.getSession().getAttribute("username").toString();
			
			if (header == null || header.trim().isEmpty()) {
			    response.sendRedirect("employee?action=createRequest&msgNullHeader=Please enter header");
			    return;
			}

			if (content == null || content.trim().isEmpty()) {
			    response.sendRedirect("employee?action=createRequest&msgNullContent=Please enter content");
			    return;
			}
			
		    if (date == null || date.trim().isEmpty()) {
		        response.sendRedirect("employee?action=createRequest&msgNullDate=Please enter date");
		        return;
		    }
			
			/* Khởi tạo Nhân viên Gửi*/
			NhanVienModel nhanVienModel = new NhanVienModel();
			Nhanvien nhanVienGui = nhanVienModel.findByUsername(maNVGui);
			
			/* Khởi tạo Độ Ưu Tiên*/
			DoUuTienModel doUuTienModel = new DoUuTienModel();
			Douutien doUuTien = doUuTienModel.findById(maDoUuTien);
			
			/* Khởi tạo yêu cầu */
			YeuCauModel yeuCauModel = new YeuCauModel();
			Yeucau yeucau = new Yeucau();
			
			yeucau.setTieuDe(header);
			yeucau.setNoiDung(content);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			yeucau.setNgayGui(simpleDateFormat.parse(date));
			
			yeucau.setDouutien(doUuTien);
			yeucau.setNhanvienByMaNvGui(nhanVienGui);
			
			
			boolean result = yeuCauModel.Create(yeucau);
			if(result) {
				response.sendRedirect("employee?action=create-request&msgCreateSuccess=Send Request Successfully");
			}else {
				response.sendRedirect("employee?action=create-request&msgCreateFailed=Send Request Failed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
