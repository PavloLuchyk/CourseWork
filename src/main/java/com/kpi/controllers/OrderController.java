package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "OrderController", value = "/OrderController")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartService.viewCart(request);
        request.getRequestDispatcher("WEB-INF/jsp/cartPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartService.order(request);
        request.getRequestDispatcher("WEB-INF/jsp/cartPage.jsp").forward(request, response);
    }
}
