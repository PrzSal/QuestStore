package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.MentorModel;

import java.sql.*;

public class MentorDAO extends AbstractDAO<MentorModel> {

    public void loadMentors() {

        Connection conn;
        Statement stat;

        try {

            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();

            String query = "SELECT * FROM MentorsTable JOIN UsersTable ON UsersTable.user_id = MentorsTable.user_id";
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

    public void insertMentor(String name, String surname, String email,
                          String login, String password, String className) {

        Connection conn;
        Statement stat;

        try {
            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();

            String statement1 = String.format("INSERT INTO UsersTable (name, surname, email, login, password, user_type) VALUES (%s, %s, %s, %s, %s, %s);", name, surname, email, login, password, "mentor");

            stat.executeUpdate(statement1);

            int userId = this.getMentorId(login);

            String statement2 = String.format("INSERT INTO MentorsTable (user_id, class_name) VALUES (%d, %s);", userId, className);

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

            String statement1 = "DELETE FROM UsersTable WHERE user_id = ?";
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
}



