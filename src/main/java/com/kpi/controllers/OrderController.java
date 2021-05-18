package com.kpi.controllers;

import com.kpi.sevices.CartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
