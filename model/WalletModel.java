// package model;
import java.util.LinkedList;

public class WalletModel{
    private Integer coolCoins;
    private LinkedList<ArtifactModel> artifactsList;

    public WalletModel(Integer coolCoins){

        this.coolCoins = 0;
        this.artifactsList = new LinkedList<ArtifactModel>();
    }

    public void removeCoolCoins(Integer amount){

        this.coolCoins -= amount;
    }

    public void addCoolCoins(Integer amount){

        this.coolCoins += amount;
    }

    public void addBoughtArtifact(ArtifactModel artifact){

        this.artifactsList.add(artifact);
    }

}
