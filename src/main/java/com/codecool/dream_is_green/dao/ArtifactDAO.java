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
            String query = "SELECT * FROM StudentsWithArtifacts;";
            ResultSet resultSet = statement.executeQuery(query);

            while ( resultSet.next() ) {

                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                String artifactCategoryDb = resultSet.getString("artifact_category");
                ArtifactCategoryModel artifactCategoryModel = new ArtifactCategoryModel(artifactCategoryDb);
                ArtifactModel artifactModel = new ArtifactModel(title, price, artifactCategoryModel);
                this.addObject(artifactModel);

            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void insertArtifact(String artifactName, Integer price,  String artifactCategory) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String query= "INSERT INTO ArtifactsTable (artifact_name, price, artifact_category) VALUES(?,?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artifactName);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, artifactCategory);

            preparedStatement.execute();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteArtifact(String nameArtifact) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String query = "DELETE FROM ArtifactsTable WHERE artifact_name = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameArtifact);
            preparedStatement.execute();

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}