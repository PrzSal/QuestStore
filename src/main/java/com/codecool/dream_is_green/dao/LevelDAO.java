package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.LevelModel;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Comparator;

public class LevelDAO extends AbstractDAO<LevelModel> {


    public void loadLevels() {

        Connection conn;
        Statement stat;

        try {

            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();


            String query = "SELECT * FROM LevelsTable";
            ResultSet result = stat.executeQuery(query);
            String name;
            int expRequired;

            while (result.next()) {

                name = result.getString("level_name");
                expRequired = result.getInt("exp_required");


                LevelModel level = new LevelModel(name, expRequired);
                this.addObject(level);
            }
            Collections.sort(objectsList, Comparator.comparing(LevelModel::getExpRequired));

            result.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertLevel(String name, Integer expRequired)  {

        Connection conn;
        Statement stat;

        try {
            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();
            conn.setAutoCommit(false);

            String statement = String.format("INSERT INTO LevelsTable (level_name, exp_required) VALUES ('%s', '%d');", name, expRequired);

            stat.executeUpdate(statement);

            stat.close();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteLevel(String levelName) {

        Connection conn;

        try {
            conn = DatabaseConnection.getConnection();

            String statement = "DELETE FROM LevelsTable WHERE level_name = ?";
            PreparedStatement prepStmt = conn.prepareStatement(statement);

            prepStmt.setString(1, levelName);
            prepStmt.execute();
            prepStmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LevelModel getLevelByStudentExp(Integer studentExp) {

        Connection conn;
        Statement stat;

        try {

            conn = DatabaseConnection.getConnection();
            stat = conn.createStatement();


            String query = String.format("SELECT * FROM LevelsTable where exp_required <= %d;", studentExp);
            ResultSet result = stat.executeQuery(query);
            String name;
            int expRequired;

            while (result.next()) {

                name = result.getString("level_name");
                expRequired = result.getInt("exp_required");

                LevelModel level = new LevelModel(name, expRequired);
                this.addObject(level);
            }
            Collections.sort(objectsList, Comparator.comparing(LevelModel::getExpRequired));

            result.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectsList.get(objectsList.size() - 1);
    }


}