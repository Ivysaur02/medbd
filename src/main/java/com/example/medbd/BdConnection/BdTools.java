package com.example.medbd.BdConnection;

import com.example.medbd.models.Person;

import java.sql.*;

public class BdTools {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (connection == null) {
            String url = "jdbc:postgresql://localhost:5432/regbd";
            String user = "postgres";
            String password = "123";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public static void deleteUser(Person user, String table) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = getConnection();
        String query = "DELETE FROM " + table + " WHERE id=?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, Integer.parseInt(user.getId()));
        stmt.executeUpdate();
    }

}