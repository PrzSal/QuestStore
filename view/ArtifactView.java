package view;

public class ArtifactView {

    public void showArtifactList(String artifactDAOString) {

        String headline = "\033[1;33mIndex | Category | Title | Price | Use State\033[0m\n";
        System.out.println(headline);
        System.out.println(artifactDAOString);
    }


}
