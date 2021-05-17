package com.kpi.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;

public class Order {
    private int orderId;
    private int userId;
    private Timestamp time;
    public boolean status;

    public Order(int orderId, int userId, Timestamp time, boolean status) {
        this.orderId = orderId;
        this.userId = userId;
        this.time = time;
        this.status = status;
    }

    public Order(int userId, Timestamp time) {
        orderId = 0;
        this.userId = userId;
        this.time = time;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
