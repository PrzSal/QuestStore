package com.codecool.dream_is_green.model;

public class LoginDetailsModel {

    private String userType;
    private String password;

    public LoginDetailsModel(String userType, String password) {
        this.userType = userType;
        this.password = password;
    }

    public String getUserType() {
        return this.userType;
    }

    public String getUserPassword() {
        return this.password;
    }


}
