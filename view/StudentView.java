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
                      "1) Buy artifact.\n" +
                      "2) Buy artifact in team.\n" +
                      "3) Show wallet.\n" +
                      "0) EXIT";

        System.out.println(menu);
    }

}
