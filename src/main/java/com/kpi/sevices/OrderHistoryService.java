package com.kpi.sevices;

import com.kpi.dao.DAOFactory;
import com.kpi.dao.OrderDetailsDao;
import com.kpi.models.MenuElement;
import com.kpi.models.Order;
import com.kpi.models.OrderDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHistoryService {
    public static HashMap<Order, HashMap<OrderDetails, MenuElement>> getOrders(DAOFactory daoFactory, ArrayList<Order> orders){
        HashMap<Order, HashMap<OrderDetails, MenuElement>> ordersWithDetails = new HashMap<>();
        OrderDetailsDao orderDetailsDao = daoFactory.getOrderDetailsDao();
        for (Order order: orders){
            ArrayList<OrderDetails> orderDetails = orderDetailsDao.getAllByOrdersId(order.getOrderId());
            ordersWithDetails.put(order, CartService.getOrderDetailsWithMenu(orderDetails));
        }
        return ordersWithDetails;
    }
}
