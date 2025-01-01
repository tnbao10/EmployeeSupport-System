package com.demo.servlets.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Nhanvien;
import com.demo.entities.Yeucau;
import com.demo.models.DoUuTienModel;
import com.demo.models.NhanVienModel;
import com.demo.models.YeuCauModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			doGet_Index(request, response);
		}else {
			if(action.equalsIgnoreCase("employee-list")) {
				doGet_EmployeeList(request, response);
			}else if(action.equalsIgnoreCase("assignment")) {
				doGet_Assignment(request, response);
			}else if(action.equalsIgnoreCase("create-account")) {
				doGet_CreateAccount(request, response);
			}else if(action.equalsIgnoreCase("logout")) {
				doGet_Logout(request, response);
			}else if(action.equalsIgnoreCase("search-by-username")) {
				doGet_SearchByUsername(request, response);
			}else if(action.equalsIgnoreCase("search-by-dates")) {
				doGet_SearchByDates(request, response);
			}else if(action.equalsIgnoreCase("search-by-priority")) {
				doGet_SearchByPriority(request, response);
			}else if(action.equalsIgnoreCase("search-by-dates-and-employee")) {
				doGet_SearchByDatesAndEmployee(request, response);
			}else if(action.equalsIgnoreCase("search-by-priority-and-employee")) {
				doGet_SearchByPriorityAndEmployee(request, response);
			}else if(action.equalsIgnoreCase("employee-request-list")) {
				doGet_EmployeeRequestList(request, response);
			}
		}
	}
	
	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		request.setAttribute("nhanviens", nhanVienModel.findAll());

		
		request.getRequestDispatcher("WEB-INF/views/admin/index.jsp").forward(request, response);
	}
	
	protected void doGet_EmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		request.setAttribute("nhanviens", nhanVienModel.findAll());
		
		request.getRequestDispatcher("WEB-INF/views/admin/employeeList.jsp").forward(request, response);
	}
	
	protected void doGet_SearchByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		if(username == null || username.isEmpty()) {
			response.sendRedirect("admin?action=employee-list");
			return;
		}
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		request.setAttribute("nhanviens", nhanVienModel.searchByUsername(username));
		
		request.getRequestDispatcher("WEB-INF/views/admin/employeeList.jsp").forward(request, response);
	}
	
	protected void doGet_Assignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		YeuCauModel yeuCauModel = new YeuCauModel();
		request.setAttribute("yeucaus", yeuCauModel.findAll());
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		request.setAttribute("nhanvienhotros", nhanVienModel.findAllSupportEmployee());
		
		/* Từ doPost_Assignment */
		request.setAttribute("msgUpdateSuccess", request.getParameter("msgUpdateSuccess"));
		request.setAttribute("msgUpdateFailed", request.getParameter("msgUpdateFailed"));
		
		request.getRequestDispatcher("WEB-INF/views/admin/assignment.jsp").forward(request, response);
	}
	
	protected void doGet_SearchByDates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String from = request.getParameter("startDate");
			String to = request.getParameter("endDate");
			
			 // Check if 'from' or 'to' is null or empty
	        if (from == null || from.isEmpty() || to == null || to.isEmpty()) {
	            response.sendRedirect("admin?action=assignment");
	            return;
	        }

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = simpleDateFormat.parse(from);
			Date endDate = simpleDateFormat.parse(to);
			
			YeuCauModel yeuCauModel = new YeuCauModel();
			request.setAttribute("yeucaus", yeuCauModel.searchByDate(startDate, endDate));
			
			NhanVienModel nhanVienModel = new NhanVienModel();
			request.setAttribute("nhanvienhotros", nhanVienModel.findAllSupportEmployee());
			
			
			request.getRequestDispatcher("WEB-INF/views/admin/assignment.jsp").forward(request, response);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doGet_SearchByPriority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int priorityId = Integer.parseInt(request.getParameter("priorityId"));
		
		YeuCauModel yeuCauModel = new YeuCauModel();
		request.setAttribute("yeucaus", yeuCauModel.searchByPriorityId(priorityId));
		
		NhanVienModel nhanVienModel = new NhanVienModel();
		request.setAttribute("nhanvienhotros", nhanVienModel.findAll());
		
		request.getRequestDispatcher("WEB-INF/views/admin/assignment.jsp").forward(request, response);

		
	}
	
	protected void doGet_CreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("msgSuccess", request.getParameter("msgSuccess")); // Lấy từ doPost_Register
		request.setAttribute("msgFailed", request.getParameter("msgFailed")); // Lấy từ doPost_Register

		request.getRequestDispatcher("WEB-INF/views/admin/createAccount.jsp").forward(request, response);
	}
	
	protected void doGet_Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("fullname");
		response.sendRedirect("auth");
	}
	
	protected void doGet_EmployeeRequestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		int quyen = Integer.parseInt(request.getParameter("quyen"));
		
		YeuCauModel yeuCauModel = new YeuCauModel();
		
		if(quyen == 1) {
			request.setAttribute("yeucaus", yeuCauModel.findByEmployeeUsername(username));
		}else if(quyen == 2) {
			request.setAttribute("yeucaus", yeuCauModel.findBySupportEmployeeUsername(username));
		}
		
		request.setAttribute("username", username);
		request.setAttribute("quyen", quyen);


		request.getRequestDispatcher("WEB-INF/views/admin/employeeRequestList.jsp").forward(request, response);
	}
	protected void doGet_SearchByDatesAndEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String from = request.getParameter("startDate");
			String to = request.getParameter("endDate");
			String username = request.getParameter("username");
			int quyen = Integer.parseInt(request.getParameter("quyen"));
			
			 // Check if 'from' or 'to' is null or empty
	        if (from == null || from.isEmpty() || to == null || to.isEmpty()) {
	            response.sendRedirect("admin?action=employee-request-list&username="+ username +"&quyen="+quyen+"");
	            return;
	        }
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = simpleDateFormat.parse(from);
			Date endDate = simpleDateFormat.parse(to);
			
			YeuCauModel yeuCauModel = new YeuCauModel();
			if(quyen == 1) {
				request.setAttribute("yeucaus", yeuCauModel.searchBy_Dates_And_EmployeeUsername(startDate,endDate,username));
			}else if(quyen == 2) {
				request.setAttribute("yeucaus", yeuCauModel.searchBy_Dates_And_SupportEmployeeUsername(startDate,endDate,username));
			}
			
			request.setAttribute("quyen", quyen);
			request.setAttribute("username", username);
			
			request.getRequestDispatcher("WEB-INF/views/admin/employeeRequestList.jsp").forward(request, response);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doGet_SearchByPriorityAndEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int priorityId = Integer.parseInt(request.getParameter("priorityId"));
		String username = request.getParameter("username");
		int quyen = Integer.parseInt(request.getParameter("quyen"));
		
		YeuCauModel yeuCauModel = new YeuCauModel();
		if(quyen == 1) {
			request.setAttribute("yeucaus", yeuCauModel.searchBy_PriorityId_And_EmployeeUsername(priorityId,username));
		}else if(quyen == 2) {
			request.setAttribute("yeucaus", yeuCauModel.searchBy_PriorityId_And_SupportEmployeeUsername(priorityId, username));
		}
		
		request.setAttribute("quyen", quyen);
		request.setAttribute("username", username);
		
		request.getRequestDispatcher("WEB-INF/views/admin/employeeRequestList.jsp").forward(request, response);
	}
	




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("create-account")) {
			doPost_CreateAccount(request, response);
		}else if(action.equalsIgnoreCase("assignment")) {
			doPost_Assignment(request, response);
		}
	}
	
	protected void doPost_CreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int quyen = Integer.parseInt(request.getParameter("quyen"));
			
			Nhanvien nhanvien = new Nhanvien();
			nhanvien.setUsername(username);
			nhanvien.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
			nhanvien.setQuyen(quyen);
			
			NhanVienModel nhanVienModel = new NhanVienModel();
			boolean result = nhanVienModel.Create(nhanvien);
			
			if (result) {
			    response.sendRedirect("admin?action=create-account&msgSuccess=Create-Successfully");
			} else {
			    response.sendRedirect("admin?action=create-account&msgFailed=Create-Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Assignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String supportEmployeeUsername = request.getParameter("supportEmployeeUsername");
			int maYeuCau = Integer.parseInt(request.getParameter("yeucauId"));
		

			
			YeuCauModel yeuCauModel = new YeuCauModel();
			Yeucau yeucau = yeuCauModel.findById(maYeuCau);
			
			NhanVienModel nhanVienModel = new NhanVienModel();
			Nhanvien nhanvienhotro = nhanVienModel.findSupportEmployeeByUsername(supportEmployeeUsername);
			
			yeucau.setNhanvienByMaNvXuLy(nhanvienhotro);
			
			boolean result = yeuCauModel.Update(yeucau);
			
			if(result) {
				response.sendRedirect("admin?action=assignment&msgUpdateSuccess=Assign Support Employee Successfully");
			}else {
				response.sendRedirect("admin?action=assignment&msgUpdateFailed=Assign Support Employee Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
