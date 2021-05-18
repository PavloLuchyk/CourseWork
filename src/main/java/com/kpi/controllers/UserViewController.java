package com.kpi.controllers;

import com.kpi.dao.UserDao;
import com.kpi.dao.mysql.UserMySQLDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserViewController", value = "/UserViewController")
public class UserViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processData(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processData(request, response);
    }

    private void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UserDao userDao = new UserMySQLDao();
        request.setAttribute("users", userDao.getAll());
        request.getRequestDispatcher("WEB-INF/jsp/userViewPage.jsp").forward(request,response);
    }
}
