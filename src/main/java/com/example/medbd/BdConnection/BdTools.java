package com.example.medbd.BdConnection;

import java.sql.*;

public class BdTools {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:postgresql://localhost:5432/regbd";
            String user = "postgres";
            String password = "123";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

}