package com.codecool.dream_is_green.model;


public class ArtifactModel extends AbstractTask<ArtifactCategoryModel> {

    private Integer isUsed;

    public ArtifactModel(String title, Integer price, ArtifactCategoryModel artifactCategory) {
        super(title, price, artifactCategory);
        this.isUsed = 0;
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

    public Integer getIsUsed() {
        return isUsed;
    }

}
