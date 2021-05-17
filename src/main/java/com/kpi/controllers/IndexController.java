package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.models.Menu;
import com.kpi.models.MenuElement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "IndexController", value = "/IndexController")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = new Menu(new MenuElementDao().getAll());
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = new Menu(new MenuElementDao().getAll());
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
