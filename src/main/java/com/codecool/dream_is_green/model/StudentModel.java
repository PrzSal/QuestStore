package com.codecool.dream_is_green.model;

import java.util.LinkedList;

public class StudentModel extends User {

    private Integer experience;
    private String level;
    private WalletModel wallet;
    private String teamID;
    private String className;
    private LinkedList<QuestModel> questsList;

    public StudentModel(int userID, String name, String surname, String email, String login, String password, String className) {

        super(userID, name, surname, email, login, password);
        this.login = login;
        this.password = password;
        this.experience = 0;
        this.level = "noob";
        this.wallet = new WalletModel();
        this.teamID = null;
        this.className = className;
        this.questsList = new LinkedList<QuestModel>();
    }

    public String getLevel() {
        return this.level;
    }

    public Integer getExperience() {
        return this.experience;
    }

    public void setLevel(String level) {
        this.level.equals(level);
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void addExperience(Integer experience) {
        this.experience += experience;
    }

    public LinkedList<QuestModel> getQuestst() {
        return this.questsList;
    }

    public void removeQuest(QuestModel questToRemove) {
        this.questsList.remove(questToRemove);
    }

    public void addQuest(QuestModel questToAdd) {
        this.questsList.remove(questToAdd);
    }

    public WalletModel getWallet() {
        return this.wallet;
    }

    public String toString() {
        return userID + " | " + name + " | " + surname + " | " + className + " | " + teamID;
    }
}
