package com.bona.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Database credentials and URL
    private static final String URL = "jdbc:mysql://localhost:3306/roseridedb"; // Replace with your DB URL
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "Bona.1993"; // Replace with your DB password

    static {
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Replace with the driver for your database
        } catch (ClassNotFoundException e) {
            System.err.println("Database Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to get a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

