package com.codecool.dream_is_green.view;

public class StudentView {

    public static void showLevel(Integer level) {

        System.out.println(level);
    }

    public static void showExperience(Integer experience) {
        System.out.println(experience);
    }

    public void showMenu() {
        String menu = " Student Menu \n" +
                      "1) Show quests\n" +
                      "2) Buy artifact\n" +
                      "3) Buy artifact in team\n" +
                      "4) Show wallet\n" +
                      "0) EXIT";

        System.out.println(menu);
    }

    public void showStudentList(String studentDAOString) {

        String headline = String.format("\033[3;33m %1s %-8s %-12s %-12s %-24s %-12s %-12s %-12s %-12s \033[0m",
                "No.", "User Id", "Name", "Surname", "Email", "Class name", "Team name", "Login", "Password");

        System.out.println(headline);
        System.out.println(studentDAOString);
    }

}
