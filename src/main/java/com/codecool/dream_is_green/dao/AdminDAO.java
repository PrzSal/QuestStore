package com.codecool.dream_is_green.dao;

import java.sql.*;

import com.codecool.dream_is_green.model.AdminModel;
import com.codecool.dream_is_green.view.UIView;

public class AdminDAO extends AbstractDAO<AdminModel> {

        private static UIView view = new UIView();

        public void loadAdmins() {
//            Method ready to use, pass test, but not implemented in controller
            Connection c;
            Statement stmt;

            try {

                c = DatabaseConnection.getConnection();
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

            } catch ( Exception e ) {
                view.printMessage( e.getClass().getName() + ": " + e.getMessage() );
            }
        }
    }