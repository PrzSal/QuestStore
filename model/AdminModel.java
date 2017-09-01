package model;

public class AdminModel extends User {

    public static Integer counter = 0;

    public AdminModel(String name, String surname, String email, String login, String password){
        super(name, surname, email, login, password);
        this.userID = "A_" + counter++;
    }

    public String toString() {
        return userID + " | " + name + " | " + surname + " | " + email;
    }
}
