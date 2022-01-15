package com.arjun.imageManagementUtility.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjun.imageManagementUtility.dao.ImageDao;

@WebServlet("/imgDelete")
public class ImageDeleteController extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    	    throws ServletException, IOException {
	    	
	    	String imgId = request.getParameter("imgId");
			int delId=Integer.parseInt(imgId);
			
			ImageDao imgdao = new ImageDao();
			imgdao.deleteImage(delId);
			response.sendRedirect("imageUtility.jsp");
			
	    }

}
