package models;

public class ArtifactModel extends AbstractTask<T> {

    private boolean isUsed = false;
    private Integer amount;

    public ArtifactModel(String tittle, Integer price, T artifactCategory) {

        this.tittle = tittle;
        this.price = price;
        this.artifactCategory = artifactCategory;
        this.isUsed = false;
        this.amount = 0
    }

    @Override
    public String toString() {

        String isUsedState = "";

        if (this.isUsed == true) {
            isUsedState = "[X]";
        }

        else {
            isUsedState = "[ ]";
        }


        String artifactString = "Tittle artifact: " + this.tittle + " Price: " + this.price + "State use: " + isUsedState;

        return artifactString;
    }

    public boolean setIsUsed() {

        return this.isUsed = true;
    }

    public Integer getAmount() {

        return this.amount;
    }
}
