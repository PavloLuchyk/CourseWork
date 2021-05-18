package com.kpi.dao;

public interface DAOFactory {
    OrderDao getOrderDao();
    OrderDetailsDao getOrderDetailsDao();
    UserDao getUserDao();
    MenuElementDao getMenuElementDao();
}
