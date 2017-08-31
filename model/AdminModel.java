package model;

public class AdminModel extends User {

    public static Integer counter = 0;

    public AdminModel(String name, String surname, String email, String login, String password){
        super(name, surname, email, login, password);
        this.USER_ID = "A_" + counter++;
    }
}
