package com.kpi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AbstractDAO<T> {
    Connection getConnection() throws SQLException, ClassNotFoundException;
    void add(T a);
    void delete(int id);
    void update(T a);
    T get(int id);
    ArrayList<T> getAll();
}
