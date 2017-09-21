package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.model.*;
import com.codecool.dream_is_green.dao.*;
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

        String artifactDaoString = DaoStart.getArtifactDao().toString();
        artifactView.showArtifactList(artifactDaoString);
    }

    public void showQuestList() {

        String questDaoString = DaoStart.getArtifactDao().toString();
        questView.showQuestList(questDaoString);
    }

    public void showStudentList() {

        String studentDaoString = DaoStart.getStudentDao().toString();
        studentView.showStudentList(studentDaoString);
    }

    public void addStudent() {

        int userID = uiView.getInputInt("Enter student ID: ");
        String name = uiView.getInput("Enter name: ");
        String surname = uiView.getInput("Enter surname: ");
        String email = uiView.getInput("Enter email: ");
        String login = uiView.getInput("Enter login: ");
        String password = uiView.getInput("Enter password: ");
        String className = uiView.getInput("Enter class name: ");

        StudentModel studentModel = new StudentModel(userID, name, surname, email, login, password, className);
        DaoStart.getStudentDao().addObject(studentModel);
    }

    public void addQuest() {

        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        String questCategoryStr = uiView.getInput("Enter category: ");
        QuestCategoryModel questCategory = new QuestCategoryModel(questCategoryStr);
        DaoStart.getQuestDao().inserQuest(title, price, questCategoryStr);
        QuestModel questModel = new QuestModel(title, price, questCategory);
        DaoStart.getQuestDao().addObject(questModel);
    }

    public void addArtifact() {

        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        String categoryName = uiView.getInput("Enter category: ");
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(categoryName);
        DaoStart.getArtifactDao().insertArtifact(title, price, categoryName);
        ArtifactModel artifactModel = new ArtifactModel(title, price, artifactCategory);
        DaoStart.getArtifactDao().addObject(artifactModel);
    }
}
