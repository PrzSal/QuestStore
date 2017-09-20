package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.ArtifactCategoryModel;
import com.codecool.dream_is_green.model.ArtifactModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ArtifactDAO extends AbstractDAO<ArtifactModel> {

    public void loadArtifact() {

        Connection connection;
        Statement statement;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
            conection.setAutoCommit(false);

            statement = connection.createStatement();
            String query = "SELECT * FROM StudentsWithArtifacts;";
            ResultSet resultSet = statement.executeQuery(query);

            while ( resultSet.next() ) {
                int artifactId = resultSet.getInt("artifact_id");
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
}