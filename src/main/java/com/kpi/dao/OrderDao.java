package com.kpi.dao;

import com.kpi.models.Order;

import java.sql.*;
import java.util.ArrayList;

public interface OrderDao extends AbstractDAO<Order> {
    void updateOrderStatus(int id);
    ArrayList<Order> getUndoneOrders();
    ArrayList<Order> getByUserId(int id);
}
