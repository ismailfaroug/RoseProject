package com.bona.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
