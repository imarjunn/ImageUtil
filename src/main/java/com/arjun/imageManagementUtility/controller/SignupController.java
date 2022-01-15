package com.arjun.imageManagementUtility.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjun.imageManagementUtility.dao.UserDao;
import com.arjun.imageManagementUtility.entity.SignupEntity;

@WebServlet("/register")
public class SignupController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao dao;

    public void init() {
        dao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        SignupEntity signup = new SignupEntity();
        signup.setName(name);
        signup.setUsername(username);
        signup.setPassword(password);
        signup.setRePassword(rePassword);

        dao.saveUser(signup);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}