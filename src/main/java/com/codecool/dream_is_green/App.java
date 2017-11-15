package com.codecool.dream_is_green;

import com.codecool.dream_is_green.controller.controllers.Static2;
import com.codecool.dream_is_green.controller.controllers.StudentController;
import com.codecool.dream_is_green.dao.DatabaseConnection;
import com.codecool.dream_is_green.controller.LoginPanelController;
import com.codecool.dream_is_green.dao.FactoryDAO;
import com.codecool.dream_is_green.model.ClassModel;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) {
        StudentController.getClassModels().add(new ClassModel("krk-2017-1"));
        // create a server on port 8000
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set routes
        server.createContext("/students", new StudentController());
        server.createContext("/static", new Static2());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
        FactoryDAO factoryDao = new FactoryDAO();
        factoryDao.checkIsDatabase();
        DatabaseConnection.getConnection();
        LoginPanelController loginPanel = new LoginPanelController();
        loginPanel.loginIntoSystem();  // admin/admin, mentor/mentor, student/student
        DatabaseConnection.closeConnection();

    }

}
