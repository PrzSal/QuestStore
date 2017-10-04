package com.codecool.dream_is_green.model;

public class PreUserModel {

    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private String className;

    public PreUserModel(String name, String surname, String email,
                        String login, String password, String className) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.className = className;
    }


    public String getName() {
        return name;
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

    public String getClassName() {
        return className;
    }

}
