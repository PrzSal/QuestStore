package com.codecool.dream_is_green;

import com.codecool.dream_is_green.controller.controllers.*;
import com.codecool.dream_is_green.dao.DatabaseConnection;
import com.codecool.dream_is_green.controller.LoginPanelController;
import com.codecool.dream_is_green.dao.FactoryDAO;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) {
        // create a server on port 8000
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set routes
        server.createContext("/login", new LoginController());
        server.createContext("/admin", new AdminController());
        server.createContext("/mentor", new MentorController());
        server.createContext("/student", new StudentController());
        server.createContext("/static", new Static2());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
        FactoryDAO factoryDao = new FactoryDAO();
        factoryDao.checkIsDatabase();
        DatabaseConnection.getConnection();
//        DatabaseConnection.closeConnection();

    }

}
