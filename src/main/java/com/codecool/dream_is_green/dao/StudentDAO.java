package com.codecool.dream_is_green.dao;
import java.sql.*;

import com.codecool.dream_is_green.model.StudentModel;

public class StudentDAO extends AbstractDAO<StudentModel> {

    public void loadStudents() {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();


            String query = "SELECT * FROM StudentsTable JOIN UsersTable ON UsersTable.user_id = StudentsTable.user_id";
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

                StudentModel student = new StudentModel(userID, name, surname, email, login, password, className);
                this.addObject(student);
            }
            result.close();
            stat.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertStudent(String name, String surname, String email,
                             String login, String password, String className) {

        Connection conn;
        Statement stat;

        try {
            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();
            conn.setAutoCommit(false);

            String statement1 = String.format("INSERT INTO UsersTable (name, surname, email, login, password, user_type) VALUES ('%s', '%s', '%s', '%s', '%s', 'student');", name, surname, email, login, password);

            stat.executeUpdate(statement1);

            int userId = this.getMentorId(login);

            String statement2 = String.format("INSERT INTO StudentsTable (user_id, experience, level_name, class_name) VALUES (%d, '%d', 'noob');", userId);

            stat.executeUpdate(statement2);

            stat.close();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
