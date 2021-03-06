package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.ClassModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void insertClass(ClassModel classModel) {

        Connection conn;
        Statement stat;
        String className = classModel.getClassName();
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

    public void deleteClass(String className) {

        Connection connection;
        String classNameRep = className.replaceAll("\\s+", "\n");

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String deleteTableSQL = "DELETE FROM ClassTable WHERE class_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteTableSQL);
            preparedStatement.setString(1, classNameRep);

            preparedStatement .executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
