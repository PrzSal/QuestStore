package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.ArtifactCategoryModel;
import com.codecool.dream_is_green.model.ArtifactModel;

import java.sql.*;

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

    public void insertArtifact(String table, String artifactName, Integer price,  String artifactCategory, int id) {

        Connection connection;
        Statement statement;

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
}