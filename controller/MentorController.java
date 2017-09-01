package controller;

import model.*;
import dao.*;
import view.*;

import java.lang.NumberFormatException;

public class MentorController {

    private static final String ADD_STUDENT = "1";
    private static final String ADD_QUEST = "2";
    private static final String ADD_ARTIFACT = "3";
    private static final String EXIT = "0";

    private static UIView uiView = new UIView();
    private static MentorView mentorView = new MentorView();

    public void startMentorController(StudentDAO studentDAO, QuestDAO questDAO, ArtifactDAO artifactDAO) {

        String operation = "";

     do {
            uiView.clearScreen();
            mentorView.printMenu();
            operation = uiView.getInput("Choice option: ");
            chooseOption(operation, studentDAO, questDAO, artifactDAO);
        } while (!operation.equals(EXIT));

    }

    public void chooseOption(String operation, StudentDAO studentDAO,
                             QuestDAO questDAO, ArtifactDAO artifactDAO) {

        switch(operation) {

            case "1":
                uiView.clearScreen();
                addStudent(studentDAO);
                break;

            case "2":
                uiView.clearScreen();
                try {
                    addQuest(questDAO);
                } catch (NumberFormatException e) {
                    uiView.printMessage("This is not integer number");
                    uiView.continueButton();
                }
                break;

            case "3":
                uiView.clearScreen();
                try {
                    addArtifact(artifactDAO);
                } catch (NumberFormatException e) {
                    uiView.printMessage("This is not integer number");
                    uiView.continueButton();
                }
                break;

            case "EXIT":
                break;

            default:
                uiView.printMessage("No option! Try Again!\n");
        }
    }

    public void printMenu() {

    }

    public void addStudent(StudentDAO studentDAO) {

        String name = uiView.getInput("Enter name: ");
        String surname = uiView.getInput("Enter surname: ");
        String email = uiView.getInput("Enter email: ");
        String login = uiView.getInput("Enter login: ");
        String password = uiView.getInput("Enter password: ");

        StudentModel studentModel = new StudentModel(name, surname, email, login, password);
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
