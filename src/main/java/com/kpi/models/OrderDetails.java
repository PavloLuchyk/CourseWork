package com.kpi.models;

import java.util.Objects;

public class OrderDetails {
    private int orderId;
    private int menuElementId;
    private int quantity;

    public OrderDetails(int orderId, int menuelementId, int quantity) {
        this.orderId = orderId;
        this.menuElementId = menuelementId;
        this.quantity = quantity;
    }

    public OrderDetails(int menuelementId, int quantity) {
        this.orderId = 0;
        this.menuElementId = menuelementId;
        this.quantity = quantity;
    }

    public OrderDetails(int menuelementId) {
        this.menuElementId = menuelementId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuElementId() {
        return menuElementId;
    }

    public void setMenuElementId(int menuElementId) {
        this.menuElementId = menuElementId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return orderId == that.orderId && menuElementId == that.menuElementId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, menuElementId);
    }
}
