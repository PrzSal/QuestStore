package view;

import model.ArtifactModel;

public class ArtifactView {

    public void showArtifact(ArtifactModel artifact) {

        System.out.println(artifact);
    }

    public void showArtifactList(String artifactListString) {

        String headline = "Index | Category | Title | Price | Use State";
        System.out.println(headline);
        System.out.println(artifactListString);
    }


}
