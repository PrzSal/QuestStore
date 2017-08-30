package model;

public abstract User {

    protected final Integer USER_ID;
    public static Integer counter = 0;
    protected String name;
    protected String surname;
    protected String email;
    protected String login;
    protected String password;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

}
