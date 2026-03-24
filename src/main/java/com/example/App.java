package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Main application entry point.
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    // Hardcoded credentials (Security issue)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";

    public static void main(String[] args) {

        LOGGER.info("Starting application with user input: " + args[0]); // Unsafe logging

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();

            // SQL Injection vulnerability
            String query = "SELECT * FROM users WHERE name = '" + args[0] + "'";
            stmt.executeQuery(query);

        } catch (Exception e) {
            // Poor exception handling (just printing stack trace)
            e.printStackTrace();
        }

        // Null pointer risk
        String str = null;
        if (str.equals("test")) {
            LOGGER.info("This will throw NullPointerException");
        }

        // Dead code / always true condition
        if (true) {
            LOGGER.info("This condition is always true");
        }

        // Resource leak (connection not closed)

        LOGGER.info("Hello World!");
    }
}
