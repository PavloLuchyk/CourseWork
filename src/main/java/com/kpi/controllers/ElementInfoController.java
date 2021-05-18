package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.sevices.CartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ElementInfoController", value = "/ElementInfoController")
public class ElementInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuElementDao menuElementDao = new MenuElementDao();
        request.setAttribute("menuElement", menuElementDao.get(Integer.parseInt(request.getParameter("elementId"))));
        request.getRequestDispatcher("WEB-INF/jsp/elementDetailsPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuElementDao menuElementDao = new MenuElementDao();
        CartService.addToCart(request);
        request.setAttribute("menuElement", menuElementDao.get(Integer.parseInt(request.getParameter("menuElementId"))));
        request.getRequestDispatcher("WEB-INF/jsp/elementDetailsPage.jsp").forward(request, response);
    }
}
