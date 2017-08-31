package model;

import java.util.LinkedList;
import java.lang.NullPointerException;

public class WalletModel {

    private Integer coolCoins;
    private LinkedList<ArtifactModel> artifactsList;

    public WalletModel() {

        this.coolCoins = 0;
        this.artifactsList = new LinkedList<ArtifactModel>();
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

    public void addCoolCoins(Integer amount) {

        this.coolCoins += amount;
    }

    public void addBoughtArtifact(ArtifactModel artifact) {

        for (ArtifactModel artifactElem : this.artifactsList) {

            if(artifactElem.equals(artifact)) {

                artifactElem.setAmount();
            }
        }
        if (!this.artifactsList.contains(artifact)) {

            this.artifactsList.add(artifact);
        }
    }

    public void setIsUsed(String name) throws NullPointerException {

        for (ArtifactModel artifact : this.artifactsList) {

            if (artifact.title.equals(name)) {
                artifact. = true;
            }
        throw new NullPointerException("Artifact not found \n");

        }

    }
}
