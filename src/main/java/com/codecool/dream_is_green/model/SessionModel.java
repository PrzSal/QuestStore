package com.codecool.dream_is_green.model;

public class SessionModel {

    private String sessionId;
    private String userName;
    private String userType;

    public SessionModel(String sessionId, String userName, String userType) {

        this.sessionId = sessionId;
        this.userName = userName;
        this.userType = userType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }
}
