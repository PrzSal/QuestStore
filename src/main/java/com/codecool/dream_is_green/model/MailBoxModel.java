package com.codecool.dream_is_green.model;

public class MailBoxModel extends User {

    private String content;
    private String header;

    public MailBoxModel(int userID, String name, String surname, String email,
                        String login, String password, String header, String content) {
        super(userID, name, surname, email, login, password);
        this.content = content;
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }
}
