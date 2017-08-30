package model;

import java.util.LinkedList;

public class StudentModel extends User {

    private Integer experience;
    private Integer level;
    private WalletModel wallet;
    private String teamID;
    private LinkedList<QuestModel> questsList;

    public StudentModel(String name, String surname, String email) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.USER_ID = counter++;
        this.login = setDefaultLogin();
        this.password = setDefaultPassword();
        this.experience = 0;
        this.level = 0;
        this.wallet = new WalletModel();
        this.teamID = null;
        this.questsList = new LinkedList<QuestModel>();
    }
}