import controller.*;
import model.*;
import view.*;
import java.util.ArrayList;
import java.util.LinkedList;



public class Test {

    public static void main(String[] args) {

        UIView view = new UIView();

        // ArrayList<MentorModel> mentorList = new ArrayList<MentorModel>();
        //
        // MentorModel mentor1 = new MentorModel("Mateusz", "Ostafil", "mati@gmail.com");
        // MentorModel mentor2 = new MentorModel("Mateusz", "Steliga", "scooby@gmail.com");
        // MentorModel mentor3 = new MentorModel("Agnieszka", "Koszany", "agi@gmail.com");
        //
        // mentorList.add(mentor1);
        // mentorList.add(mentor2);
        // mentorList.add(mentor3);


        // view.clearScreen();
        AdminController admin = new AdminController();
        // MentorModel mentor4 = admin.createMentor();
        // admin.editMentor(mentor4);
        // System.out.println(mentor4);
        // System.out.println();

        // String mentorSurname = view.getInput("Enter mentor surname: ");
        // ArrayList<MentorModel> mentorBySurname = admin.getMentorBySurname(mentorSurname);
        // System.out.println(mentorBySurname);
        ClassModel klasa = new ClassModel("Dupa");
        System.out.println(klasa);
        System.out.println(klasa);
        ClassModel nowaKlasa = admin.createClass();
        System.out.println(nowaKlasa);


    }
}
