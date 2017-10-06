package com.codecool.dream_is_green.model;

import java.util.LinkedList;
import java.lang.NullPointerException;

public class WalletModel {

    private Integer coolCoins;
    private LinkedList<ArtifactModel> artifactsList;

    public WalletModel() {

        this.coolCoins = 0;
        this.artifactsList = new LinkedList<>();
    }

    public void removeCoolCoins(Integer amount) {

        this.coolCoins -= amount;
    }

    public Integer getCoolCoins() {

        return this.coolCoins;
    }

    public LinkedList<ArtifactModel> getArtifactList() {

        return this.artifactsList;
    }

    public void addBoughtArtifact(ArtifactModel artifact) {

        if (!this.artifactsList.contains(artifact)) {
            this.artifactsList.add(artifact);
        }
    }

    public void setCoolCoins(Integer coolCoins) {
        this.coolCoins = coolCoins;
    }
}
