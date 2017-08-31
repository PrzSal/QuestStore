package models;

public class ArtifactModel extends AbstractTask<T> {

    private boolean isUsed;

    public ArtifactModel(String tittle, Integer price, T artifactCategory) {

        this.tittle = tittle;
        this.price = price;
        this.artifactCategory = artifactCategory;
        this.isUsed = false;
    }
}
