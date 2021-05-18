package com.kpi.dao;

import com.kpi.models.MenuElement;
import com.kpi.models.Order;
import com.kpi.models.User;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao implements DAO<Order>{
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorder",
                "root",
                "root");
    }

    @Override
    public void add(Order a) {
        String insert = "insert into orders(userId, ordertime) VALUES (?,?);";
        try(Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(insert)){
            preparedStatement.setInt(1, a.getUserId());
            preparedStatement.setTimestamp(2, a.getTime());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Exception! " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String delete = "delete from orders where orderId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(delete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Exception! " + e.getMessage());
        }

    }

    @Override
    public void update(Order a) {
        String update = "update orders set userId = ?, orderTime = ?, orderDone = ? where orderId = ?";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(update)) {
            preparedStatement.setInt(1, a.getUserId());
            preparedStatement.setTimestamp(2, a.getTime());
            preparedStatement.setBoolean(3, a.isStatus());
            preparedStatement.setInt(4, a.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    public void updateOrderStatus(int id){
        String update = "update orders set  orderDone = TRUE where orderId = ?";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(update)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public Order get(int id) {
        String select = "select * from orders where orderId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(select)){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if(rs.next()){
                   return new Order(rs.getInt(1), rs.getInt(2),
                           rs.getTimestamp(3), rs.getBoolean(4));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return new Order(-1, -1, new Timestamp(0L), false);
    }

    public ArrayList<Order> getUndoneOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        String select = "select * from orders where orderDone = FALSE;";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(select);
             ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(rs.getInt(1),
                            rs.getInt(2),
                            rs.getTimestamp(3),
                            rs.getBoolean(4)));
                }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return orders;
    }

    public ArrayList<Order> getByUserId(int id) {
        ArrayList<Order> orders = new ArrayList<>();
        String select = "select * from orders where userId = ?;";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(select);){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(rs.getInt(1),
                            rs.getInt(2),
                            rs.getTimestamp(3),
                            rs.getBoolean(4)));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return orders;
    }

    @Override
    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders;")){
            while (resultSet.next()){
                orders.add(new Order(resultSet.getInt(1),
                                    resultSet.getInt(2),
                                    resultSet.getTimestamp(3),
                                    resultSet.getBoolean(4)));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        return orders;
    }
}
