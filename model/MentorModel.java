package model;

public static MentorModel extends User {

    public static Integer counter = 0;
    private String classID;

    public MentorModel (String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.USER_ID = "M_" + counter++;
        this.login = setDefaultLogin();
        this.pasword = setDeafultPassword();
        this.classID = null;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
