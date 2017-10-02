package com.codecool.dream_is_green.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPanelDAO {

    public String[] getUserDetails(String login) {

        Connection conn;
        Statement stat;

        try {

            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();

            String query = "SELECT user_type, password FROM UsersTable WHERE login = '" + login + "';" ;
            ResultSet result = stat.executeQuery(query);
            String user_type = "";
            String password = "";
            String[] userLoginDetails = {"", ""};

            while (result.next()) {
                user_type = result.getString("user_type");
                password = result.getString("user_type");
            }

            userLoginDetails[0] = user_type;
            userLoginDetails[1] = password;

            result.close();
            stat.close();

            return  userLoginDetails;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
