package com.codecool.dream_is_green.model;

public class QuestModel extends AbstractTask<QuestCategoryModel> {

    public QuestModel(String name, Integer price, QuestCategoryModel questCategory ) {

        this.title = name;
        this.price = price;
        this.category = questCategory;

    }

    @Override
    public String toString() {

        String stringQuest = category.toString() + " | " + title + " | " + price;

        return stringQuest;
    }
}
