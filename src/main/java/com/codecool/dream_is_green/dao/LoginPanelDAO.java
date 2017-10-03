package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.LoginDetailsModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPanelDAO {

    public LoginDetailsModel getUserDetails(String login) {

        Connection conn;
        Statement stat;

        try {

            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();

            String query = "SELECT user_type, password FROM UsersTable WHERE login = '" + login + "';" ;
            ResultSet result = stat.executeQuery(query);
            String userType = "";
            String password = "";

            while (result.next()) {
                userType = result.getString("user_type");
                password = result.getString("user_type");
            }

            LoginDetailsModel loginDetails = new LoginDetailsModel(userType, password);

            result.close();
            stat.close();

            return  loginDetails;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
