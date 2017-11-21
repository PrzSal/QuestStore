package com.codecool.dream_is_green.model;

public class MailBoxModel extends User {

    private String content;
    private String header;
    private Integer idRecipient;

    public MailBoxModel(int userID, String name, String surname, String email,
                        String login, String password, String header, String content, Integer idRecipient) {
        super(userID, name, surname, email, login, password);
        this.content = content;
        this.header = header;
        this.idRecipient = idRecipient;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    public Integer getIdRecipient() {
        return idRecipient;
    }
}
