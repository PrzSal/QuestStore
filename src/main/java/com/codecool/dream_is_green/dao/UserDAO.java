package com.codecool.dream_is_green.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public Integer getUserId(String userName) {

        Connection connection;
        Integer userId = 0;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT user_id FROM UsersTable WHERE login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, userName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("user_id");

                userId = id;
            }
            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return userId;
    }

    public String getUserPassword(String userName) {

        Connection connection;
        String userPassword = "";

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT password FROM UsersTable WHERE login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, userName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String password = rs.getString("password");

                userPassword = password;
            }
            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return userPassword;
    }

    public String getUserType(String userName) {

        Connection connection;
        String userType = "";

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT user_type FROM UsersTable WHERE login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, userName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String type = rs.getString("user_type");

                userType = type;
            }
            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return userType;
    }
}
