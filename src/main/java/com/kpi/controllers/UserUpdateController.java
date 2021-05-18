package com.kpi.controllers;

import com.kpi.dao.mysql.UserMySQLDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserUpdateController", value = "/UserUpdateController")
public class UserUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int)request.getSession().getAttribute("userId");
        UserMySQLDao userDao = new UserMySQLDao();
        request.setAttribute("user", userDao.get(userId));
        request.getRequestDispatcher("WEB-INF/jsp/userUpdatePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int)request.getSession().getAttribute("userId");
        UserMySQLDao userDao = new UserMySQLDao();

    }
}
