package view;

import java.util.LinkedList;

public class WalletView {

    public void showWallet(LinkedList <ArtifactModel> artifactsList, Integer coolCoins) {

        Integer index = 0;
        System.out.println("Colected artifacts: ");

        for (ArtifactModel artifact : artifactsList) {

            System.out.println(index + ". " + artifact);
            index++;
        }

        System.out.println("\n" + "Colected coolCoins: " + coolCoins);
    }
}
