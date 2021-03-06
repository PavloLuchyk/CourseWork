package com.kpi.controllers;

import com.kpi.dao.DAOFactory;
import com.kpi.dao.OrderDao;
import com.kpi.dao.OrderDetailsDao;
import com.kpi.dao.mysql.MySQLDaoFactory;
import com.kpi.dao.mysql.OrderMySQLDao;
import com.kpi.dao.mysql.OrderDetailsMySQLDao;
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

@WebServlet(name = "OrderHistoryController", value = "/OrderHistoryController")
public class OrderHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processData(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processData(request, response);
    }

    private void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int userId = (int) request.getSession().getAttribute("userId");
        DAOFactory daoFactory = new MySQLDaoFactory();
        OrderDao orderDao = daoFactory.getOrderDao();
        ArrayList<Order> orders = orderDao.getByUserId(userId);
        request.setAttribute("orderHistory", OrderHistoryService.getOrders(daoFactory, orders));
        request.getRequestDispatcher("WEB-INF/jsp/orderHistoryPage.jsp").forward(request, response);
    }

}
