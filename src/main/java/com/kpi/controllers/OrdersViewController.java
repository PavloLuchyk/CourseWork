package com.kpi.controllers;

import com.kpi.dao.DAOFactory;
import com.kpi.dao.OrderDao;
import com.kpi.dao.OrderDetailsDao;
import com.kpi.dao.mysql.MySQLDaoFactory;
import com.kpi.models.MenuElement;
import com.kpi.models.Order;
import com.kpi.models.OrderDetails;
import com.kpi.sevices.CartService;
import com.kpi.sevices.OrderHistoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "OrdersViewController", value = "/OrdersViewController")
public class OrdersViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory daoFactory = new MySQLDaoFactory();
        OrderDao orderDao = daoFactory.getOrderDao();
        ArrayList<Order> orders = orderDao.getAll();
        request.setAttribute("allOrders", OrderHistoryService.getOrders(daoFactory, orders));
        request.getRequestDispatcher("WEB-INF/jsp/allOrdersPage.jsp").forward(request, response);
    }


}
