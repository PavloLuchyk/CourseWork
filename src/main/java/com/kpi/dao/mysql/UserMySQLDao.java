package com.kpi.dao.mysql;

import com.kpi.dao.UserDao;
import com.kpi.models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserMySQLDao implements UserDao {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorder",
                "root",
                "root");
    }

    @Override
    public void add(User a) {
        String insert = "insert into user(username, email, passwordHash, salt, phoneNumber) VALUES(?,?,?,?,?);";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, a.getUsername());
            preparedStatement.setString(2, a.getEmail());
            preparedStatement.setBytes(3, a.getPasswordHash());
            preparedStatement.setBytes(4, a.getPasswordSalt());
            preparedStatement.setString(5, a.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(int id) {
        String delete = "delete from user where userId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(delete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(User a) {
        String update ="update user set username = ?, email = ?, passwordHash = ?, salt = ?, phoneNumber = ?, admin = ?, address = ? where userId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(update)) {
            preparedStatement.setString(1, a.getUsername());
            preparedStatement.setString(2, a.getEmail());
            preparedStatement.setBytes(3, a.getPasswordHash());
            preparedStatement.setBytes(4, a.getPasswordSalt());
            preparedStatement.setString(5, a.getPhoneNumber());
            preparedStatement.setBoolean(6, a.isAdmin());
            preparedStatement.setString(7, a.getAddress());
            preparedStatement.setInt(8, a.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatePassword(byte[] passwordHash, byte[] passwordSalt, int userId) {
        String update ="update user set  passwordHash = ?, salt = ? where userId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(update)) {
            preparedStatement.setBytes(1, passwordHash);
            preparedStatement.setBytes(2, passwordSalt);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateUserDetails(User a){
        String update ="update user set username = ?, email = ?, phoneNumber = ?, address = ? where userId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(update)) {
            preparedStatement.setString(1, a.getUsername());
            preparedStatement.setString(2, a.getEmail());
            preparedStatement.setString(3, a.getPhoneNumber());
            preparedStatement.setString(4, a.getAddress());
            preparedStatement.setInt(5, a.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateAdmin(int id, boolean status) {
        String update ="update user set admin = ? where userId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(update)) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User get(int id) {
        String select = "select * from user where userId = ?;";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getBytes(4),
                            resultSet.getBytes(5),
                            resultSet.getString(6),
                            resultSet.getTimestamp(7),
                            resultSet.getBoolean(8),
                            resultSet.getString(9)
                    );
                }

            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return new User("","",new byte[1], new byte[1], "", new Timestamp(-1));
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        String select = "select * from user;";
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select)) {
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getBytes(4),
                        resultSet.getBytes(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7),
                        resultSet.getBoolean(8),
                        resultSet.getString(9)
                ));
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Exception! " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }
}
