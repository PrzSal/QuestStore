package com.codecool.dream_is_green.dao;
import java.sql.*;

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
            int userID;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                className = result.getString("class_name");

                StudentModel student = new StudentModel(userID, name, surname, email,
                                                        login, password, className);
                this.addObject(student);
            }
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertStudent(String name, String surname, String email,
                             String login, String password, String className) {

        Connection connection;
        Statement statement;

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

            String query2 = String.format("INSERT INTO StudentsTable (user_id, level_name, class_name)" +
                                          " VALUES (%d, 'noob', '%s');", userId, className);

            statement.executeUpdate(query2);

            statement.close();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(int id) {
//        Method ready to use, pass test, but not implemented in controller
        Connection connection;

            try {
            connection = DatabaseConnection.getConnection();

            String query1 = "DELETE FROM UsersTable WHERE user_id = ? AND user_type = 'student'";
            String query2 = "DELETE FROM StudentsTable WHERE user_id = ?";
            PreparedStatement prepStmt1 = connection.prepareStatement(query1);
            PreparedStatement prepStmt2 = connection.prepareStatement(query2);

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
}
