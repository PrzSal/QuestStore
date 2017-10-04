package com.codecool.dream_is_green.model;

public class QuestCategoryModel extends AbstractTaskCategory {

    private String name;

    public QuestCategoryModel(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }
}
