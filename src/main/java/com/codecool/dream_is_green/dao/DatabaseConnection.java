package com.codecool.dream_is_green.dao;

import java.sql.*;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return connection;

    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}