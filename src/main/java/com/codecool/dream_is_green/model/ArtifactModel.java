package com.codecool.dream_is_green.model;


public class ArtifactModel extends AbstractTask<ArtifactCategoryModel> {

    private Integer isUsed;

    public ArtifactModel() {
        title = "empty";
        price = 0;
    }

    public ArtifactModel(String title, Integer price, ArtifactCategoryModel artifactCategory) {
        super(title, price, artifactCategory);
        this.isUsed = 0;
    }

    public Integer setIsUsed(Integer status) {

        return this.isUsed = status;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

}
