package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.QuestModel;
import com.codecool.dream_is_green.model.QuestCategoryModel;
import com.codecool.dream_is_green.model.StudentQuestModel;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;

public class QuestDAO extends AbstractDAO<QuestModel> {

    public LinkedList<String> loadQuestsTitle(int studentID) {
        LinkedList<String> questsTile = new LinkedList<>();
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String selectSQL = "SELECT quest_name FROM StudentsWithQuests WHERE user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, studentID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("quest_name");

                questsTile.add(title);
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return questsTile;
    }

    public LinkedList<StudentQuestModel> loadStudentsWithQuests() {
        LinkedList<StudentQuestModel> studentsWithQuests = new LinkedList<>();
        Connection connection;

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String selectSQL = "SELECT * FROM StudentsWithQuests;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("quest_name");
                Integer price = rs.getInt("price");
                String category = rs.getString("quest_category");
                Integer studentID = rs.getInt("user_id");

                QuestCategoryModel questCategoryModel = new QuestCategoryModel(category);
                StudentQuestModel studentQuestModel = new StudentQuestModel(title, price, questCategoryModel, studentID);
                studentsWithQuests.add(studentQuestModel);
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return studentsWithQuests;
    }

    public void deleteStudentWithQuest(StudentQuestModel studentQuestModel) {

        Connection connection;
        String title = studentQuestModel.getTitle().replaceAll("\\s+", "\n");
        Integer studentID = studentQuestModel.getStudentID();

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String insertTableSQL = "DELETE FROM StudentsWithQuests WHERE quest_name = ? AND user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, studentID);

            preparedStatement .executeUpdate();
            preparedStatement.close();
            connection.commit();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
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

    public void deleteQuest(String questTitle) {

        Connection connection;
        String questTitleRep = questTitle.replaceAll("\\s+", "\n");

        try {
            connection =  DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String insertTableSQL = "DELETE FROM QuestsTable WHERE quest_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, questTitleRep);

            preparedStatement .executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void updateQuestStudents(QuestModel questModel) {

        Connection connection;
        String title = questModel.getTitle();
        int price = questModel.getPrice();
        String category = questModel.getCategory().getName();

        try {
            connection = DatabaseConnection.getConnection();

            String updateQuestStudents = "UPDATE StudentsWithQuests SET price = ?, quest_category = ? WHERE quest_name = ?;";
            PreparedStatement artifactsStudentStatement = connection.prepareStatement(updateQuestStudents);

            artifactsStudentStatement.setInt(1, price);
            artifactsStudentStatement.setString(2, category);
            artifactsStudentStatement.setString(3, title);

            artifactsStudentStatement.executeUpdate();
            artifactsStudentStatement.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void updateQuestTable(QuestModel questModel) {

        Connection connection;
        String title = questModel.getTitle();
        int price = questModel.getPrice();
        String category = questModel.getCategory().getName();

        try {
            connection = DatabaseConnection.getConnection();

            String updateQuestTable = "UPDATE QuestsTable SET price = ?, quest_category = ? WHERE quest_name = ?;";
            PreparedStatement artifactsStatement = connection.prepareStatement(updateQuestTable);

            artifactsStatement.setInt(1, price);
            artifactsStatement.setString(2, category);
            artifactsStatement.setString(3, title);

            artifactsStatement.executeUpdate();
            artifactsStatement.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
