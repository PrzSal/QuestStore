package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.QuestModel;
import com.codecool.dream_is_green.model.QuestCategoryModel;

import java.sql.*;

public class QuestDAO extends AbstractDAO<QuestModel> {

    public void loadQuest() {

        Connection connection;
        Statement statement;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String query = "SELECT * FROM QuestsTable";
            ResultSet resultSet = statement.executeQuery(query);

            while ( resultSet.next() ) {

                int questId = resultSet.getInt("quest_id");
                String questName = resultSet.getString("quest_name");
                int price = resultSet.getInt("price");
                String questCategoryDb = resultSet.getString("quest_category");
                QuestCategoryModel questCategoryModel = new QuestCategoryModel(questCategoryDb);
                QuestModel questModel = new QuestModel(questId, questName, price, questCategoryModel);
                this.addObject(questModel);

            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void deleteQuest(int id) {


        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:quest_store.db");

            String statement = "DELETE FROM QuestTable WHERE quest_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(statement);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
