package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.ArtifactDAO;
import com.codecool.dream_is_green.dao.DaoStart;
import com.codecool.dream_is_green.dao.QuestDAO;
import com.codecool.dream_is_green.dao.StudentDAO;
import com.codecool.dream_is_green.model.*;
import com.codecool.dream_is_green.view.*;


import java.lang.NumberFormatException;

public class MentorController {

    private static final int ADD_STUDENT = 1;
    private static final int SHOW_STUDENTS = 2;
    private static final int ADD_QUEST = 3;
    private static final int SHOW_QUESTS = 4;
    private static final int ADD_ARTIFACT = 5;
    private static final int SHOW_ARTIFACTS = 6;
    private static final int EXIT = 0;

    private static UIView uiView = new UIView();
    private static MentorView mentorView = new MentorView();
    private static StudentView studentView = new StudentView();
    private static QuestView questView = new QuestView();
    private static ArtifactView artifactView = new ArtifactView();
    private static QuestDAO questDao = DaoStart.getQuestDao();


    public void startMentorController() {

        int operation;

     do {
            uiView.clearScreen();
            mentorView.printMenu();
            operation = uiView.getInputInt("Choice option: ");
            chooseOption(operation);
        } while (operation != EXIT);

    }

    public void chooseOption(int operation) {

        switch(operation) {

            case ADD_STUDENT:
                uiView.clearScreen();
                addStudent();
                break;

            case SHOW_STUDENTS :
                this.showStudentList();
                uiView.pressToContinue();
                break;

            case ADD_QUEST:
                uiView.clearScreen();
                try {
                    addQuest();
                } catch (NumberFormatException e) {
                    uiView.printMessage("This is not integer number");
                    uiView.pressToContinue();
                }
                break;

            case SHOW_QUESTS :
                this.showQuestList();
                uiView.pressToContinue();
                break;

            case ADD_ARTIFACT:
                uiView.clearScreen();
                try {
                    addArtifact();
                } catch (NumberFormatException e) {
                    uiView.printMessage("This is not integer number");
                    uiView.pressToContinue();
                }
                break;

            case SHOW_ARTIFACTS :
                this.showArtifactList();
                uiView.pressToContinue();
                break;

            case EXIT:
                break;

            default:
                uiView.printMessage("No option! Try Again!\n");
        }
    }

    public void showArtifactList() {

        ArtifactDAO artifactDao = new ArtifactDAO();
        artifactDao.loadArtifact();
        String artifactDaoString = artifactDao.toString();
        artifactView.showArtifactList(artifactDaoString);
    }

    public void showQuestList() {

        questDao.loadQuest();
        String questDaoString = questDao.toString();
        questView.showQuestList(questDaoString);
        questDao.clearObjectList();
    }

    public void showStudentList() {

        StudentDAO studentDao = new StudentDAO();
        studentDao.loadStudents();
        String studentDaoString = studentDao.toString();
        studentView.showStudentList(studentDaoString);
    }

    public void addStudent() {

        String name = uiView.getInput("Enter name: ");
        String surname = uiView.getInput("Enter surname: ");
        String email = uiView.getInput("Enter email: ");
        String login = uiView.getInput("Enter login: ");
        String password = uiView.getInput("Enter password: ");
        String className = uiView.getInput("Enter class name: ");

        StudentDAO studentDao = new StudentDAO();
        studentDao.insertStudent(name, surname, email, login, password, className);
    }

    public void addQuest() {

        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        String questCategoryStr = uiView.getInput("Enter category: ");
        QuestCategoryModel questCategory = new QuestCategoryModel(questCategoryStr);
        questDao.insertQuest(title, price, questCategoryStr);
        QuestModel questModel = new QuestModel(title, price, questCategory);
        questDao.addObject(questModel);
    }

    public void addArtifact() {

        String column = "ArtifactsTable";
        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        String categoryName = uiView.getInput("Enter category: ");
        ArtifactDAO artifactDao = new ArtifactDAO();
        artifactDao.insertArtifact(column, title, price, categoryName, 0);
    }
}
