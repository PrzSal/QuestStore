package com.codecool.dream_is_green.model;

public class QuestModel extends AbstractTask<QuestCategoryModel> {

    public QuestModel(String title, Integer price, QuestCategoryModel questCategory ) {
        super(title, price, questCategory);
    }

    @Override
    public String toString() {
        String stringQuest = String.format("%-24s %-12d %-20s", title, price, category.toString());
        return stringQuest;
    }
}
