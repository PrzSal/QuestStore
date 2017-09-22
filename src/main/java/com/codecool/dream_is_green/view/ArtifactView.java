package com.codecool.dream_is_green.view;

public class ArtifactView {

    public void showArtifactList(String artifactDAOString) {

        String headline = String.format("\033[3;33m %1s %-12s %-12s %-20s %-12s\033[0m",
                "No.", "Title", "Price", "Category", "Is used");

        System.out.println(headline);
        System.out.println(artifactDAOString);
    }


}
