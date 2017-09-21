package com.codecool.dream_is_green.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSingletonDAO {

    private static ConnectionSingletonDAO instance = null;
    private Connection conn;
    private Statement stat;

    private ConnectionSingletonDAO() {

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
            stat = conn.createStatement();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeConnection() {
        try {

            instance.stat.close();
            instance.conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static ConnectionSingletonDAO getInstance() {

        if(instance == null) {
            instance = new ConnectionSingletonDAO();
        }
        return instance;
    }
}
