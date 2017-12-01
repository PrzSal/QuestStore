package com.codecool.dream_is_green.model;

public class MailBoxModel extends User {

    private String content;
    private String header;
    private Integer idRecipient;
    private Integer read;
    private Integer react;
    private Integer idMail;
    private String userType;

    public MailBoxModel(int userID, String name, String surname, String email,
                        String login, String password, String header, String content, Integer idRecipient, Integer read, Integer react, Integer idMail, String userType) {
        super(userID, name, surname, email, login, password);
        this.content = content;
        this.header = header;
        this.idRecipient = idRecipient;
        this.read = read;
        this.react = react;
        this.idMail = idMail;
        this.userType = userType;
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

    public Integer getRead() {
        return read;
    }

    public Integer getReact() {
        return react;
    }

    public void setReact(Integer react) {
        this.react = react;
    }

    public String getUserType() {
        return userType;
    }

    public Integer getIdMail() {

        return idMail;
    }

}
