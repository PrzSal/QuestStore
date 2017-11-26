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
}
