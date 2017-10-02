package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.model.ArtifactModel;
import com.codecool.dream_is_green.model.WalletModel;
import com.codecool.dream_is_green.view.WalletView;
import java.util.LinkedList;

public class WalletController {

    public void showWalletContent(WalletModel walletModel) {


        LinkedList<ArtifactModel> artifactsList = walletModel.getArtifactList();
        WalletView walletView = new WalletView();
        Integer index = 0;
        String message;

        walletView.showLineInWallet("Collected artifacts: ");

        for (ArtifactModel artifact : artifactsList) {

            message = String.valueOf(index) + ". " + artifact;
            walletView.showLineInWallet(message);
            index++;

        }
        message = "\nCollected coolCoins: " + String.valueOf(walletModel.getCoolCoins());
        walletView.showLineInWallet(message);
    }
}
