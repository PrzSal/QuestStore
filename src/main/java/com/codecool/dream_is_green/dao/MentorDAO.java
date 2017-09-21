package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.MentorModel;

import java.sql.*;

public class MentorDAO extends AbstractDAO<MentorModel> {

    public void loadMentors() {

        Connection conn;
        Statement stat;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
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

                System.out.println("dupa" + surname);
                MentorModel mentor = new MentorModel(userID, name, surname, email, login, password, className);
                this.addObject(mentor);
            }
            result.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMentor(String name, String surname, String email,
                          String login, String password, String className) {

        Connection conn;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:quest_store.db");

            String statement1 = "INSERT INTO UsersTable (user_id, name, surname, email, login, password, user_type) " +
                    "VALUES (null, ?, ?, ?, ?, ?, 'mentor');";
            String statement2 = "INSERT INTO MentorsTable (user_id, class_name) " +
                    "VALUES (null, ?);";
            PreparedStatement prepStmt1 = conn.prepareStatement(statement1);
            PreparedStatement prepStmt2 = conn.prepareStatement(statement2);

            prepStmt1.setString(1, name);
            prepStmt1.setString(2, surname);
            prepStmt1.setString(3, email);
            prepStmt1.setString(4, login);
            prepStmt1.setString(5, password);

            prepStmt2.setString(1, className);
            prepStmt1.execute();
            prepStmt2.execute();
            prepStmt1.close();
            prepStmt2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
