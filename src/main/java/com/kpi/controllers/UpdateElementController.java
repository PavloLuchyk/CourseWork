package com.kpi.controllers;

import com.kpi.dao.mysql.MenuElementMySQLDao;
import com.kpi.models.MenuElement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateElementController", value = "/UpdateElementController")
public class UpdateElementController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuElementMySQLDao menuElementDao = new MenuElementMySQLDao();
        request.setAttribute("menuElement", menuElementDao.get(Integer.parseInt(request.getParameter("menuElementId"))));
        request.getRequestDispatcher("WEB-INF/jsp/elementUpdatePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuElementMySQLDao menuElementDao = new MenuElementMySQLDao();
        MenuElement menuElement = new MenuElement(
                Integer.parseInt(request.getParameter("elementId")),
                request.getParameter("name"),
                request.getParameter("description"),
                request.getParameter("ingredients"),
                Double.parseDouble(request.getParameter("price")));
        menuElementDao.updateDetails(menuElement);
        request.setAttribute("menuElement", menuElementDao.get(Integer.parseInt(request.getParameter("elementId"))));
        request.getRequestDispatcher("WEB-INF/jsp/elementUpdatePage.jsp").forward(request, response);
    }
}
