package com.codecool.dream_is_green.dao;

import java.sql.*;
import java.util.LinkedList;

import com.codecool.dream_is_green.model.AdminModel;

public class AdminDAO extends AbstractDAO<AdminModel> {


        public void loadAdmins() {

            Connection c = null;
            Statement stmt = null;

            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:quest_store.db");
                c.setAutoCommit(false);

                stmt = c.createStatement();
                String query = "SELECT * FROM UsersTable WHERE user_type = 'admin';";
                ResultSet rs = stmt.executeQuery(query);

                while ( rs.next() ) {
                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String email = rs.getString("email");
                    String login = rs.getString("login");
                    String password = rs.getString("password");

                    AdminModel admin = new AdminModel(userId, name, surname, email, login, password);
                    this.addObject(admin);

                }
                rs.close();
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
    }
