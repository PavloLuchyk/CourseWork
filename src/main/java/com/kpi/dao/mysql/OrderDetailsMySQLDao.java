package com.kpi.dao.mysql;

import com.kpi.dao.OrderDetailsDao;
import com.kpi.models.OrderDetails;

import java.sql.*;
import java.util.ArrayList;

public class OrderDetailsMySQLDao implements OrderDetailsDao {
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
        orderQuery(a, insert);
    }


    private void orderQuery(OrderDetails a, String sql) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
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
    public void deleteByMenuElementId(int id){
        String delete ="delete from orderdetails where menuelementId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(delete)){
            preparedStatement.setInt(1, id);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public void update(OrderDetails a) {
        String update ="update orderdetails set orderId = ?, menuelementId = ?, quantity = ?;";
        orderQuery(a, update);
    }

    @Override
    public OrderDetails get(int id) {
        String select = "select * from orderdetails where orderId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new OrderDetails(id, resultSet.getInt(2), resultSet.getInt(3));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return new OrderDetails(-1, -1, -1);
    }

    @Override
    public ArrayList<OrderDetails> getAllByOrdersId(int id) {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        String select = "select * from orderdetails where orderId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(select)) {
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
