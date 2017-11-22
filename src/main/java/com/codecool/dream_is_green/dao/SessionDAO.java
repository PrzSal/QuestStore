package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.QuestModel;
import com.codecool.dream_is_green.model.SessionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SessionDAO extends AbstractDAO<QuestModel> {

    public void insertSession(SessionModel newSession) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String sessionId = newSession.getSessionId();
            String userName = newSession.getUserName();
            String userType = newSession.getUserType();

            String insertTableSQL = "INSERT INTO SessionTable (session_id, user_name, user_type) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, sessionId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, userType);

            preparedStatement .executeUpdate();

            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void deleteSession(String sessionId) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String insertTableSQL = "DELETE FROM SessionTable WHERE session_id = ?;";
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

    public SessionModel getSession(String sessionId) {

        SessionModel session = null;
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT * FROM SessionTable WHERE session_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, sessionId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userSessionId = rs.getString("session_id");
                String userName = rs.getString("user_name");
                String userType = rs.getString("user_type");

                session = new SessionModel(userSessionId, userName, userType);
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return session;
    }

}