package model;

public class ArtifactModel<T> extends AbstractTask<T> {

    private boolean isUsed = false;
    private Integer amount;

    public ArtifactModel(String title, Integer price, T artifactCategory) {

        this.title = title;
        this.price = price;
        this.category = artifactCategory;
        this.isUsed = false;
        this.amount = 0;
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


        String artifactString = "Category: " + this.artifactCategory.toString() + "Tittle artifact: " + this.title + " Price: " + this.price +  + "State use: " + isUsedState;

        return artifactString;
    }

    public boolean setIsUsed() {

        return this.isUsed = true;
    }

    public Integer getAmount() {

        return this.amount;
    }

    public void setAmount() {

        this.amount++;
    }
}
