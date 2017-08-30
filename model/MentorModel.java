package model;

public static MentorModel extends User {

    public static Integer counter = 0;
    private String teamID;

    public MentorModel (String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.USER_ID = "M_" + counter++;
        this.login = setDefaultLogin();
        this.pasword = setDeafultPassword();
        this.teamID = null;

    }
