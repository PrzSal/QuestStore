package model;

public static AdminModel extends User {

    public AdminModel(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.USER_ID = counter++;
        this.login = setDefaultLogin();
        this.pasword = setDeafultPassword();

    }
}
