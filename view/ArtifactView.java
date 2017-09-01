package view;

import model.ArtifactModel;

public class ArtifactView {

    public void showArtifactList(String artifactDAOString) {

        String headline = "No | Category | Title | Price | Use State";
        System.out.println(headline);
        System.out.println(artifactDAOString);
    }


}
