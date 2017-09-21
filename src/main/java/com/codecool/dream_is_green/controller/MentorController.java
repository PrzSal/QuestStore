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

    public void startMentorController(StudentDAO studentDAO, QuestDAO questDAO, ArtifactDAO artifactDAO) {

        int operation;

     do {
            uiView.clearScreen();
            mentorView.printMenu();
            operation = uiView.getInputInt("Choice option: ");
            chooseOption(operation, studentDAO, questDAO, artifactDAO);
        } while (operation != EXIT);

    }

    public void chooseOption(int operation, StudentDAO studentDAO,
                             QuestDAO questDAO, ArtifactDAO artifactDAO) {

        switch(operation) {

            case ADD_STUDENT:
                uiView.clearScreen();
                addStudent(studentDAO);
                break;

            case SHOW_STUDENTS :
                this.showStudentList(studentDAO);
                uiView.pressToContinue();
                break;

            case ADD_QUEST:
                uiView.clearScreen();
                try {
                    addQuest(questDAO);
                } catch (NumberFormatException e) {
                    uiView.printMessage("This is not integer number");
                    uiView.pressToContinue();
                }
                break;

            case SHOW_QUESTS :
                this.showQuestList(questDAO);
                uiView.pressToContinue();
                break;

            case ADD_ARTIFACT:
                uiView.clearScreen();
                try {
                    addArtifact(artifactDAO);
                } catch (NumberFormatException e) {
                    uiView.printMessage("This is not integer number");
                    uiView.pressToContinue();
                }
                break;

            case SHOW_ARTIFACTS :
                this.showArtifactList(artifactDAO);
                uiView.pressToContinue();
                break;

            case EXIT:
                break;

            default:
                uiView.printMessage("No option! Try Again!\n");
        }
    }

    public void showArtifactList(ArtifactDAO artifactDAO) {

        String artifactDaoString = artifactDAO.toString();
        artifactView.showArtifactList(artifactDaoString);
    }

    public void showQuestList(QuestDAO questDAO) {

        String questDaoString = questDAO.toString();
        questView.showQuestList(questDaoString);
    }

    public void showStudentList(StudentDAO studentDAO) {

        String studentDaoString = studentDAO.toString();
        studentView.showStudentList(studentDaoString);
    }

    public void addStudent(StudentDAO studentDAO) {

        int userID = uiView.getInputInt("Enter student ID: ");
        String name = uiView.getInput("Enter name: ");
        String surname = uiView.getInput("Enter surname: ");
        String email = uiView.getInput("Enter email: ");
        String login = uiView.getInput("Enter login: ");
        String password = uiView.getInput("Enter password: ");
        String className = uiView.getInput("Enter class name: ");

        StudentModel studentModel = new StudentModel(userID, name, surname, email, login, password, className);
        studentDAO.addObject(studentModel);
    }

    public void addQuest(QuestDAO questDAO) {
        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        QuestCategoryModel questCategory = new QuestCategoryModel(uiView.getInput("Enter category: "));

        QuestModel questModel = new QuestModel(title, price, questCategory);
        questDAO.addObject(questModel);
    }

    public void addArtifact(ArtifactDAO artifactDAO) {

        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        String categoryName = uiView.getInput("Enter category: ");
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(categoryName);

        ArtifactModel artifactModel = new ArtifactModel(title, price, artifactCategory);
        artifactDAO.addObject(artifactModel);
    }
}
