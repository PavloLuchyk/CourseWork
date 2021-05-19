package com.kpi.controllers;

import com.kpi.dao.DAOFactory;
import com.kpi.dao.OrderDao;
import com.kpi.dao.mysql.MySQLDaoFactory;
import com.kpi.models.Order;
import com.kpi.sevices.OrderHistoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrderProcessingController", value = "/OrderProcessingController")
public class OrderProcessingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory daoFactory = new MySQLDaoFactory();
        OrderDao orderDao = daoFactory.getOrderDao();
        ArrayList<Order> orders = orderDao.getUndoneOrders();
        request.setAttribute("undoneOrders", OrderHistoryService.getOrders(daoFactory, orders));
        request.getRequestDispatcher("WEB-INF/jsp/orderProcessingPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDao orderDao = new MySQLDaoFactory().getOrderDao();
        orderDao.updateOrderStatus(orderId);
        request.getRequestDispatcher("WEB-INF/jsp/orderProcessingPage.jsp").forward(request, response);
    }
}
