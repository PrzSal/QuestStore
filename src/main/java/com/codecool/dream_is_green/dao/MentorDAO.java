package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.MentorModel;

import java.sql.*;

public class MentorDAO extends AbstractDAO<MentorModel> {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:quest_store.db";

    public void loadMentors() {

        Connection conn;
        Statement stat;

        try {

            Class.forName(MentorDAO.DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();


            String query = "SELECT * FROM UsersTable JOIN MentorsTable ON UsersTable.user_id = MentorsTable.user_id";
            ResultSet result = stat.executeQuery(query);
            String name, surname, email, login, password;
            int userID, classID;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                classID = result.getInt("class_id");

                MentorModel mentor = new MentorModel(userID, name, surname, email, login, password, classID);
                this.addObject(mentor);
                result.close();
                stat.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
