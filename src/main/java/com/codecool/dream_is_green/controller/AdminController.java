package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.model.*;
import com.codecool.dream_is_green.view.*;
import com.codecool.dream_is_green.dao.*;
import java.lang.NullPointerException;


public class AdminController {

    private static final int CREATE_MENTOR = 1;
    private static final int EDIT_MENTOR = 2;
    private static final int SHOW_MENTORS = 3;
    private static final int CREATE_CLASS = 4;
    private static final int SHOW_CLASSES = 5;
    private static final int EXIT = 0;

    private static UIView view = new UIView();
    private static AdminView adminView = new AdminView();
    private static MentorView mentorView = new MentorView();
    private static ClassView classView = new ClassView();


    public MentorModel createMentor() {

        String name = view.getInput("Enter mentor name: ");
        String surname = view.getInput("Enter mentor surname: ");
        String email = view.getInput("Enter mentor email: ");
        String login = view.getInput("Enter mentor login: ");
        String password = view.getInput("Enter mentor password: ");
        String className = view.getInput("Enter class name: ");

        DaoStart.getMentorDao().insertMentor(name, surname, email, login, password, className);
        int userID = DaoStart.getMentorDao().getMentorId(login);
        MentorModel mentor = new MentorModel(userID, name, surname, email, login, password, className);

        return mentor;
    }

    public MentorModel getMentorByID() {

        this.showMentorList();
        int userID = view.getInputInt("Enter mentor ID: ");

        for (MentorModel mentor : DaoStart.getMentorDao().getObjectList()) {
            if (mentor.getUserID() == userID) {
                return mentor;
            }
        }
        return null;
    }

    public void showMentorList() {

        DaoStart.getMentorDao().loadMentors();
        String mentorDaoString = DaoStart.getMentorDao().toString();
        mentorView.showMentorList(mentorDaoString);
    }

    public void showClassList() {

        String classDaoString = DaoStart.getClassDao().toString();
        classView.showClassList(classDaoString);
    }


    public void editMentor(MentorModel mentor) {

        String mentorDaoString = DaoStart.getMentorDao().toString();
        mentorView.showMentorList(mentorDaoString);

        String mentorInfo = mentor.toString();
        view.clearScreen();
        view.printMessage(mentorInfo);
        view.printMessage("1) Edit email.\n2) Edit mentor class.");
        String option = view.getInput("Choose option: ");

        Boolean done = false;

        while(!done)
            if(option.equals("1")) {
                String email = view.getInput("Enter mentor email: ");
                mentor.setEmail(email);
                done = true;
            }
            else if(option.equals("2")) {
                String className = view.getInput("Enter mentor class name: ");
                mentor.setClassName(className);
                done = true;
            }
            else {
                view.printMessage("Choose 1 or 2.");
                view.pressToContinue();
                view.clearScreen();
                view.printMessage(mentorInfo);
                view.printMessage("1) Edit email.\n2) Edit mentor class.");
                option = view.getInput("Choose option: ");
            }

    }

    public ClassModel createClass() {

        view.clearScreen();
        view.printMessage("Create new class");

        String className = view.getInput("Enter class name: ");
        ClassModel newClass = new ClassModel(className);

        return newClass;
    }

    public void startMenu(int operation) {

        switch(operation) {

        case CREATE_MENTOR :
            MentorModel newMentor = this.createMentor();
            DaoStart.getMentorDao().addObject(newMentor);
            view.pressToContinue();
            break;

        case EDIT_MENTOR :
            MentorModel mentor = getMentorByID();

            try {
                this.editMentor(mentor);
            } catch (NullPointerException e) {
                view.printMessage("Wrong ID\n");
            }
            view.pressToContinue();
            break;

        case SHOW_MENTORS :
            this.showMentorList();
            view.pressToContinue();
            break;

        case CREATE_CLASS :
            ClassModel newClass = this.createClass();
            DaoStart.getClassDao().addObject(newClass);
            view.pressToContinue();
            break;

        case SHOW_CLASSES :
            this.showClassList();
            view.pressToContinue();
            break;

        case EXIT:
            break;

         default :
            view.printMessage("No option! Try Again!\n");
            view.pressToContinue();
        }

    }

    public void startAdminController() {

        int operation;

        do {
            view.clearScreen();
            adminView.showMenu();
            operation = view.getInputInt("Choice option: ");
            view.clearScreen();
            this.startMenu(operation);
        } while (operation != EXIT);

    }

}
