package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.model.ArtifactCategoryModel;
import com.codecool.dream_is_green.model.ArtifactModel;
import com.codecool.dream_is_green.model.WalletModel;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WalletControllerTest {

    @Test
    void testShowWalletContent() {
        ArtifactCategoryModel artifactCategoryModel = new ArtifactCategoryModel("catName");
        ArtifactModel artifactModel = new ArtifactModel("title", 1000, artifactCategoryModel);
        WalletModel walletModel = new WalletModel();
        walletModel.addBoughtArtifact(artifactModel);

        WalletController walletController = new WalletController();


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        walletController.showWalletContent(walletModel);

        String expectedOutput = "Collected artifacts: \n" +
                "0. title                    1000         catName              [ ]         \n" +
                "\n" +
                "Collected coolCoins: 0\n";

        assertEquals(expectedOutput, outContent.toString());

    }

}