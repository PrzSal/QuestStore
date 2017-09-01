package view;

public class MentorView {

    public void printMenu() {

        System.out.println(   "Mentor Menu: \n"
                            + "1. Add new student\n"
                            + "2. Add new quest\n"
                            + "3. Add new artifact\n"
                            + "0. Exit");
    }

    public void showMentorList(String mentorDaoString) {

        String headline = "\033[3;33mNo | User ID | Name | Surname | Email | Class ID | Login | Password\033[0m\n";
        System.out.println(headline);
        System.out.println(mentorDaoString);
    }

}
