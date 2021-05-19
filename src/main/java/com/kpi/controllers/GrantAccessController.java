package com.kpi.controllers;

import com.kpi.dao.UserDao;
import com.kpi.dao.mysql.UserMySQLDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GrantAccessController", value = "/GrantAccessController")
public class GrantAccessController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if  (request.getSession().getAttribute("admin")!= null
                && (boolean) request.getSession().getAttribute("admin")) {
            UserDao userDao = new UserMySQLDao();
            userDao.updateAdmin(
                    Integer.parseInt(request.getParameter("userId")),
                    !Boolean.parseBoolean(request.getParameter("admin"))
            );
            request.getRequestDispatcher("UserViewController").forward(request, response);
        } else {
            response.sendRedirect("LogInController");
        }
    }
}
