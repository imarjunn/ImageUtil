package com.arjun.imageManagementUtility.controller;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.arjun.imageManagementUtility.dao.ImageDao;
import com.arjun.imageManagementUtility.entity.ImageEntity;
@MultipartConfig
@WebServlet("/editImg")
public class ImageEditController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	String imgId;
	@Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    	    throws ServletException, IOException {
		imgId = request.getParameter("idEdit");
		response.sendRedirect("edit.jsp");
	 			
	    	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		int editId=Integer.parseInt(imgId);
		
		ImageEntity img = new ImageEntity();
		ImageDao imgdao = new ImageDao();
		img.setImageName(request.getParameter("filename"));
		Part part = request.getPart("photo");
    	InputStream is = null;
    	if (part != null) {
    		is = part.getInputStream();
    	}
    	byte[] data = readFully(is);
    	
    	double imgsize = imgdao.convertToMB(part.getSize());
 	
    	is.read(data);
    	img.setImage(data);
    	if(imgsize<=1 ) {
    	imgdao.editImage(editId, img.getImageName(), img.getImage(), imgsize);
    	}else {
    		System.out.println("Size of the image is greater than 1 MB, or total Size is exceeding 10 MB");
    	}
    	RequestDispatcher dispatcher = request.getRequestDispatcher("imageUtility.jsp");
        dispatcher.forward(request, response);
	}
	 public static byte[] readFully(InputStream is) throws IOException {
			byte[] buffer = new byte[8192];
			int bytesRead;
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			while ((bytesRead = is.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
			return output.toByteArray();
		}
}
