package com.kpi.dao.mysql;

import com.kpi.dao.*;

public class MySQLDaoFactory implements DAOFactory {
    @Override
    public OrderDao getOrderDao() {
        return new OrderMySQLDao();
    }

    @Override
    public OrderDetailsDao getOrderDetailsDao() {
        return new OrderDetailsMySQLDao();
    }

    @Override
    public UserDao getUserDao() {
        return new UserMySQLDao();
    }

    @Override
    public MenuElementDao getMenuElementDao() {
        return new MenuElementMySQLDao();
    }
}
