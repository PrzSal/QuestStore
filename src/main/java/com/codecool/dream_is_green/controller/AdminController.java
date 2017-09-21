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
    private static final int REMOVE_MENTOR = 6;
    private static final int EXIT = 0;

    private static UIView view = new UIView();
    private static AdminView adminView = new AdminView();
    private static MentorView mentorView = new MentorView();
    private static ClassView classView = new ClassView();
    private static MentorDAO mentorDao = DaoStart.getMentorDao();
    private static ClassDAO classDao = DaoStart.getClassDao();

    public MentorModel createMentor() {

        String name = view.getInput("Enter mentor name: ");
        String surname = view.getInput("Enter mentor surname: ");
        String email = view.getInput("Enter mentor email: ");
        String login = view.getInput("Enter mentor login: ");
        String password = view.getInput("Enter mentor password: ");
        String className = view.getInput("Enter class name: ");

        mentorDao.insertMentor(name, surname, email, login, password, className);
        int userID = mentorDao.getMentorId(login);
        MentorModel mentor = new MentorModel(userID, name, surname, email, login, password, className);

        return mentor;
    }

    public void removeMentor() {

        int mentorID = view.getInputInt("Enter the mentor ID you want to remove ");
        mentorDao.deleteMentor(mentorID);
        for (MentorModel mentor : mentorDao.getObjectList()) {
            if (mentor.getUserID() == mentorID) {
                mentorDao.removeObject(mentor);
            }
        }
    }

    public MentorModel getMentorByID() {

        this.showMentorList();
        int userID = view.getInputInt("Enter mentor ID: ");

        for (MentorModel mentor : mentorDao.getObjectList()) {
            if (mentor.getUserID() == userID) {
                return mentor;
            }
        }
        return null;
    }

    public void showMentorList() {

        mentorDao.loadMentors();
        String mentorDaoString = mentorDao.toString();
        mentorDao.loadMentors();
        mentorView.showMentorList(mentorDaoString);
        mentorDao.clearObjectList();

    }

    public void showClassList() {

        String classDaoString = classDao.toString();
        classView.showClassList(classDaoString);
    }


    public void editMentor(MentorModel mentor) {

        mentorDao.clearObjectList();
        String mentorDaoString = mentorDao.toString();
        mentorView.showMentorList(mentorDaoString);

        int mentorID = mentor.getUserID();

        String mentorInfo = mentor.toString();
        view.clearScreen();
        view.printMessage(mentorInfo);
        view.printMessage("1) Edit email.\n2) Edit mentor class.");
        String option = view.getInput("Choose option: ");

        Boolean done = false;

        while(!done)
            if(option.equals("1")) {
                String email = view.getInput("Enter mentor email: ");
                mentorDao.updateMentor(email, mentorID, "email");
                mentor.setEmail(email);
                done = true;
            }
            else if(option.equals("2")) {
                String className = view.getInput("Enter mentor class name: ");
                mentorDao.updateMentor(className, mentorID, "class_name");
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
            mentorDao.addObject(newMentor);
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
            classDao.addObject(newClass);
            view.pressToContinue();
            break;

        case SHOW_CLASSES :
            this.showClassList();
            view.pressToContinue();
            break;

        case REMOVE_MENTOR :
            this.removeMentor();
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
