package com.codecool.dream_is_green.model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class WalletModelTest {

    @Test
    public void testGetCoolcoins() {
        WalletModel wallet = new WalletModel();

        Integer expected = 0;
        assertEquals(expected, wallet.getCoolCoins());
    }

    @Test
    public void testRemoveCoolcoins() {
        Integer value = 500;
        WalletModel wallet = new WalletModel();

        Integer expected = - value;
        wallet.removeCoolCoins(value);
        assertEquals(expected, wallet.getCoolCoins());

    }

    @Test
    public void isArtifactListEmptyOnInitialize() {
        WalletModel wallet = new WalletModel();

        assertTrue(wallet.getArtifactList().isEmpty());
    }

    @Test
    public void testAddArtifacts() {
        WalletModel wallet = new WalletModel();
        ArtifactModel artifact1 = new ArtifactModel("title1", 500, new ArtifactCategoryModel("catName"));
        ArtifactModel artifact2 = new ArtifactModel("title2", 100, new ArtifactCategoryModel("catName"));
        wallet.addBoughtArtifact(artifact1);
        wallet.addBoughtArtifact(artifact2);
        wallet.addBoughtArtifact(artifact1);

        Integer expectedSize = 2;
        Integer size = wallet.getArtifactList().size();
        assertEquals(expectedSize, size);
    }

    @Test
    public void testSetCoolcoins() {
        WalletModel wallet = new WalletModel();

        Integer value = 200;
        wallet.setCoolCoins(value);

        assertEquals(value, wallet.getCoolCoins());
    }

}
