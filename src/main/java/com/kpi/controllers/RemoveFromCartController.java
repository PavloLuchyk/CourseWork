package com.kpi.controllers;

import com.kpi.models.OrderDetails;
import com.kpi.sevices.CartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.kpi.sevices.CartService.getOrderDetailsWithMenu;

@WebServlet(name = "RemoveFromCartController", value = "/RemoveFromCartController")
public class RemoveFromCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<OrderDetails> orderDetails = CartService.getCartList(request);
        int elementId = Integer.parseInt(request.getParameter("elementId"));
        for (OrderDetails orderDetail: orderDetails){
            if (orderDetail.getMenuElementId() == elementId){
                orderDetails.remove(orderDetail);
                break;
            }
        }

        request.getSession().setAttribute("orderDetails", orderDetails);
        request.setAttribute("orderMenuElements", getOrderDetailsWithMenu(orderDetails));
        request.getRequestDispatcher("WEB-INF/jsp/cartPage.jsp").forward(request, response);
    }
}
