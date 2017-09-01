package controller;
import model.MentorModel;
import view.*;

import model.*;
import view.*;
import dao.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.NullPointerException;


public class AdminController {

    private static final String CREATE_MENTOR = "1";
    private static final String EDIT_MENTOR = "2";
    private static final String SHOW_MENTORS = "3";
    private static final String CREATE_CLASS = "4";
    private static final String SHOW_CLASSES = "5";
    private static final String EXIT = "0";

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

        MentorModel mentor = new MentorModel(name, surname, email, login, password);

        return mentor;
    }

    public MentorModel getMentorByID(MentorDAO mentorDao) {

        this.showMentorList(mentorDao);
        String mentorID = view.getInput("Enter mentor ID: ");

        for(MentorModel mentor: mentorDao.getObjectList()) {
            if(mentor.getUserID().equals(mentorID))
                return mentor;
        }

        return null;
    }

    public void showMentorList(MentorDAO mentorDao) {

        String mentorDaoString = mentorDao.toString();
        mentorView.showMentorList(mentorDaoString);
    }

    public void showClassList(ClassDAO classDao) {

        String classDaoString = classDao.toString();
        classView.showClassList(classDaoString);
    }


    public void editMentor(MentorModel mentor, MentorDAO mentorDao) {

        String mentorDaoString = mentorDao.toString();
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
                String classID = view.getInput("Enter mentor class ID: ");
                mentor.setClassID(classID);
                done = true;
            }
            else {
                view.printMessage("Choose 1 or 2.");
                view.continueButton();
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

    public void startMenu(String operation, MentorDAO mentorDao, ClassDAO classDao) {

        switch(operation) {

        case CREATE_MENTOR :
            MentorModel newMentor = this.createMentor();
            mentorDao.addObject(newMentor);
            view.continueButton();
            break;

        case EDIT_MENTOR :
            MentorModel mentor = getMentorByID(mentorDao);

            try {
                this.editMentor(mentor, mentorDao);
            } catch (NullPointerException e) {
                view.printMessage("Wrong ID\n");
            }
            view.continueButton();
            break;

        case SHOW_MENTORS :
            this.showMentorList(mentorDao);
            view.continueButton();
            break;

        case CREATE_CLASS :
            ClassModel newClass = this.createClass();
            classDao.addObject(newClass);
            view.continueButton();
            break;

        case SHOW_CLASSES :
            this.showClassList(classDao);
            view.continueButton();
            break;

        case EXIT:
            break;

         default :
            view.printMessage("No option! Try Again!\n");
            view.continueButton();
        }

    }

    public void startAdminController(MentorDAO mentorDao, ClassDAO classDao) {

        String operation;

        do {
            view.clearScreen();
            adminView.showMenu();
            operation = view.getInput("Choice option: ");
            view.clearScreen();
            this.startMenu(operation, mentorDao, classDao);
        } while (!operation.equals(EXIT));

    }

}
