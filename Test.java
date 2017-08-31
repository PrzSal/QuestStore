import controller.*;
import model.*;
import view.*;
import java.util.ArrayList;


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



        AdminController admin = new AdminController();
        MentorModel mentor4 = admin.createMentor();
        String mentorSurname = view.getInput("Enter mentor surname: ");
        ArrayList<MentorModel> mentorBySurname = admin.getMentorBySurname(mentorSurname);
        System.out.println(mentorBySurname);
    }
}
