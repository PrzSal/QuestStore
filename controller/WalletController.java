package controller;

import java.util.LinkedList;
import model.ArtifactModel;
import view.WalletView;

public class WalletController {

    public void showWallet(LinkedList <ArtifactModel> artifactsList) {

        WalletView walletView = new WalletView();
        Integer index = 0;
        String message = "";

        walletView.showLineInWallet("Colected artifacts: ");

        for (ArtifactModel artifact : artifactsList) {

            message = String.valueOf(index) + ". " + artifact;
            walletView.showLineInWallet(message);
            index++;

        }
        message = "\n" + "Colected coolCoins: " + String.valueOf(coolCoins);
        walletView.showLineInWallet(message);
    }
}
