package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.controller.AdminController;
import com.codecool.dream_is_green.controller.MentorController;
import com.codecool.dream_is_green.controller.StudentController;
import com.codecool.dream_is_green.view.UIView;

public class LoginDAO {

    private static final String EXIT = "E";

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
               chooseUserPanel(login, password);
            }

        } while (!operation.equals(EXIT));
    }


    private static void chooseUserPanel(String login, String password) {

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
}
