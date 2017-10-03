package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.ArtifactDAO;
import com.codecool.dream_is_green.dao.QuestDAO;
import com.codecool.dream_is_green.dao.StudentDAO;
import com.codecool.dream_is_green.enums.MentorEnum;
import com.codecool.dream_is_green.view.*;


import java.lang.NumberFormatException;

public class MentorController {

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
        } while (operation != 0);

    }

    public void chooseOption(int operation) {

        MentorEnum choice = MentorEnum.values()[operation];

        switch(choice) {

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

        QuestDAO questDao = new QuestDAO();
        questDao.loadQuest();
        String questDaoString = questDao.toString();
        questView.showQuestList(questDaoString);
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
        QuestDAO questDao = new QuestDAO();
        questDao.insertQuest(title, price, questCategoryStr);
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
