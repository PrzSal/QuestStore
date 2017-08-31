package controller;

import model.*;
import dao.*;
import view.*;

public class MentorController {


    public void startMentorController(StudentDAO studentDAO, QuestDAO questDAO, ArtifactDAO artifactDAO) {

        UIView uiView = new UIView();
        MentorView mentorView = new MentorView();
        String operation = "";

     do {
            mentorView.printMenu();
            operation = uiView.getInput("Choice option: ");
            chooseOption(operation, studentDAO, questDAO, artifactDAO);
        } while (!operation.equals("0"));
 
    }

    public void chooseOption(String operation, StudentDAO studentDAO, 
                             QuestDAO questDAO, ArtifactDAO artifactDAO) {
 
        UIView uiView = new UIView();

        switch(operation) {
 
            case "1":
                addStudent(studentDAO);
                break;
 
            case "2":
                addQuest(questDAO);
                break;
 
            case "3":
                addArtifact(artifactDAO);
                break;
 
            case "0":
                break;
 
            default:
                uiView.printMessage("No option! Try Again!\n");
        }
    }

    public void printMenu() {

    }

    public void addStudent(StudentDAO studentDAO) {

        UIView uiView = new UIView();

        String name = uiView.getInput("Enter name: ");
        String surname = uiView.getInput("Enter surname: ");
        String email = uiView.getInput("Enter email: ");
        String login = uiView.getInput("Enter login: ");
        String password = uiView.getInput("Enter password: ");

        StudentModel studentModel = new StudentModel(name, surname, email, login, password);
        studentDAO.addObject(studentModel);
    }

    public void addQuest(QuestDAO questDAO) {
        UIView uiView = new UIView();

        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        QuestCategoryModel questCategory = new QuestCategoryModel(uiView.getInput("Enter category: "));

        QuestModel questModel = new QuestModel(title, price, questCategory);
        questDAO.addObject(questModel);
    }

    public void addArtifact(ArtifactDAO artifactDAO) {
        UIView uiView = new UIView();

        String title = uiView.getInput("Enter title: ");
        Integer price = Integer.parseInt(uiView.getInput("Enter price: "));
        String categoryName = uiView.getInput("Enter category: ");
        ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(categoryName);

        ArtifactModel artifactModel = new ArtifactModel(title, price, artifactCategory);
        artifactDAO.addObject(artifactModel);
    }
}