package com.codecool.dream_is_green.view;

public class QuestView {

    public void showQuestList(String questDAOString) {

        String headline = String.format("\033[3;33m %1s %-12s %-12s %-20s\033[0m",
                "No.", "Title", "Price", "Category");

        System.out.println(headline);
        System.out.println(questDAOString);
    }


}
