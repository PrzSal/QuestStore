import com.codecool.dream_is_green.model.*;
import com.codecool.dream_is_green.controller.*;
import com.codecool.dream_is_green.view.*;
import com.codecool.dream_is_green.dao.*;

import java.util.Arrays;

public class App {

    private static final String EXIT = "E";
    static AdminDAO adminDao;
    static MentorDAO mentorDao;
    static StudentDAO studentDao;
    static ClassDAO classDao;
    static QuestDAO questDao;
    static ArtifactDAO artifactDao;

    public App() {

        adminDao = new AdminDAO();
        mentorDao = new MentorDAO();
        studentDao = new StudentDAO();
        classDao = new ClassDAO();
        questDao = new QuestDAO();
        artifactDao = new ArtifactDAO();

    }

    public static void main(String[] args) {

        App app = new App();

        AdminModel admin = new AdminModel(12,"Janek", "Kowalski",
                                          "janek.ko@uo.com", "admin", "admin");
        adminDao.addObject(admin);

        loginIntoSystem();
    }

    public static void loginIntoSystem() {

        UIView view = new UIView();
        String operation;

        do {

            view.clearScreen();

            operation = view.getInput("~~~ Welcome in CODECOOL SHOP :)\n" +
                                      "~~~ Login into system: press ENTER to continue\n" +
                                      "~~~ Exit: press E\n");
            operation = operation.toUpperCase();

            if (!operation.equals(EXIT)) {

                String login = view.getInput("Enter login: ");
                String password = view.getInput("Enter password: ");

                System.out.println(login + " " + password);

                MentorModel mentor = findMentor(login);
                AdminModel admin = findAdmin(login);
                StudentModel student = findStudent(login);

                if (mentor != null && mentor.getPassword().equals(password)) {

                    MentorController mentorController = new MentorController();
                    mentorController.startMentorController();

                } else if (admin != null && admin.getPassword().equals(password)) {

                    AdminController adminController = new AdminController();
                    adminController.startAdminController();

                } else if (student != null && student.getPassword().equals(password)) {

                    StudentController studentController = new StudentController();
                    studentController.startStudentController(student);

                } else {

                    view.printMessage("\nWrong login or password!\n");
                    view.pressToContinue();
                }
            }

       } while (!operation.equals(EXIT));
    }

    public static MentorModel findMentor(String login) {

        for (MentorModel mentor : mentorDao.getObjectList()) {
            if(mentor.getLogin().equals(login)) {
                return mentor;
            }
        }
        return null;
    }

    public static StudentModel findStudent(String login) {

        for (StudentModel student : studentDao.getObjectList()) {
            if(student.getLogin().equals(login)) {
                return student;
            }
        }
        return null;
    }

    public static AdminModel findAdmin(String login) {

        for (AdminModel admin : adminDao.getObjectList()) {
            if(admin.getLogin().equals(login)) {
                return admin;
            }
        }
        return null;
    }
}
