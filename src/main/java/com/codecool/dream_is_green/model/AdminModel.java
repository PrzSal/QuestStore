package com.codecool.dream_is_green.model;

public class AdminModel extends User {

    public static Integer counter = 0;

    public AdminModel(int userID, String name, String surname, String email, String login, String password){
        super(userID, name, surname, email, login, password);
    }

    public String toString() {
        return userID + "\t" + name + "\t" + surname + "\t" + email;
    }
}
