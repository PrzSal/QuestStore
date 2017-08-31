package view;

public class StudentView {

    public static void showLevel(Integer level) {

        System.out.println(level);
    }

    public static void showExperience(Integer experience) {
        System.out.println(experience);
    }

    public void showMenu() {
        String menu = "1) Create mentor.\n" +
                      "2) Edit mentor.\n" +
                      "3) Create class.\n" +
                      "0) EXIT";

        System.out.println(menu);
    }

}
