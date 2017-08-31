package controller;

import model.StudentModel;
import java.util.LinkedList;

import model.*;
import dao.ArtifactDAO;
import view.*;

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
}