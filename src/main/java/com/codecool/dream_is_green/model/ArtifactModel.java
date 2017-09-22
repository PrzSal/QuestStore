package com.codecool.dream_is_green.model;

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

        String isUsedState;

        if (this.isUsed) {
            isUsedState = "[X]";

        } else {
            isUsedState = "[ ]";
        }

        String artifactString = String.format("%-24s %-12d %-20s %-12s",
                                title, price, category.toString(), isUsedState);

        return artifactString;
    }

    public boolean setIsUsed() {

        return this.isUsed = true;
    }

    public void setAmount() {

        this.amount++;
    }
}
