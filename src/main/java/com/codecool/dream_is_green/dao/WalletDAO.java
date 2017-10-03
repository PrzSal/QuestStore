package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.StudentModel;

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
}
