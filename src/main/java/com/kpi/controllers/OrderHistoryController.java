package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.dao.OrderDao;
import com.kpi.dao.OrderDetailsDao;
import com.kpi.dao.UserDao;
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
        OrderDao orderDao = new OrderDao();
        ArrayList<Order> orders = orderDao.getByUserId(userId);
        HashMap<Order, HashMap<OrderDetails, MenuElement>> orderHistory = new HashMap<>();
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        for (Order order: orders){
            ArrayList<OrderDetails> orderDetails = orderDetailsDao.getAllByOrdersId(order.getOrderId());
            orderHistory.put(order, CartService.getOrderDetailsWithMenu(orderDetails));
        }
        request.setAttribute("orderHistory", orderHistory);
        request.getRequestDispatcher("WEB-INF/jsp/orderHistoryPage.jsp").forward(request, response);
    }

}
