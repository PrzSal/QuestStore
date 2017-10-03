package com.codecool.dream_is_green.view;

public class MentorView {

    public void printMenu() {

        System.out.println(   "Mentor Menu: \n"
                            + "1. Add new student\n"
                            + "2. Show students\n"
                            + "3. Add new quest\n"
                            + "4. Show quests\n"
                            + "5. Add new artifact\n"
                            + "6. Show artifacts\n"
                            + "7. Mark artifact\n"
                            + "8. Show summary students wallets\n"
                            + "0. Exit");
    }

    public void showMentorList(String mentorDaoString) {

        String headline = String.format("\033[3;33m %1s %-8s %-12s %-12s %-24s %-12s %-12s %-12s \033[0m",
                "No.", "User Id", "Name", "Surname", "Email", "Class name", "Login", "Password");

        System.out.println(headline);
        System.out.println(mentorDaoString);

    }

}
