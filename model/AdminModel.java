package model;

public static AdminModel extends User {

    public static Integer counter = 0;

    public AdminModel(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.USER_ID = "A_" + counter++;
        this.login = setDefaultLogin();
        this.pasword = setDeafultPassword();

    }
}
