package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.models.Menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MenuUpdateController", value = "/MenuUpdateController")
public class MenuUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = new Menu(new MenuElementDao().getAll());
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("WEB-INF/jsp/menuUpdatePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
