package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.enums.AdminEnum;
import com.codecool.dream_is_green.dao.ClassDAO;
import com.codecool.dream_is_green.dao.MentorDAO;
import com.codecool.dream_is_green.model.ClassModel;
import com.codecool.dream_is_green.model.MentorModel;
import com.codecool.dream_is_green.view.AdminView;
import com.codecool.dream_is_green.view.ClassView;
import com.codecool.dream_is_green.view.MentorView;
import com.codecool.dream_is_green.view.UIView;
import java.lang.NullPointerException;

public class AdminController {
  
    private static UIView view = new UIView();
    private static AdminView adminView = new AdminView();
    private static MentorView mentorView = new MentorView();
    private static ClassView classView = new ClassView();

    public void startMenu(int operation) {

        switch(operation) {

            case CREATE_MENTOR :
                MentorDAO mentorDao = new MentorDAO();
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
                this.showClassList();
                ClassModel newClass = this.createClass();
                ClassDAO classDao = new ClassDAO();
                classDao.addObject(newClass);
                view.pressToContinue();
                break;

            case SHOW_CLASSES :
                this.showClassList();
                view.pressToContinue();
                break;

            case REMOVE_MENTOR :
                this.showMentorList();
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

    public MentorModel createMentor() {

        String name = view.getInput("Enter mentor name: ");
        String surname = view.getInput("Enter mentor surname: ");
        String email = view.getInput("Enter mentor email: ");
        String login = view.getInput("Enter mentor login: ");
        String password = view.getInput("Enter mentor password: ");
        String className = view.getInput("Enter class name: ");

        MentorDAO mentorDao = new MentorDAO();
        mentorDao.insertMentor(name, surname, email, login, password, className);
        int userID = mentorDao.getMentorId(login);
        MentorModel mentor = new MentorModel(userID, name, surname, email, login, password, className);

        return mentor;
    }

    public void removeMentor() {

        MentorDAO mentorDao = new MentorDAO();
        int mentorID = view.getInputInt("Enter the mentor ID you want to remove ");
        mentorDao.deleteMentor(mentorID);
        for (MentorModel mentor : mentorDao.getObjectList()) {
            if (mentor.getUserID() == mentorID) {
                mentorDao.removeObject(mentor);
            }
        }
    }

    public MentorModel getMentorByID() {

        MentorDAO mentorDao = new MentorDAO();
        this.showMentorList();
        int userID = view.getInputInt("Enter mentor ID: ");
        mentorDao.loadMentors();
        for (MentorModel mentor : mentorDao.getObjectList()) {
            if (mentor.getUserID() == userID) {
                return mentor;
            }
        }
        return null;
    }

    public void showMentorList() {

        MentorDAO mentorDao = new MentorDAO();
        mentorDao.loadMentors();
        String mentorDaoString = mentorDao.toString();
        mentorView.showMentorList(mentorDaoString);
    }

    public void showClassList() {

        ClassDAO classDao = new ClassDAO();
        classDao.loadClasses();
        String classDaoString = classDao.toString();
        classView.showClassList(classDaoString);
    }


    public void editMentor(MentorModel mentor) {

        MentorDAO mentorDao = new MentorDAO();
        mentorDao.loadMentors();
        String mentorDaoString = mentorDao.toString();
        mentorView.showMentorList(mentorDaoString);

        int mentorID = mentor.getUserID();
        view.printMessage(mentorID);

        String mentorInfo = mentor.toString();
        view.clearScreen();
        view.printMessage(mentorInfo + "\n1) Edit email.\n2) Edit mentor class.");
        String option = view.getInput("Choose option: ");

        Boolean done = false;

        while(!done) {

            if (option.equals("1")) {
                String email = view.getInput("Enter mentor email: ");
                mentorDao.updateMentor(email, mentorID, "email", "UsersTable");
                mentor.setEmail(email);
                done = true;

            } else if (option.equals("2")) {
                String className = view.getInput("Enter mentor class name: ");
                mentorDao.updateMentor(className, mentorID, "class_name", "MentorsTable");
                mentor.setClassName(className);
                done = true;

            } else {
                view.printMessage("Choose 1 or 2.");
                view.pressToContinue();
                view.clearScreen();
                view.printMessage(mentorInfo + "\n1) Edit email.\n2) Edit mentor class.");
                option = view.getInput("Choose option: ");
            }
        }
    }

    public ClassModel createClass() {

        view.clearScreen();
        view.printMessage("Create new class");

        String className = view.getInput("Enter class name: ");
        ClassDAO classDao = new ClassDAO();
        classDao.insertClass(className);
        ClassModel newClass = new ClassModel(className);

        return newClass;
    }
}
