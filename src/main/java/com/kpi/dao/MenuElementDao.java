package com.kpi.dao;

import com.kpi.models.MenuElement;

import java.sql.*;
import java.util.ArrayList;

public class MenuElementDao implements DAO<MenuElement> {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorder",
                "root",
                "root");
    }

    @Override
    public void add(MenuElement a) {
        String alter = "alter table menuelements auto_increment = 1;";
        String insert = "insert into menuelements(name, description, ingredients, price, image) values (?,?,?,?,?);";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(insert);
             Statement statement = con.prepareStatement(alter)) {
            preparedStatement.setString(1, a.getName());
            preparedStatement.setString(2, a.getDescription());
            preparedStatement.setString(3, a.getIngredients());
            preparedStatement.setDouble(4, a.getPrice());
            preparedStatement.setBytes(5, a.getImage());
            statement.executeUpdate(alter);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String delete = "delete from menuelements where idmenuElement = ?;";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(delete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public void update(MenuElement a) {
        String update = "update menuelements set name=?, decription=?, ingredients=?, price=? where idmenuElement = ?;";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(update)) {
            preparedStatement.setString(1, a.getName());
            preparedStatement.setString(2, a.getDescription());
            preparedStatement.setString(3, a.getIngredients());
            preparedStatement.setDouble(4, a.getPrice());
            preparedStatement.setInt(5, a.getElementId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }

    @Override
    public MenuElement get(int id) {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from menuelements where (idmenuElement =" + id + ");")) {
            rs.next();
            return new MenuElement(
                     rs.getInt(1),
                     rs.getString(2),
                     rs.getString(3),
                     rs.getString(4),
                     rs.getDouble(5),
                     rs.getBytes(6));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
            return new MenuElement(-1, "None", "None", "None", -1);
        }
    }

    @Override
    public ArrayList<MenuElement> getAll() {
            try (Connection con = getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("select * from menuelements;")) {
                ArrayList<MenuElement> menuElements = new ArrayList<>();
                while (rs.next()) {
                    menuElements.add(new MenuElement(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getBytes(6)));
                }
                return menuElements;
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Exception!" + e.getMessage());
                return new ArrayList<>();
            }
    }
}
