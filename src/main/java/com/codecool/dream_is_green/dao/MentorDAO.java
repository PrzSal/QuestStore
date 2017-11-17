package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.MentorModel;
import com.codecool.dream_is_green.model.PreUserModel;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MentorDAO extends AbstractDAO<MentorModel> {

    public MentorModel getMentorByID(int userID, MentorDAO mentorDao) {

        for (MentorModel mentor : mentorDao.getObjectList()) {
            if (mentor.getUserID() == userID) {
                return mentor;
            }
        }
        return null;
    }

    public void loadMentors() {

        Connection conn;
        Statement stat;

        try {

            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();


            String query = "SELECT * FROM MentorsTable JOIN UsersTable" +
                           " ON UsersTable.user_id = MentorsTable.user_id";
            ResultSet result = stat.executeQuery(query);
            String name, surname, email, login, password, className;
            int userID;

            while (result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                className = result.getString("class_name");

                MentorModel mentor = new MentorModel(userID, name, surname, email, login, password, className);
                this.addObject(mentor);
            }
            result.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertMentor(PreUserModel preMentorModel) {

        Connection conn;
        Statement stat;

        String name = preMentorModel.getName();
        String surname = preMentorModel.getSurname();
        String email = preMentorModel.getEmail();
        String login = preMentorModel.getLogin();
        String password = preMentorModel.getPassword();
        String className = preMentorModel.getClassName();

        try {
            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();
            conn.setAutoCommit(false);

            String statement1 = String.format("INSERT INTO UsersTable (name, surname, email, login, password, user_type) VALUES ('%s', '%s', '%s', '%s', '%s', 'mentor');", name, surname, email, login, password);

            stat.executeUpdate(statement1);

            int userId = this.getMentorId(login);

            String statement2 = String.format("INSERT INTO MentorsTable (user_id, class_name) VALUES (%d, '%s');", userId, className);

            stat.executeUpdate(statement2);

            stat.close();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMentor(int id) {

        Connection conn;

        try {
            conn = DatabaseConnection.getConnection();

            String statement1 = "DELETE FROM UsersTable WHERE user_id = ? AND user_type = 'mentor'";
            String statement2 = "DELETE FROM MentorsTable WHERE user_id = ?";
            PreparedStatement prepStmt1 = conn.prepareStatement(statement1);
            PreparedStatement prepStmt2 = conn.prepareStatement(statement2);

            prepStmt1.setInt(1, id);
            prepStmt2.setInt(1, id);
            prepStmt1.execute();
            prepStmt2.execute();
            prepStmt1.close();
            prepStmt2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMentorId(String login) {

        Connection conn;
        Statement stat;
        int userID = 0;

        try {
            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();

            String query = "SELECT user_id FROM UsersTable WHERE login = '" + login + "';";
            ResultSet result = stat.executeQuery(query);

            while (result.next()) {
                userID = result.getInt("user_id");
            }

            result.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userID;
    }

    public void updateMentor(String phrase, int userID, String column, String table) {

        Connection conn;
        Statement stat;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            stat = conn.createStatement();
            if (column.equals("class_name")) {
                column = "class_name";

            } else if (column.equals("email")) {
                column = "email";

            }
            String statement = String.format("UPDATE %s set '%s' = '%s' where user_id=%d;", table, column, phrase, userID);
            stat.executeUpdate(statement);
            conn.commit();

            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            System.exit(0);
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
            System.exit(0);
        }
        return userType;
    }
}