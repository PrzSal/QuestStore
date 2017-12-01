package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class WalletDAO extends AbstractDAO<WalletDAO> {

    public LinkedList<CoolCoinsModel> getStudentCoolCoins() {

        LinkedList<CoolCoinsModel> studentsWithCoolcoins = new LinkedList<>();
        Connection connection;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            String query = "SELECT * FROM WalletTable";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Integer coolCoins = resultSet.getInt("coolcoins");
                Integer studentID = resultSet.getInt("user_id");
                CoolCoinsModel studentCoolCoins = new CoolCoinsModel(coolCoins, studentID);
                studentsWithCoolcoins.add(studentCoolCoins);
            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsWithCoolcoins;
    }

    public void loadCoolcoinsToWallet(StudentModel student) {

        Connection connection;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            String query = "SELECT * FROM WalletTable WHERE user_id = '" + student.getUserID() + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Integer coolcoins = resultSet.getInt("coolcoins");

                student.getWallet().setCoolCoins(coolcoins);

            }
            resultSet.close();
            statement.close();

         } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadArtifactsToWallet(StudentModel student) {

        Connection connection;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            String query = "SELECT * FROM StudentsWithArtifacts WHERE user_id = '" + student.getUserID() + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String artifactName = resultSet.getString("artifact_name");
                Integer price = resultSet.getInt("price");
                String artifactCategory = resultSet.getString("artifact_category");
                Integer state = resultSet.getInt("state");

                ArtifactCategoryModel artifactCategoryModel = new ArtifactCategoryModel(artifactCategory);
                ArtifactModel artifactModel = new ArtifactModel(artifactName, price, artifactCategoryModel);
                artifactModel.setIsUsed(state);

                student.getWallet().addBoughtArtifact(artifactModel);


            }
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<ArtifactModel> getStudentArtifacts(Integer userId) {

        LinkedList<ArtifactModel> studentArtifacts = new LinkedList<>();
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT * FROM StudentsWithArtifacts WHERE user_Id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("artifact_name");
                Integer price = rs.getInt("price");
                String category = rs.getString("artifact_category");
                Integer state = rs.getInt("state");

                ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(category);
                ArtifactModel artifact = new ArtifactModel(title, price, artifactCategory);
                artifact.setIsUsed(state);
                studentArtifacts.add(artifact);
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return studentArtifacts;
    }

    public Integer getStudentCoolCoins(Integer userId) {

        Integer coolCoins = null;
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT coolcoins FROM WalletTable WHERE user_Id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer studentCoins = rs.getInt("coolcoins");
                coolCoins = studentCoins;
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return coolCoins;
    }

    public LinkedList<ArtifactModel> getStudentUnUsedArtifacts(Integer userId) {

        LinkedList<ArtifactModel> studentArtifacts = new LinkedList<>();
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT * FROM StudentsWithArtifacts WHERE user_Id = ? AND state = 0;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("artifact_name");
                Integer price = rs.getInt("price");
                String category = rs.getString("artifact_category");
                Integer state = rs.getInt("state");

                ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(category);
                ArtifactModel artifact = new ArtifactModel(title, price, artifactCategory);
                artifact.setIsUsed(state);
                studentArtifacts.add(artifact);
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return studentArtifacts;
    }

    public void setArtifactOnUsed(String artifactTitle, Integer userId) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "UPDATE StudentsWithArtifacts SET state = 1 WHERE artifact_name = ? AND user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, artifactTitle);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void updateStudentCoolCoins(Integer coolCoins, Integer userId) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "UPDATE WalletTable SET coolcoins = ? WHERE user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, coolCoins);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public Integer getStudentExp(Integer userId) {

        Integer userExp = 0;
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT user_exp FROM WalletTable WHERE user_Id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer studentExp = rs.getInt("user_exp");
                userExp = studentExp;
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return userExp;
    }

    public void updateStudentExp(Integer userExp, Integer userId) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "UPDATE WalletTable SET user_exp = ? WHERE user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userExp);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
