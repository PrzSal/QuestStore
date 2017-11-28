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
            Integer userId = newSession.getUserId();
            String userName = newSession.getUserName();
            String userType = newSession.getUserType();
            Integer teamId = newSession.getTeamId();

            String insertTableSQL = "INSERT INTO SessionTable (session_id, user_id, user_name, user_type, team_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, sessionId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, userType);
            preparedStatement.setInt(5, teamId);

            preparedStatement .executeUpdate();

            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
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
                Integer userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String userType = rs.getString("user_type");
                Integer teamId = rs.getInt("team_id");

                session = new SessionModel(userSessionId, userId, userName, userType);
                session.setTeamId(teamId);
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return session;
    }

}