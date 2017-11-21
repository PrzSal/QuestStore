package com.codecool.dream_is_green.model;

public class PreMailModel {
    String content;
    String header;
    Integer userIdAddressee;
    Integer userIdSender;

    public PreMailModel(String content, String header, Integer userIdAddressee, Integer userIdSender) {
        this.content = content;
        this.header = header;
        this.userIdAddressee = userIdAddressee;
        this.userIdSender = userIdSender;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    public Integer getUserIdAddressee() {
        return userIdAddressee;
    }

    public Integer getUserIdSender() {
        return userIdSender;
    }
}
