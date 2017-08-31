package model;

public class ArtifactCategoryModel extends AbstractTaskCategory {

    public ArtifactCategoryModel(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        String artifactCategoryString = "Artifact category: " + this.name;

        return artifactCategoryString;

    }
}
