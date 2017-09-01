package view;

public class ClassView {


    public void showClassList(String classDaoString) {

        String headline = "\033[3;33m No | Class ID \033[0m\n";
        System.out.println(headline);
        System.out.println(classDaoString);
    }

}
