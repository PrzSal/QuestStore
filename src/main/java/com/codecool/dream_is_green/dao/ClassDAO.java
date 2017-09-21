package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.ClassModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClassDAO extends AbstractDAO<ClassModel> {

    public void loadClasses() {

        Connection conn;
        Statement stat;

        try {
            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();

            String query = "SELECT * FROM ClassTable ";
            ResultSet result = stat.executeQuery(query);

            while (result.next()) {

                String className = result.getString("class_name");

                ClassModel newClass = new ClassModel(className);
                this.addObject(newClass);
            }
            result.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertClass(String className) {

        Connection conn;
        Statement stat;

        try {
            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();
            conn.setAutoCommit(false);

            String statement = String.format("INSERT INTO ClassTable (class_name) VALUES ('%s');", className);
            stat.executeUpdate(statement);

            stat.close();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
