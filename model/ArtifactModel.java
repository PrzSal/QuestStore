package model;

public class ArtifactModel extends AbstractTask<ArtifactCategoryModel> {

    private boolean isUsed;
    private Integer amount;

    public ArtifactModel(String title, Integer price, ArtifactCategoryModel artifactCategory) {

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


        String artifactString = this.category.toString() + "Tittle artifact: " +
                                this.title + " Price: " + this.price + "State use: " + isUsedState;

        return artifactString;
    }

    public boolean getIsUsed() {
        return this.isUsed;
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
