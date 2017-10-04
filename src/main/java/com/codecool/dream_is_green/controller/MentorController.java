package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.ArtifactDAO;
import com.codecool.dream_is_green.dao.QuestDAO;
import com.codecool.dream_is_green.dao.StudentDAO;
import com.codecool.dream_is_green.dao.WalletDAO;
import com.codecool.dream_is_green.enums.MentorEnum;
import com.codecool.dream_is_green.model.PreUserModel;
import com.codecool.dream_is_green.model.QuestCategoryModel;
import com.codecool.dream_is_green.model.QuestModel;
import com.codecool.dream_is_green.model.StudentModel;
import com.codecool.dream_is_green.view.*;


import java.lang.NumberFormatException;

class MentorController {

    private static UIView uiView = new UIView();
    private static MentorView mentorView = new MentorView();
    private static StudentView studentView = new StudentView();
    private static QuestView questView = new QuestView();
    private static ArtifactView artifactView = new ArtifactView();

    void startMentorController() {

        int operation;

     do {
            uiView.clearScreen();
            mentorView.printMenu();
            operation = uiView.getInputInt("Choice option: ");

             try {
                 chooseOption(operation);
             } catch (ArrayIndexOutOfBoundsException e) {
                 uiView.printMessage("No option in menu :)");
             }

        } while (operation != 0);
    }

    private void chooseOption(int operation) {

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

            case MARK_ARTIFACT :
                uiView.printMessage("Coming soon, be patient :)");
                uiView.pressToContinue();
                break;

            case SHOW_SUMMARY_STUDENTS_WALLETS :
                this.showSummaryStudentsWallets();
                uiView.pressToContinue();
                break;

            case EXIT:
                break;

            default:
                uiView.printMessage("No option! Try Again!\n");
        }
    }

    private void showArtifactList() {

        ArtifactDAO artifactDao = new ArtifactDAO();
        artifactDao.loadArtifact();
        String artifactDaoString = artifactDao.toString();
        artifactView.showArtifactList(artifactDaoString);
    }

    private void showQuestList() {

        QuestDAO questDao = new QuestDAO();
        questDao.loadQuest();
        String questDaoString = questDao.toString();
        questView.showQuestList(questDaoString);
    }

    private void showStudentList() {

        StudentDAO studentDao = new StudentDAO();
        studentDao.loadStudents();
        String studentDaoString = studentDao.toString();
        studentView.showStudentList(studentDaoString);
    }

    private void addStudent() {

        String name = uiView.getInputString("Enter name: ");
        String surname = uiView.getInputString("Enter surname: ");
        String email = uiView.getInputWithoutSpaces("Enter email: ");
        String login = uiView.getInputWithoutSpaces("Enter login: ");
        String password = uiView.getInputWithoutSpaces("Enter password: ");
        String className = uiView.getInputWithoutSpaces("Enter class name: ");

        StudentDAO studentDao = new StudentDAO();
        PreUserModel preStudent = new PreUserModel(name, surname, email, login, password, className);
        studentDao.insertStudent(preStudent);
    }

    private void addQuest() {

        String title = uiView.getInputAllowSpaces("Enter title: ");
        Integer price = uiView.getInputInt("Enter price: ");
        String questCategoryStr = uiView.getInputAllowSpaces("Enter category: ");
        QuestDAO questDao = new QuestDAO();
        QuestCategoryModel questCategoryModel = new QuestCategoryModel(questCategoryStr);
        QuestModel quest = new QuestModel(title, price, questCategoryModel);
        questDao.insertQuest(quest);
    }

    private void addArtifact() {

        String column = "ArtifactsTable";
        String title = uiView.getInputAllowSpaces("Enter title: ");
        Integer price = uiView.getInputInt("Enter price: ");
        String categoryName = uiView.getInputAllowSpaces("Enter category: ");
        ArtifactDAO artifactDao = new ArtifactDAO();
        artifactDao.insertArtifact(column, title, price, categoryName, 0);
    }

    private void showSummaryStudentsWallets() {
        StudentDAO studentDao = new StudentDAO();
        StudentController studentController = new StudentController();
        WalletDAO walletDAO = new WalletDAO();

        studentDao.loadStudents();
        for (StudentModel student : studentDao.getObjectList()) {
            walletDAO.loadCoolcoinsToWallet(student);
            walletDAO.loadArtifactsToWallet(student);
            uiView.printMessage(student.getFullName());
            studentController.showWallet(student);
        }
    }
}
