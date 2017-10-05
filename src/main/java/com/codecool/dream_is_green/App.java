package com.codecool.dream_is_green;

import com.codecool.dream_is_green.dao.DatabaseConnection;
import com.codecool.dream_is_green.controller.LoginPanelController;
import com.codecool.dream_is_green.dao.FactoryDAO;

public class App {

    public static void main(String[] args) {

        FactoryDAO factoryDao = new FactoryDAO();
        factoryDao.checkIsDatabase();
        DatabaseConnection.getConnection();
        LoginPanelController loginPanel = new LoginPanelController();
        loginPanel.loginIntoSystem();  // admin/admin, mentor/mentor, student/student
        DatabaseConnection.closeConnection();
    }

}
