package com.kpi.controllers;

import com.kpi.dao.OrderDao;
import com.kpi.dao.OrderDetailsDao;
import com.kpi.models.MenuElement;
import com.kpi.models.Order;
import com.kpi.models.OrderDetails;
import com.kpi.sevices.CartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "OrderProcessingController", value = "/OrderProcessingController")
public class OrderProcessingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderDao = new OrderDao();
        ArrayList<Order> orders = orderDao.getUndoneOrders();
        HashMap<Order, HashMap<OrderDetails, MenuElement>> undoneOrders = new HashMap<>();
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        for (Order order: orders){
            ArrayList<OrderDetails> orderDetails = orderDetailsDao.getAllByOrdersId(order.getOrderId());
            undoneOrders.put(order, CartService.getOrderDetailsWithMenu(orderDetails));
        }
        request.setAttribute("undoneOrders", undoneOrders);
        request.getRequestDispatcher("WEB-INF/jsp/orderProcessingPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDao orderDao = new OrderDao();
        orderDao.updateOrderStatus(orderId);
        request.getRequestDispatcher("WEB-INF/jsp/orderProcessingPage.jsp").forward(request, response);
    }
}
