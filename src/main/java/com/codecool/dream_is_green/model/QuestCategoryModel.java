package com.codecool.dream_is_green.model;

public class QuestCategoryModel extends AbstractTaskCategory {

    private String name;

    public QuestCategoryModel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }
}
