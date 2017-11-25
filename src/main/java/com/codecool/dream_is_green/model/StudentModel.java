package com.codecool.dream_is_green.model;

public class StudentModel extends User {

    private Integer experience;
    private WalletModel wallet;
    private Integer teamId;
    private String className;
    private String voted;

    public StudentModel(int userID, String name, String surname, String email, String login, String password, String className, Integer experience) {

        super(userID, name, surname, email, login, password);
        this.login = login;
        this.password = password;
        this.experience = experience;
        this.wallet = new WalletModel();
        this.teamId = 0;
        this.className = className;
        this.voted = "no";
    }

    public WalletModel getWallet() {
        return this.wallet;
    }

    public Integer getExperience() {
        return experience;
    }

    public String toString() {

        String str = String.format("%-8s %-12s %-12s %-24s %-12s  %-12s %-12s",
                userID, name, surname, email, className, login, password);

        return str;

    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getVoted() {
        return voted;
    }

    public void setVoted(String voted) {
        this.voted = voted;
    }
}
