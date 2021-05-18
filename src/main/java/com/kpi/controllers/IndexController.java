package com.kpi.controllers;

import com.kpi.dao.mysql.MenuElementMySQLDao;
import com.kpi.models.Menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IndexController", value = "/IndexController")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = new Menu(new MenuElementMySQLDao().getAll());
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = new Menu(new MenuElementMySQLDao().getAll());
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
