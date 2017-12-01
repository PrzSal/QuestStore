package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MailBoxDao extends AbstractDAO<MailBoxModel> {

    public void loadReadMail(Integer userId, Integer status) {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = String.format("SELECT * from MailBox join UsersTable on " +
                                         "MailBox.user_id_sender = UsersTable.user_id where " +
                                         "MailBox.user_id_recipient == %d and MailBox.read == %d;", userId, status);
            ResultSet result = stat.executeQuery(query);
            String content, header, name, surname, email, login, password, userType;
            int read, userID, userIDSender, react, mailId;

            while(result.next()) {

                content = result.getString("content");
                header = result.getString("header");
                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                read = result.getInt("read");
                userID = result.getInt("user_id_recipient");
                userIDSender = result.getInt("user_id_sender");
                react = result.getInt("react");
                mailId = result.getInt("id");
                userType = result.getString("user_type");
                MailBoxModel mailBoxModel = new MailBoxModel(userID, name, surname, email, login, password, header, content, userIDSender, read, react, mailId, userType);
                this.addObject(mailBoxModel);
            }
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadReadMailAll() {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = String.format("SELECT * from MailBox join UsersTable on " +
                    "MailBox.user_id_sender = UsersTable.user_id");
            ResultSet result = stat.executeQuery(query);
            String content, header, name, surname, email, login, password, userType;
            int read, userID, userIDSender, react, mailId;

            while(result.next()) {

                content = result.getString("content");
                header = result.getString("header");
                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                read = result.getInt("read");
                userID = result.getInt("user_id_recipient");
                userIDSender = result.getInt("user_id_sender");
                react = result.getInt("react");
                mailId = result.getInt("id");
                userType = result.getString("user_type");
                MailBoxModel mailBoxModel = new MailBoxModel(userID, name, surname, email, login, password, header, content, userIDSender, read, react, mailId, userType);
                this.addObject(mailBoxModel);
            }
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateReact(Integer mailId) {

        Connection connection;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepStmt;
            String statement = "UPDATE MailBox SET react = 0 WHERE id == ? ;";
            prepStmt = connection.prepareStatement(statement);
            prepStmt.setInt(1, mailId);
            prepStmt.executeUpdate();
            connection.commit();
            prepStmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertMail(PreMailModel preMailModel) {

        Connection connection;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            String query = String.format("INSERT INTO MailBox (content, header, user_id_recipient," +
                    " user_id_sender) VALUES ('%s', '%s'," +
                    "'%d', '%d');", preMailModel.getContent(), preMailModel.getHeader(), preMailModel.getUserIdAddressee(), preMailModel.getUserIdSender());

            statement.executeUpdate(query);
            connection.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

