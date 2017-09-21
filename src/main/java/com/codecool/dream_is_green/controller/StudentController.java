package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.model.StudentModel;
import java.util.LinkedList;
import java.lang.IndexOutOfBoundsException;
import java.lang.NumberFormatException;

import com.codecool.dream_is_green.model.*;
import com.codecool.dream_is_green.view.*;
import com.codecool.dream_is_green.dao.*;


public class StudentController {

    private static final int SHOW_QUESTS = 1;
    private static final int BUY_ARTIFACT = 2;
    private static final int TEAM_BUY_ARTIFACT = 3;
    private static final int SHOW_WALLET = 4;
    private static final int EXIT = 0;

    private static UIView uiView = new UIView();
    private static StudentView studentView = new StudentView();
    private static QuestView questView = new QuestView();
    private static ArtifactDAO artifactDao = DaoStart.getArtifactDao();
    private static QuestDAO questDao = DaoStart.getQuestDao();


    public void buyArtifact(StudentModel studentModel) {

        ArtifactView artifactView = new ArtifactView();

        LinkedList<ArtifactModel> artifactList = artifactDao.getObjectList();

        String artifactDaoString = artifactDao.toString();
        artifactView.showArtifactList(artifactDaoString);

        Integer index = Integer.parseInt(uiView.getInput("\nEnter index for chosen artifact: "));
        ArtifactModel artifactToBuy = artifactList.get(index - 1);
        Integer artifactPrice = artifactToBuy.getPrice();


        if (artifactPrice < studentModel.getWallet().getCoolCoins()) {

            studentModel.getWallet().addBoughtArtifact(artifactToBuy);
            studentModel.getWallet().removeCoolCoins(artifactPrice);
        }
        else {
            uiView.printMessage("You don't have enought cool coins");
        }

    }

    public void showWallet(StudentModel student) {

        WalletController walletController = new WalletController();
        LinkedList<ArtifactModel> artifactModelList = student.getWallet().getArtifactList();
        walletController.showWalletContent(student.getWallet());
    }

    public void startMenu(int operation, StudentModel student) {

        switch(operation) {

            case SHOW_QUESTS :
                this.showQuestList(questDao);
                uiView.pressToContinue();
                break;

            case BUY_ARTIFACT :
                try {
                    this.buyArtifact(student);
                } catch (IndexOutOfBoundsException e) {
                    uiView.printMessage("Wrong index.");
                } catch (NumberFormatException e) {
                    uiView.printMessage("This is not number");
                }
                uiView.pressToContinue();
                break;

            case TEAM_BUY_ARTIFACT :
                System.out.println("Coming soon ...");
                uiView.pressToContinue();
                break;

            case SHOW_WALLET :
                this.showWallet(student);
                uiView.pressToContinue();
                break;

            case EXIT:
                break;

             default :
                uiView.printMessage("No option! Try Again!\n");
                uiView.pressToContinue();
        }

    }

    public void startStudentController(StudentModel student) {

        int operation;

        do {
            uiView.clearScreen();
            System.out.println("Hello " + student.getName() + "\n");
            studentView.showMenu();
            operation = uiView.getInputInt("Choice option: ");
            uiView.clearScreen();
            this.startMenu(operation, student);
        } while (operation != EXIT);

    }

    public void showQuestList() {

        String questDaoString = questDao.toString();
        questView.showQuestList(questDaoString);
    }
}
