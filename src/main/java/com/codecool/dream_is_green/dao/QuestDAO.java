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

                String questName = resultSet.getString("quest_name");
                int price = resultSet.getInt("price");
                String questCategoryDb = resultSet.getString("quest_category");
                QuestCategoryModel questCategoryModel = new QuestCategoryModel(questCategoryDb);
                QuestModel questModel = new QuestModel(questName, price, questCategoryModel);
                this.addObject(questModel);

            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void inserQuest(String questName, Integer price,  String questCategory) {

        Connection connection;
        Statement statement = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:quest_store.db");

            String query= "INSERT INTO QuestsTable (quest_name, price, quest_category)" +
                          "\nVALUES(" + questName +  Integer.toString(price) + questCategory + ")";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteQuest(String nameQuest) {

        Connection connection;
        Statement statement = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
            String query = "DELETE FROM QuestTable WHERE quest_name = " + nameQuest + ";";
            statement.executeUpdate(query);
            connection.commit();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
