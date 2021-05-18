package com.kpi.controllers;

import com.kpi.dao.DAOFactory;
import com.kpi.dao.MenuElementDao;
import com.kpi.dao.OrderDetailsDao;
import com.kpi.dao.mysql.MySQLDaoFactory;
import com.kpi.dao.mysql.OrderDetailsMySQLDao;

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
        DAOFactory mySQLDaoFactory = new MySQLDaoFactory();
        MenuElementDao menuElementDao = mySQLDaoFactory.getMenuElementDao();
        OrderDetailsDao orderDetailsDao = mySQLDaoFactory.getOrderDetailsDao();
        orderDetailsDao.deleteByMenuElementId(elementId);
        menuElementDao.delete(elementId);
        request.getRequestDispatcher("MenuUpdateController").forward(request,response);
    }
}
