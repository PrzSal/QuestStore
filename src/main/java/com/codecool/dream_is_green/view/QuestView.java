package com.codecool.dream_is_green.view;

public class QuestView {

    public void showQuestList(String questDAOString) {

        String headline = "\033[1;33mIndex | Category | Title | Price\033[0m\n";
        System.out.println(headline);
        System.out.println(questDAOString);
    }


}
