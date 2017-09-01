package controller;

import model.StudentModel;
import java.util.LinkedList;

import model.*;
import view.*;
import dao.*;


public class StudentController {

    private static final String BUY_ARTIFACT = "1";
    private static final String TEAM_BUY_ARTIFACT = "2";
    private static final String SHOW_WALLET = "3";
    private static final String EXIT = "0";

    private static UIView uiView = new UIView();
    private static StudentView studentView = new StudentView();

    public void buyArtifact(StudentModel studentModel, ArtifactDAO artifactDAO) {

        ArtifactView artifactView = new ArtifactView();

        LinkedList<ArtifactModel> artifactList = artifactDAO.getObjectList();

        String artifactDAOString = artifactDAO.toString();
        artifactView.showArtifactList(artifactDAOString);

        Integer index = Integer.parseInt(uiView.getInput("Enter index for chosen artifact:"));
        ArtifactModel artifactToBuy = artifactList.get(index + 1);
        Integer artifactPrice = artifactToBuy.getPrice();

        if (artifactPrice < studentModel.getWallet().getCoolCoins()) {

            studentModel.getWallet().addBoughtArtifact(artifactToBuy);
            studentModel.getWallet().removeCoolCoins(artifactPrice);

        }
        else {
            uiView.printMessage("You don't have enought cool coins");
        }

    }

    // public ArtifactModel buyArtifactTogether() {
    //
    //     WalletView walletView = new WalletView();
    //     return;
    // }

    public void showWallet(StudentModel student) {

        WalletController walletController = new WalletController();
        LinkedList<ArtifactModel> artifactModelList = student.getWallet().getArtifactList();
        walletController.showWalletContent(student.getWallet());
    }

    public void startMenu(String operation, StudentModel student, ArtifactDAO artifactDao, QuestDAO questDao) {

        switch(operation) {

        case BUY_ARTIFACT :
            this.buyArtifact(student, artifactDao);
            System.out.println("dupa1");
            uiView.continueButton();
            break;

        case TEAM_BUY_ARTIFACT :
            // ArrayList<MentorModel> mentorSurname = view.getInput("Find mentor by surname: ");
            // this.getMentorBySurname(String mentorSurname)
            System.out.println("dupa2");
            uiView.continueButton();
            break;

        case SHOW_WALLET :
            this.showWallet(student);
            uiView.continueButton();
            break;

        case EXIT:
            break;

         default :
            uiView.printMessage("No option! Try Again!\n");
            uiView.continueButton();
        }

    }

    public void startStudentController(StudentModel student, ArtifactDAO artifactDao, QuestDAO questDao) {

        String operation;


        do {
            uiView.clearScreen();
            System.out.println("Hello " + student.getName());
            studentView.showMenu();
            operation = uiView.getInput("Choice option: ");
            uiView.clearScreen();
            this.startMenu(operation, student, artifactDao, questDao);
        } while (!operation.equals(EXIT));

    }
}
