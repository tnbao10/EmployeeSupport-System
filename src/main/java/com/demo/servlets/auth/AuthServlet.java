package com.demo.servlets.auth;

import java.io.IOException;

import com.demo.entities.Nhanvien;
import com.demo.models.NhanVienModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AuthServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			doGet_Login(request, response);
		}else {
			
		}
	}
	
	protected void doGet_Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("WEB-INF/views/auth/login.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("login")) {
			doPost_Login(request, response);
		}
	}
	
	protected void doPost_Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		NhanVienModel nhanVienModel = new NhanVienModel();
		
		
		if(nhanVienModel.login(username, password)) {
			
			request.getSession().setAttribute("username", username); // Set session cho username
			
			Nhanvien nhanvien = nhanVienModel.findByUsername(username);
			
			/* Set session cho mấy thuộc tính còn lại*/
			request.getSession().setAttribute("fullname", nhanvien.getHoTen());
			request.getSession().setAttribute("photo", nhanvien.getHinhAnh());

			if (nhanvien.getQuyen() == 1) {            
			    response.sendRedirect(request.getContextPath() + "/employee");
			} else if (nhanvien.getQuyen() == 2) {
			    response.sendRedirect(request.getContextPath() + "/support-employee");
			} else if (nhanvien.getQuyen() == 3) {
			    response.sendRedirect(request.getContextPath() + "/admin");
			}

		}else {
			request.setAttribute("messageFail", "Incorrect Username or Password");
			request.getRequestDispatcher("WEB-INF/views/auth/login.jsp").forward(request, response);
		}
	}

}
