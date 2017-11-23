package com.codecool.dream_is_green.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.codecool.dream_is_green.model.PreUserModel;
import com.codecool.dream_is_green.model.StudentModel;

public class StudentDAO extends AbstractDAO<StudentModel> {

    public void loadStudents() {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = "SELECT * FROM StudentsTable JOIN UsersTable" +
                           " ON UsersTable.user_id = StudentsTable.user_id";
            ResultSet result = stat.executeQuery(query);
            String name, surname, email, login, password, className;
            int userID, studentExp;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                className = result.getString("class_name");
                studentExp = result.getInt("experience");


                StudentModel student = new StudentModel(userID, name, surname, email,
                                                        login, password, className, studentExp);
                this.addObject(student);
            }
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(PreUserModel preStudentModel) {

        Connection connection;
        Statement statement;

        String name = preStudentModel.getName();
        String surname = preStudentModel.getSurname();
        String email = preStudentModel.getEmail();
        String login = preStudentModel.getLogin();
        String password = preStudentModel.getPassword();
        String className = preStudentModel.getClassName();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            String query1 = String.format("INSERT INTO UsersTable (name, surname, email," +
                                          " login, password, user_type) VALUES ('%s', '%s'," +
                                          "'%s', '%s', '%s', 'student');", name, surname, email, login, password);

            statement.executeUpdate(query1);
            connection.commit();
            int userId = this.getStudentId(login);

            String query2 = String.format("INSERT INTO StudentsTable (user_id, class_name)" +
                                          " VALUES (%d, '%s');", userId, className);

            statement.executeUpdate(query2);

            statement.close();
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

    public StudentModel getStudent(Integer userId) {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = "SELECT * FROM StudentsTable JOIN UsersTable" +
                    " ON UsersTable.user_id = StudentsTable.user_id WHERE UsersTable.user_id = '" + userId + "'";
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
