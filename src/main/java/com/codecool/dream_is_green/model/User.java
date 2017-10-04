package com.codecool.dream_is_green.model;

public abstract class User {

    protected int userID;
    protected String name;
    protected String surname;
    protected String email;
    protected String login;
    protected String password;

    public User(int userID, String name, String surname, String email, String login, String password) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }
    public String getFullName() {
        return name + " " + surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return  email; }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setLogin(String newLogin) {
        login = newLogin;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }
}
