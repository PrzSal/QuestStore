package com.codecool.dream_is_green.model;

public class MentorModel extends User {

    public static Integer counter = 0;
    private String classID;

    public MentorModel (String name, String surname, String email, String login, String password) {
        super(name, surname, email, login, password);
        this.userID = "M_" + counter++;
        this.classID = null;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String toString() {
        return userID + " | " + name + " | " + surname + " | " + email + " | " + classID +
               " | " + login + " | " + password;
    }

}
