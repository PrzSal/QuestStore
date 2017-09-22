package com.codecool.dream_is_green.view;

public class ClassView {


    public void showClassList(String classDaoString) {

        String headline = String.format("\033[3;33m %1s %-16s\033[0m", "No.", "Class name");

        System.out.println(headline);
        System.out.println(classDaoString);
    }

}
