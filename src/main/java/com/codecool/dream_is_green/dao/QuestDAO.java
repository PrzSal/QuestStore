package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.QuestModel;
import com.codecool.dream_is_green.model.QuestCategoryModel;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

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

    public void insertQuest(QuestModel quest) {

        Connection connection;
        PreparedStatement preparedStatement;
        String questName = quest.getTitle();
        Integer price = quest.getPrice();
        String questCategory = quest.getCategory().getName();
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

    public void insertStudentQuest(QuestModel quest, int studentID) {

        Connection connection;
        PreparedStatement preparedStatement;
        String questName = quest.getTitle();
        Integer price = quest.getPrice();
        String questCategory = quest.getCategory().getName();

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            String query= "INSERT INTO StudentsWithQuests (quest_name, quest_category, price, title, user_id) VALUES(?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, questName);
            preparedStatement.setString(2, questCategory);
            preparedStatement.setInt(3, price);
            preparedStatement.setString(4,"title");
            preparedStatement.setInt(5, studentID);
            preparedStatement.execute();

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
