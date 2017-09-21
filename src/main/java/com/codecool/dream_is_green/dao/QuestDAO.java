package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.QuestModel;
import com.codecool.dream_is_green.model.QuestCategoryModel;

import java.sql.*;

public class QuestDAO extends AbstractDAO<QuestModel> {

    public void loadQuest() {

        Connection connection;
        Statement statement;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String query = "SELECT * FROM QuestsTable";
            ResultSet resultSet = statement.executeQuery(query);

            while ( resultSet.next() ) {

                String questName = resultSet.getString("quest_name");
                int price = resultSet.getInt("price");
                String questCategoryDb = resultSet.getString("quest_category");
                QuestCategoryModel questCategoryModel = new QuestCategoryModel(questCategoryDb);
                QuestModel questModel = new QuestModel(questName, price, questCategoryModel);
                this.addObject(questModel);

            }
            resultSet.close();
            statement.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void insertQuest(String questName, Integer price,  String questCategory) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String query= "INSERT INTO QuestsTable (quest_name, price, quest_category) VALUES(?,?,?);";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, questName);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, questCategory);
            preparedStatement.execute();

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteQuest(String nameQuest) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String query = "DELETE FROM QuestsTable WHERE quest_name = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameQuest);
            preparedStatement.execute();

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
