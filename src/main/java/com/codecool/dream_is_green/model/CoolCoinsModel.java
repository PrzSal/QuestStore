package com.codecool.dream_is_green.model;

public class CoolCoinsModel {
    private Integer coolCoins;
    private Integer studentID;

    public CoolCoinsModel(Integer coolCoins, Integer studentID) {
        this.coolCoins = coolCoins;
        this.studentID = studentID;
    }

    public Integer getCoolcoins() {
        return coolCoins;
    }

    public Integer getStudentID() {
        return studentID;
    }
}
