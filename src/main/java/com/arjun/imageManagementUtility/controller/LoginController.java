package com.arjun.imageManagementUtility.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.arjun.imageManagementUtility.dao.UserDao;
import com.arjun.imageManagementUtility.model.LoginModel;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao dao;

    public void init() {
        dao = new UserDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        response.sendRedirect("login.jsp");
    	    }

    	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        try {
    	            authenticate(request, response);
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }

    	    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {
    	    	HttpSession session = request.getSession();
    	    	LoginModel login = new LoginModel();
    	    	
    	    	login.setUsername(request.getParameter("username"));
    			login.setPassword(request.getParameter("password"));
    			
    			boolean result = dao.validate(login);
    	        if (result) {
    	        	session.setAttribute("username",login.getUsername());
    	            RequestDispatcher dispatcher = request.getRequestDispatcher("imageUtility.jsp");
    	            dispatcher.forward(request, response);
    	        } else {
    	            throw new Exception("Login not successful..");
    	        }
    	    }
    	}
