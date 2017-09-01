package controller;
import model.*;
import view.*;
import dao.*;

import java.util.Scanner;
import java.util.ArrayList;


public class AdminController {

    private static final String CREATE_MENTOR = "1";
    private static final String EDIT_MENTOR = "2";
    private static final String CREATE_CLASS = "3";
    private static final String EXIT = "0";

    private static UIView view = new UIView();
    private static AdminView adminView = new AdminView();

    public MentorModel createMentor() {
        String name = view.getInput("Enter mentor name: ");
        String surname = view.getInput("Enter mentor surname: ");
        String email = view.getInput("Enter mentor email: ");
        String login = view.getInput("Enter mentor login: ");
        String password = view.getInput("Enter mentor password: ");


        MentorModel mentor = new MentorModel(name, surname, email, login, password);

        return mentor;
    }

    public ArrayList<MentorModel> getMentorBySurname(String mentorSurname) {

        ArrayList<MentorModel> mentorList = new ArrayList<MentorModel>();

        MentorModel mentor1 = new MentorModel("Mateusz", "Ostafil", "mati@gmail.com", "dupa1", "pupa1");
        MentorModel mentor2 = new MentorModel("Mateusz", "Steliga", "scooby@gmail.com", "dupa1", "pupa1");
        MentorModel mentor3 = new MentorModel("Agnieszka", "Koszany", "agi@gmail.com", "dupa1", "pupa1");

        mentorList.add(mentor1);
        mentorList.add(mentor2);
        mentorList.add(mentor3);

        ArrayList<MentorModel> searchMentor = new ArrayList<MentorModel>();

        for(MentorModel mentor: mentorList) {
            if(mentor.getSurname().equals(mentorSurname))
                searchMentor.add(mentor);
        }

        return searchMentor;
    }


    public void editMentor(MentorModel mentor) {

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
            this.createMentor();
            view.continueButton();
            break;

        case EDIT_MENTOR :
            // ArrayList<MentorModel> mentorSurname = view.getInput("Find mentor by surname: ");
            // this.getMentorBySurname(String mentorSurname)
            view.continueButton();
            break;

        case CREATE_CLASS :
            this.createClass();
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
