package com.demo.servlets.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Nhanvien;
import com.demo.entities.Yeucau;
import com.demo.helpers.UploadHelper;
import com.demo.models.NhanVienModel;
import com.demo.models.YeuCauModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AdminServlet
 */
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10, // 10MB
		fileSizeThreshold = 1024 * 1024 * 10 // 10MB
)
@WebServlet("/support-employee")
public class SupportEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SupportEmployeeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			doGet_Index(request, response);
		}else {
			if(action.equalsIgnoreCase("profile")) {
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
	
		String username = request.getSession().getAttribute("username").toString(); // Lấy Session Username
		
		YeuCauModel yeuCauModel = new YeuCauModel();
		request.setAttribute("yeucaus", yeuCauModel.findBySupportEmployeeUsername(username));
		
		request.getRequestDispatcher("WEB-INF/views/user/supportEmployee/requestList.jsp").forward(request, response);
	}
	
	protected void doGet_Profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		Nhanvien nhanvien = nhanVienModel.findByUsername(request.getSession().getAttribute("username").toString());
		
		if(nhanvien != null) {
			request.setAttribute("nhanvien", nhanvien);
		}
		
		request.setAttribute("msgSuccess", request.getParameter("msgSuccess"));
		
		request.getRequestDispatcher("WEB-INF/views/user/supportEmployee/profile.jsp").forward(request, response);
	}
	
	protected void doGet_EditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		NhanVienModel nhanVienModel = new NhanVienModel();
		Nhanvien nhanvien = nhanVienModel.findByUsername(request.getSession().getAttribute("username").toString());
		
		if(nhanvien != null) {
			request.setAttribute("nhanvien", nhanvien);
		}
		request.getRequestDispatcher("WEB-INF/views/user/supportEmployee/editProfile.jsp").forward(request, response);
	}
	
	protected void doGet_Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("fullname");
		response.sendRedirect("auth");	
	}
	
	protected void doGet_ChangePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = request.getParameter("error");
		String msgChangePass = request.getParameter("msgChangePass");
		
		request.setAttribute("error", error);
		request.setAttribute("msgChangePass", msgChangePass);
		
		request.getRequestDispatcher("WEB-INF/views/user/supportEmployee/changePassword.jsp").forward(request, response);
	}
	
	protected void doGet_SearchByDates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String from = request.getParameter("startDate");
			String to = request.getParameter("endDate");
			
			if(from == null || from.isEmpty() || to == null || to.isEmpty()) {
				response.sendRedirect("support-employee");
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = simpleDateFormat.parse(from);
			Date endDate = simpleDateFormat.parse(to);
			
			YeuCauModel yeuCauModel = new YeuCauModel();
			
			String supportEmployeeUsername = request.getSession().getAttribute("username").toString();
			
			request.setAttribute("yeucaus", yeuCauModel.searchBy_Dates_And_SupportEmployeeUsername(startDate, endDate, supportEmployeeUsername));
			
			request.getRequestDispatcher("WEB-INF/views/user/supportEmployee/requestList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	protected void doGet_SearchByPriority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int priorityId = Integer.parseInt(request.getParameter("priorityId"));
		
		YeuCauModel yeuCauModel = new YeuCauModel();
		
		String supportEmployeeUsername = request.getSession().getAttribute("username").toString();

		request.setAttribute("yeucaus", yeuCauModel.searchBy_PriorityId_And_SupportEmployeeUsername(priorityId, supportEmployeeUsername));
		
		request.getRequestDispatcher("WEB-INF/views/user/supportEmployee/requestList.jsp").forward(request, response);	
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("update-profile")) {
			doPost_UpdateProfile(request,response);
		}else if(action.equalsIgnoreCase("change-password")) {
			doPost_ChangePassword(request, response);
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
			
			boolean result = nhanVienModel.Update(nhanvien);
			if(result) {
				/* Set session cho các thuộc tính còn lại */
				request.getSession().setAttribute("fullname", nhanvien.getHoTen());
				request.getSession().setAttribute("photo", nhanvien.getHinhAnh());
				
				response.sendRedirect("support-employee?action=profile&msgSuccess=Update-Successfully");
			}else {
				request.setAttribute("msgFailed", "Update-Failed");
				request.getRequestDispatcher("WEB-INF/views/user/support-employee/editProfile.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
protected void doPost_ChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		Nhanvien nhanvien = nhanVienModel.findByUsername(request.getSession().getAttribute("username").toString());
		
		if(!BCrypt.checkpw(currentPassword, nhanvien.getPassword())) {
			response.sendRedirect("support-employee?action=change-password&error=Your current Passowrd do not match");
			return;
		}
		
		if(!newPassword.equals(confirmPassword)) {
			response.sendRedirect("support-employee?action=change-password&error=New Password and Confirm Password do not match");
			return;
		}
		
		String newPasswordHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		nhanvien.setPassword(newPasswordHash);
		boolean result = nhanVienModel.Update(nhanvien);
		
		if(result) {
			response.sendRedirect("support-employee?action=change-password&msgChangePass=Change Password Successfully");
		}else {
			response.sendRedirect("support-employee?action=change-password&msgChangePass=Change Password Failed");
		}
		 
	}

}
