package controller;

import model.StudentModel;
import java.util.LinkedList;

import dao.ArtifactDAO;
import view.ArtifactView;
import view.StudentView;
import view.UIView;
import view.WalletView;

public class StudentController {

    public void buyArtifact(StudentModel studentModel, ArtifactDAO artifactDAO) {
        WalletView walletView = new WalletView();
        ArtifactView artifactView = new ArtifactView();
        ArtifactDAOView artifactDAOView = new ArtifactDAOView();
        UIView uiView = new UIView();

        LinkedList artifactList = artifactDAO.getArtifactsList();

        artifactDAOView.showList(artifactList);

        Integer index = Integer.parseInt(uiView.getInput("Enter index for chosen artifact:"));
        ArtifactModel artifactToBuy = artifactList.get(index);
        Integer artifactPrice = artifactModel.getPrice();

        if (artifactPrice < studentModel.getWallet().getCoolCoins()) {

            studentModel.getWallet().addBoughtArtifact(artifactToBuy);
            studentModel.getWallet().removeCoolCoins(artifactPrice);

        } 
        else {
            uIView.printMessage("You don't have enought cool coins");
        }

    }

    public ArtifactModel buyArtifactTogether() {
        return;
    }

    public ArtifactModel useArtifact() {
        return;
    }

    public void showWallet() {
        studentModel.getWalletModel().showWallet();
    }
}