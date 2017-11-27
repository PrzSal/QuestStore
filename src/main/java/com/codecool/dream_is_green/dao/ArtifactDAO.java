package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.ArtifactCategoryModel;
import com.codecool.dream_is_green.model.ArtifactModel;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;


public class ArtifactDAO extends AbstractDAO<ArtifactModel> {

    public void loadArtifact() {

        Connection connection;
        Statement statement;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String query = "SELECT * FROM ArtifactsTable;";
            ResultSet resultSet = statement.executeQuery(query);

            while ( resultSet.next() ) {

                String title = resultSet.getString("artifact_name");
                int price = resultSet.getInt("price");
                String artifactCategoryDb = resultSet.getString("artifact_category");
                ArtifactCategoryModel artifactCategoryModel = new ArtifactCategoryModel(artifactCategoryDb);
                ArtifactModel artifactModel = new ArtifactModel(title, price, artifactCategoryModel);
                this.addObject(artifactModel);

            }
            resultSet.close();
            statement.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void insertArtifact(String table, ArtifactModel artifactModel, Integer id) {

        Connection connection;
        Statement statement;
        String artifactName = artifactModel.getTitle();
        Integer price = artifactModel.getPrice();
        String artifactCategory = artifactModel.getCategory().getName();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            if(table.equals("ArtifactsTable")) {

                String query = String.format("INSERT INTO '%s' (artifact_name, price, artifact_category) VALUES('%s', %d, '%s');", table, artifactName, price, artifactCategory);
                statement.executeUpdate(query);
                connection.commit();

            } else {
                String query = String.format("INSERT INTO '%s' (artifact_name, price, artifact_category, user_id) VALUES('%s', %d, '%s', %d);", table, artifactName, price, artifactCategory, id);
                statement.executeUpdate(query);
                connection.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateArtifact(String table, Integer state, Integer userID) {
        Connection conn;
        Statement stat;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            stat = conn.createStatement();

            String statement = String.format("UPDATE %s set 'state' = '%d' where user_id=%d;", table, state, userID);
            stat.executeUpdate(statement);
            conn.commit();

            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteArtifact(String artifactTitle) {

        Connection connection;
        artifactTitle = artifactTitle.replaceAll("\\s+","\n");

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String insertTableSQL = "DELETE FROM ArtifactsTable WHERE artifact_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, artifactTitle);

            preparedStatement .executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public ArtifactModel getArtifact(String artifactTitle) {

        ArtifactModel artifact = null;
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT * FROM ArtifactsTable WHERE artifact_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, artifactTitle);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("artifact_name");
                Integer price = rs.getInt("price");
                String category = rs.getString("artifact_category");
                Integer state = rs.getInt("state");

                ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(category);
                artifact = new ArtifactModel(title, price, artifactCategory);
                artifact.setIsUsed(state);
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return artifact;
    }

    public ArtifactModel getUserArtifact(String artifactTitle, Integer userId) {

        ArtifactModel artifact = null;
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();

            String selectSQL = "SELECT * FROM StudentsWithArtifacts WHERE artifact_name = ? and user_Id =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, artifactTitle);
            preparedStatement.setInt(2, userId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("artifact_name");
                Integer price = rs.getInt("price");
                String category = rs.getString("artifact_category");
                Integer state = rs.getInt("state");

                ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(category);
                artifact = new ArtifactModel(title, price, artifactCategory);
                artifact.setIsUsed(state);
            }

            preparedStatement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return artifact;
    }

    public void insertUserArtifact(ArtifactModel artifact, Integer userId) {

        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String title = artifact.getTitle();
            Integer price = artifact.getPrice();
            ArtifactCategoryModel artifactCategory = artifact.getCategory();
            String category = artifactCategory.getName();
            Integer state = artifact.getIsUsed();

            String insertTableSQL = "INSERT INTO StudentsWithArtifacts (artifact_name, price, artifact_category, user_Id, state) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, state);

            preparedStatement .executeUpdate();

            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
