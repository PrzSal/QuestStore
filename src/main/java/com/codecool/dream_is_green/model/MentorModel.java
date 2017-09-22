package com.codecool.dream_is_green.model;

public class MentorModel extends User {

    private String className;

    public MentorModel (int userID, String name, String surname, String email,
                        String login, String password, String className) {

        super(userID, name, surname, email, login, password);
        this.className = className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String toString() {

        String str = String.format("%-8s %-12s %-12s %-24s %-12s %-12s %-12s",
                            userID, name, surname, email, className, login, password);
        return str;
    }

}
