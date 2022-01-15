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
import com.arjun.imageManagementUtility.entity.SignupEntity;

@MultipartConfig
@WebServlet("/imageUtility")
public class ImgController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ImageDao imgdao;

	    public void init() {
	        imgdao = new ImageDao();
	    }
	

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	SignupEntity signup = new SignupEntity();
    	ImageEntity img = new ImageEntity();
    	img.setImageName(request.getParameter("filename"));
    	String username = request.getParameter("userid");
    	int id = imgdao.getUserId(username);
    	Part part = request.getPart("photo");
    	InputStream is = null;
    	if (part != null) {
    		is = part.getInputStream();
    	}
    	byte[] data = readFully(is);
    	
    	double imgsize = imgdao.convertToMB(part.getSize());
    			
    	is.read(data);
    	img.setImage(data);
    	img.setImagesize(imgsize);
    	img.setCommonId(id);
    	
    	double totalSize = 0.0D;
    	totalSize = imgdao.getTotalDataSize(id);
    	
    	if(imgsize<=1 || totalSize > 10) {
    		imgdao.saveImage(img);
    	}else {
    		System.out.println("Size of the image is greater than 1 MB, or total Size is exceeding 10 MB");
    	}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("imageUtility.jsp");
        dispatcher.forward(request, response);
    }
    
    public static byte[] readFully(InputStream is) throws IOException {
		byte[] buffer = new byte[is.available()];
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while ((bytesRead = is.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
		return output.toByteArray();
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	
    	        response.sendRedirect("imageUtility.jsp");
    	    }

    }
    
