package com.codecool.dream_is_green.model;

public class StudentModel extends User {

    private Integer experience;
    private WalletModel wallet;
    private String teamName;
    private String className;

    public StudentModel(int userID, String name, String surname, String email, String login, String password, String className, Integer experience) {

        super(userID, name, surname, email, login, password);
        this.login = login;
        this.password = password;
        this.experience = experience;
        this.wallet = new WalletModel();
        this.teamName = null;
        this.className = className;
    }

    public WalletModel getWallet() {
        return this.wallet;
    }

    public Integer getExperience() {
        return experience;
    }

    public String toString() {

        String str = String.format("%-8s %-12s %-12s %-24s %-12s %-12s %-12s %-12s",
                userID, name, surname, email, className, teamName, login, password);

        return str;

    }
}
