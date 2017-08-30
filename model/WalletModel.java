// package model;
import java.util.LinkedList;

public class WalletModel{
    private Integer coolCoins;
    private LinkedList<Artifact> artifactsList;

    public WalletModel(Integer coolCoins){
        this.coolCoins = 0;
        this.artifactsList = new LinkedList<Artifact>();
    }
}
