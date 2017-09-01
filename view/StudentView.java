package view;

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

        String headline = "\033[1;33mIndex | User ID | Name | Surname | Class ID | Team ID\033[0m\n";
        System.out.println(headline);
        System.out.println(studentDAOString);
    }

}
