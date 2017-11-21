package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.MailBoxModel;
import com.codecool.dream_is_green.model.PreUserModel;
import com.codecool.dream_is_green.model.StudentModel;
import com.codecool.dream_is_green.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MailBoxDao extends AbstractDAO<MailBoxModel> {

    public void loadReadMail(Integer userId, Integer status) {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = String.format("SELECT * from MailBox join UsersTable on " +
                                         "MailBox.user_id_sender = UsersTable.user_id where " +
                                         "MailBox.user_id_addressee == %d and MailBox.read == %d;", userId, status);
            ResultSet result = stat.executeQuery(query);
            String content, header, name, surname, email, login, password;
            int userID;

            while(result.next()) {

                content = result.getString("content");
                header = result.getString("header");
                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");

                MailBoxModel mailBoxModel = new MailBoxModel(userID, name, surname, email, login, password, header, content);
                this.addObject(mailBoxModel);
            }
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void insertStudent(String content, String header, Integer idAddressee, Integer idSender) {

        Connection connection;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            String query1 = String.format("INSERT INTO MailBox (content, header, user_id_addressee," +
                    " user_id_sender) VALUES ('%s', '%s'," +
                    "'%d', '%d');", content, header, idAddressee, idSender);

            statement.executeUpdate(query1);
            connection.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStudentId(String login) {

        Connection connection;
        Statement statement;
        int userID = 0;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            String query = "SELECT user_id FROM UsersTable WHERE login = '" + login + "';";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                userID = result.getInt("user_id");
            }

            result.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userID;
    }

    public StudentModel getStudent(String login) {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = "SELECT * FROM StudentsTable JOIN UsersTable" +
                    " ON UsersTable.user_id = StudentsTable.user_id WHERE login = '" + login + "'";
            ResultSet result = stat.executeQuery(query);
            String name, surname, email, user_login, password, className;
            int userID, studentExp;
            StudentModel student = null;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                user_login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                className = result.getString("class_name");
                studentExp = result.getInt("experience");

                student = new StudentModel(userID, name, surname, email, user_login, password, className, studentExp);
            }
            result.close();
            stat.close();

            return student;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

