package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.QuestModel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class SessionDAO extends AbstractDAO<QuestModel> {

    public static void insertSession(String sessionId, String userName) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String insertTableSQL = "INSERT INTO SessionTable (session_id, user_name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, sessionId);
            preparedStatement.setString(2, userName);

            preparedStatement .executeUpdate();

            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void deleteSession(String sessionId) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String insertTableSQL = "DELETE FROM SessionTable WHERE session_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, sessionId);

            preparedStatement .executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static Map<String, String> getSession() {

        Map<String, String> sessionsMap = new HashMap<>();
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT * FROM SessionTable;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String sessionId = rs.getString("session_id");
                String userName = rs.getString("user_name");

                sessionsMap.put(sessionId, userName);
            }
            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return sessionsMap;
    }

}