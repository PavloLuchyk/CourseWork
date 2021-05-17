package com.kpi.dao;

import com.kpi.models.OrderDetails;

import java.sql.*;
import java.util.ArrayList;

public class OrderDetailsDao implements DAO<OrderDetails> {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorder",
                "root",
                "root");
    }

    @Override
    public void add(OrderDetails a) {
        String insert ="insert into orderdetails(orderId, menuelementId, quantity) VALUES (?,?,?);";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(insert)){
            preparedStatement.setInt(1, a.getOrderId());
            preparedStatement.setInt(2, a.getMenuElementId());
            preparedStatement.setInt(3, a.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String delete ="delete from orderdetails where orderId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(delete)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public void update(OrderDetails a) {
        String update ="update orderdetails set orderId = ?, menuelementId = ?, quantity = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(update)){
            preparedStatement.setInt(1, a.getOrderId());
            preparedStatement.setInt(2, a.getMenuElementId());
            preparedStatement.setInt(3, a.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public OrderDetails get(int id) {
        String select = "select * from orderdetails where orderId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return new OrderDetails(id, resultSet.getInt(2), resultSet.getInt(3));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return new OrderDetails(-1, -1, -1);
    }

    public ArrayList<OrderDetails> getAllByOrdersId(int id) {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        String select = "select * from orderdetails where orderId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(select);) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orderDetails.add(new OrderDetails(id, resultSet.getInt(2), resultSet.getInt(3)));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return orderDetails;
    }

    @Override
    public ArrayList<OrderDetails> getAll() {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        String select = "select * from orderdetails;";
        try(Statement preparedStatement = getConnection().createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(select)) {
            while (resultSet.next()){
                orderDetails.add(new OrderDetails(resultSet.getInt(1),
                                resultSet.getInt(2), resultSet.getInt(3)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return orderDetails;
    }
}
