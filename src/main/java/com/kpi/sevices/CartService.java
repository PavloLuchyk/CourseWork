package com.kpi.sevices;

import com.kpi.dao.MenuElementDao;
import com.kpi.dao.OrderDao;
import com.kpi.dao.mysql.MenuElementMySQLDao;
import com.kpi.dao.mysql.OrderMySQLDao;
import com.kpi.dao.mysql.OrderDetailsMySQLDao;
import com.kpi.models.MenuElement;
import com.kpi.models.Order;
import com.kpi.models.OrderDetails;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class CartService {
    public static void addToCart(HttpServletRequest request){
        ArrayList<OrderDetails> orderDetails = getCartList(request);
        int quantity =Integer.parseInt(request.getParameter("quantity"));
        int menuElementId = Integer.parseInt(request.getParameter("menuElementId"));
        OrderDetails orderDetail = new OrderDetails(menuElementId, quantity);
        if (orderDetails.contains(orderDetail)){
            orderDetail = orderDetails.stream().filter(s->menuElementId == s.getMenuElementId()).findFirst().get();
            orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
        } else {
            orderDetails.add(orderDetail);
        }
    }

    public static void viewCart(HttpServletRequest request){
        ArrayList<OrderDetails> orderDetails = getCartList(request);
        request.setAttribute("orderMenuElements", getOrderDetailsWithMenu(orderDetails));
    }

    public static HashMap<OrderDetails, MenuElement> getOrderDetailsWithMenu(ArrayList<OrderDetails> orderDetails){
        HashMap<OrderDetails, MenuElement> orderMenuElements = new HashMap<>();
        MenuElementDao menuElementDao = new MenuElementMySQLDao();
        for (OrderDetails i: orderDetails){
            for (MenuElement j: menuElementDao.getAll()){
                if (i.getMenuElementId() == j.getElementId()){
                    orderMenuElements.put(i, j);
                }
            }
        }
        return orderMenuElements;
    }

    public static void order(HttpServletRequest request){
        if (request.getSession().getAttribute("userId") != null){
            int userId = (int) request.getSession().getAttribute("userId");
            ArrayList<OrderDetails> orderDetails = getCartList(request);
            if (!orderDetails.isEmpty()) {
                Order order = new Order(userId, new Timestamp(System.currentTimeMillis()));
                OrderDao orderDao = new OrderMySQLDao();
                orderDao.add(order);
                int orderId = orderDao.getAll().get(orderDao.getAll().size() - 1).getOrderId();
                OrderDetailsMySQLDao orderDetailsDao = new OrderDetailsMySQLDao();
                for (OrderDetails i : orderDetails) {
                    i.setOrderId(orderId);
                    orderDetailsDao.add(i);
                }
                request.getSession().setAttribute("orderDetails", new ArrayList<>());
            } else {
                request.setAttribute("errorMessage", "Order is empty!");
            }
        } else {
            request.setAttribute("errorMessage", "Please log in!");
        }
    }

    private static ArrayList<OrderDetails> getCartList(HttpServletRequest request){
        ArrayList<OrderDetails> orderDetails;
        if (request.getSession().getAttribute("orderDetails") == null){
            orderDetails = new ArrayList<>();
            request.getSession().setAttribute("orderDetails", orderDetails);
        } else {
            orderDetails = (ArrayList<OrderDetails>)request.getSession().getAttribute("orderDetails");
        }
        return orderDetails;
    }

}
