package controller;
import model.MentorModel;
import view.*;

import java.util.Scanner;
import java.util.ArrayList;

public class AdminController {

    public MentorModel createMentor() {


        UIView view = new UIView();

        String name = view.getInput("Enter mentor name: ");
        String surname = view.getInput("Enter mentor surname: ");
        String email = view.getInput("Enter mentor email: ");

        MentorModel mentor = new MentorModel(name, surname, email);

        return mentor;
    }

    public ArrayList<MentorModel> getMentorBySurname(String mentorSurname) {

        ArrayList<MentorModel> mentorList = new ArrayList<MentorModel>();

        MentorModel mentor1 = new MentorModel("Mateusz", "Ostafil", "mati@gmail.com");
        MentorModel mentor2 = new MentorModel("Mateusz", "Steliga", "scooby@gmail.com");
        MentorModel mentor3 = new MentorModel("Agnieszka", "Koszany", "agi@gmail.com");

        mentorList.add(mentor1);
        mentorList.add(mentor2);
        mentorList.add(mentor3);

        ArrayList<MentorModel> searchMentor = new ArrayList<MentorModel>();

        for(MentorModel mentor: mentorList) {
            if(mentor.getSurname().equals(mentorSurname))
                searchMentor.add(mentor);
        }

        return searchMentor;


    }

    // public void editMentor() {
    //
    //     String surname = getInput("Enter mentor surname: ")
    //
    //     ArrayList<MentorModel> searchMentor = new ArrayList<MentorModel>();
    //     for(MentorModel mentor: MentorList) {
    //         if(mentor.getSurame().equals(surname))
    //             searchMentor.add(mentor)
    //
    //     }
    //
    //
    //
    //
    // }



    // public static void main(String[] args) {
    //     createMentor();
    //
    // }


}
