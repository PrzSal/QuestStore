package com.codecool.dream_is_green.controller;

import java.util.LinkedList;
import com.codecool.dream_is_green.model.*;
import com.codecool.dream_is_green.view.*;

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
