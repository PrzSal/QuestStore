package com.codecool.dream_is_green.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletModelTest {

    private WalletModel wallet;

    @BeforeEach
    public void initWallet() {
        this.wallet = new WalletModel();
    }

    @Test
    public void testGetCoolcoins() {
        Integer expected = 0;
        assertEquals(expected, wallet.getCoolCoins());
    }

    @Test
    public void testRemoveCoolcoins() {
        Integer value = 500;

        Integer expected = - value;
        wallet.removeCoolCoins(value);
        assertEquals(expected, wallet.getCoolCoins());

    }

    @Test
    public void isArtifactListEmptyOnInitialize() {
        assertTrue(wallet.getArtifactList().isEmpty());
    }

    @Test
    public void testAddArtifacts() {
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
        Integer value = 200;
        wallet.setCoolCoins(value);

        assertEquals(value, wallet.getCoolCoins());
    }

}