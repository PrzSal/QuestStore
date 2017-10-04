package com.codecool.dream_is_green.view;

public class LevelView {


    public void showLevelList(String levelDaoString) {

        String headline = String.format("\033[3;33m %1s %-16s %-16s\033[0m", "No.", "Level name", "Exp required");

        System.out.println(headline);
        System.out.println(levelDaoString);
    }

}