package com.codecool.dream_is_green.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WalletDAO extends AbstractDAO<WalletDAO> {

    public void loadWallet() {

        Connection connection;
        Statement statement;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            String query = "SELECT * FROM WalletTable";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Integer coolcoins = resultSet.getInt("coolcoins");
                Integer userId = resultSet.getInt("user_id");
                
            }
            resultSet.close();
            statement.close();

         } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
