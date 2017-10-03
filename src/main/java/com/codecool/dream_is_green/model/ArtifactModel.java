package com.codecool.dream_is_green.model;


public class ArtifactModel extends AbstractTask<ArtifactCategoryModel> {

    private Integer isUsed;
    private Integer amount;

    public ArtifactModel(String title, Integer price, ArtifactCategoryModel artifactCategory) {

        this.title = title;
        this.price = price;
        this.category = artifactCategory;
        this.isUsed = 0;
        this.amount = 0;
    }

    @Override
    public String toString() {

        String isUsedState;

        if (this.isUsed == 2) {
            isUsedState = "[USED]";

        } else if (this.isUsed == 1) {
            isUsedState = "[SEND]";

        } else {
            isUsedState = "[ ]";
        }

        String artifactString = String.format("%-24s %-12d %-20s %-12s",
                                title, price, category.toString(), isUsedState);

        return artifactString;
    }

    public Integer setIsUsed(Integer status) {

        return this.isUsed = status;
    }

    public void setAmount() {

        this.amount++;
    }
}
