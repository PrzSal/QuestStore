package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.ArtifactCategoryModel;
import com.codecool.dream_is_green.model.ArtifactModel;
import com.codecool.dream_is_green.model.StudentModel;
import com.codecool.dream_is_green.model.WalletModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WalletDAO extends AbstractDAO<WalletDAO> {

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

    public void updateCoolcoins(StudentModel student) {

        Connection connection;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            Integer coolcoins = student.getWallet().getCoolCoins();
            String query = "UPDATE WalletTable SET coolcoins = " + coolcoins +  " WHERE user_id = '" + student.getUserID() + "'";

            statement.executeUpdate(query);
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
