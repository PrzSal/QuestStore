package controller;

import model.StudentModel;
import java.util.LinkedList;

import model.*;
import dao.ArtifactDAO;
import view.*;

private static UIView view = new UIView();
private static StudentView studentView = new StudentView();

public class StudentController {

    public void buyArtifact(StudentModel studentModel, ArtifactDAO artifactDAO) {

        ArtifactView artifactView = new ArtifactView();
        ArtifactDAOView artifactDAOView = new ArtifactDAOView();
        UIView uiView = new UIView();

        LinkedList<ArtifactModel> artifactList = artifactDAO.getArtifactsList();

        artifactDAOView.showList(artifactList);

        Integer index = Integer.parseInt(uiView.getInput("Enter index for chosen artifact:"));
        ArtifactModel artifactToBuy = artifactList.get(index);
        Integer artifactPrice = artifactToBuy.getPrice();

        if (artifactPrice < studentModel.getWallet().getCoolCoins()) {

            studentModel.getWallet().addBoughtArtifact(artifactToBuy);
            studentModel.getWallet().removeCoolCoins(artifactPrice);

        }
        else {
            uiView.printMessage("You don't have enought cool coins");
        }

    }

    public ArtifactModel buyArtifactTogether() {

        WalletView walletView = new WalletView();
        return;
    }

    public void showWallet(StudentModel studentModel) {
        studentModel.getWalletModel().showWallet();
    }

    public void startMenu(String operation, ArtifactDAO artifactDao, QuestDAO questDao) {

        switch(operation) {

        case CREATE_MENTOR :
            this.createMentor();
            view.continueButton();
            break;

        case EDIT_MENTOR :
            // ArrayList<MentorModel> mentorSurname = view.getInput("Find mentor by surname: ");
            // this.getMentorBySurname(String mentorSurname)
            view.continueButton();
            break;

        case CREATE_CLASS :
            this.createClass();
            view.continueButton();
            break;

        case EXIT:
            break;

         default :
            view.printMessage("No option! Try Again!\n");
            view.continueButton();
        }

    }

    public void startStudentController(ArtifactDAO artifactDao, QuestDAO questDao) {

        String operation;

        do {
            view.clearScreen();
            adminView.showMenu();
            operation = view.getInput("Choice option: ");
            view.clearScreen();
            this.startMenu(operation, artifactDao, questDao);
        } while (!operation.equals(EXIT));

    }
}
