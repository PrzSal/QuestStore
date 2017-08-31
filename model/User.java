package model;

public abstract class User {

    protected String userID;
    protected String name;
    protected String surname;
    protected String email;
    protected String login;
    protected String password;

    public User(String name, String surname, String email, Strign login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setLogin(String newLogin) {
        login = newLogin;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public String setDefaultLogin() {
        login = "kamil";
        return login;
    }

    public String setDefaultPassword() {
        password = "kmiec";
        return password;
    }
}
