package com.codecool.dream_is_green.dao;
import java.sql.*;

import com.codecool.dream_is_green.model.StudentModel;

public class StudentDAO extends AbstractDAO<StudentModel> {

    public void loadStudents() {

        Connection conn;
        Statement stat;

        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
            stat = conn.createStatement();


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
}
