package com.codecool.dream_is_green.dao;

import java.sql.PreparedStatement;
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
            String name, surname, email, login, password, className, voted;
            int userID, studentExp, teamID;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                voted = result.getString("voted");
                userID = result.getInt("user_id");
                className = result.getString("class_name");
                studentExp = result.getInt("experience");
                teamID = result.getInt("team_id");


                StudentModel student = new StudentModel(userID, name, surname, email,
                                                        login, password, className, studentExp);
                student.setVoted(voted);
                student.setTeamId(teamID);
                this.addObject(student);
            }
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Integer userId, String column, String content) {

        Connection connection;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepStmt;
            String statement = "UPDATE StudentsTable SET voted = ? WHERE user_id == ? ;";
            if (column.equals("team_id")) {
                statement = "UPDATE StudentsTable SET team_id = ? WHERE user_id == ? ;";
                prepStmt = connection.prepareStatement(statement);
                prepStmt.setInt(1, Integer.valueOf(content));
                prepStmt.setInt(2, userId);
            } else {
                prepStmt = connection.prepareStatement(statement);
                prepStmt.setString(1, content);
                prepStmt.setInt(2, userId);
            }
            prepStmt.executeUpdate();
            connection.commit();
            prepStmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudentModel(PreUserModel studentModel, String table, int studentID, int teamID) {

        Connection connection;
        String name = studentModel.getName();
        String surname = studentModel.getSurname();
        String email = studentModel.getEmail();
        String login = studentModel.getLogin();
        String password = studentModel.getPassword();
        String className = studentModel.getClassName();

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepStmt;
            String statement = "UPDATE UsersTable SET name = ?, surname = ?, email = ?," +
                    " login = ?, password = ? WHERE user_id = ? ;";


            if (table.equals("students_table")) {
                statement = "UPDATE StudentsTable SET class_name = ?, team_id = ? WHERE user_id = ?";
                prepStmt = connection.prepareStatement(statement);
                prepStmt.setString(1, className);
                prepStmt.setInt(2, teamID);
                prepStmt.setInt(3, studentID);
            } else {
                prepStmt = connection.prepareStatement(statement);
                prepStmt.setString(1, name);
                prepStmt.setString(2, surname);
                prepStmt.setString(3, email);
                prepStmt.setString(4, login);
                prepStmt.setString(5, password);
                prepStmt.setInt(6, studentID);
            }
            prepStmt.executeUpdate();
            connection.commit();
            prepStmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertStudent(PreUserModel preStudentModel) {

        Connection connection;

        String name = preStudentModel.getName();
        String surname = preStudentModel.getSurname();
        String email = preStudentModel.getEmail();
        String login = preStudentModel.getLogin();
        String password = preStudentModel.getPassword();
        String className = preStudentModel.getClassName();
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String statement1 = "INSERT INTO UsersTable (name, surname, email," +
                         " login, password, user_type) VALUES (?, ?, ?, ?, ?, 'student')";
            PreparedStatement prepStmt1 = connection.prepareStatement(statement1);

            prepStmt1.setString(1, name);
            prepStmt1.setString(2, surname);
            prepStmt1.setString(3, email);
            prepStmt1.setString(4, login);
            prepStmt1.setString(5, password);

            prepStmt1.execute();
            connection.commit();
            prepStmt1.close();

            int studentID = getStudentId(login);
            String statement2 = "INSERT INTO StudentsTable (user_id, class_name) VALUES (?, ?);";
            PreparedStatement prepStmt2 = connection.prepareStatement(statement2);

            prepStmt2.setInt(1, studentID);
            prepStmt2.setString(2, className);

            prepStmt2.execute();
            connection.commit();
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

    public StudentModel getStudent(Integer userId) {

        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = "SELECT * FROM StudentsTable JOIN UsersTable" +
                    " ON UsersTable.user_id = StudentsTable.user_id WHERE UsersTable.user_id = '" + userId + "'";
            ResultSet result = stat.executeQuery(query);
            String name, surname, email, user_login, password, className, voted;
            int userID, studentExp, teamId;
            StudentModel student = null;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                user_login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                className = result.getString("class_name");
                voted = result.getString("voted");
                studentExp = result.getInt("experience");
                teamId = result.getInt("team_id");
                student = new StudentModel(userID, name, surname, email, user_login, password, className, studentExp);
                student.setTeamId(teamId);
                student.setVoted(voted);
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
