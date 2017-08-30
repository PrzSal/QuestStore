package model;

public abstract User {

    protected final Integer USER_ID;
    public static Integer counter = 0;
    protected String name;
    protected String surname;
    protected String email;
    protected String login;
    protected String password;

    public String getName(String name){
        return name;
    }
}
