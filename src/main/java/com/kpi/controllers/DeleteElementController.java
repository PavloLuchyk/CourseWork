package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.dao.OrderDetailsDao;
import com.kpi.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteElementController", value = "/DeleteElementController")
public class DeleteElementController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int elementId = Integer.parseInt(request.getParameter("elementId"));
        System.out.println(elementId);
        MenuElementDao menuElementDao = new MenuElementDao();
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        orderDetailsDao.deleteByMenuElementId(elementId);
        menuElementDao.delete(elementId);
        request.getRequestDispatcher("MenuUpdateController").forward(request,response);
    }
}
