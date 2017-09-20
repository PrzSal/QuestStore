package com.codecool.dream_is_green.model;

public class MentorModel extends User {

    private int classID;

    public MentorModel (int userID, String name, String surname, String email, String login, String password, int classID) {
        super(userID, name, surname, email, login, password);
        this.classID = classID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String toString() {
        return userID + " | " + name + " | " + surname + " | " + email + " | " + classID +
               " | " + login + " | " + password;
    }

}
