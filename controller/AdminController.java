package controller;
import model.MentorModel;
import view.*;

import java.util.Scanner;

public class AdminController {

    public MentorModel createMentor() {

        String name = UIView.getInput("Enter mentor name: ");
        String surname = UIView.getInput("Enter mentor surname: ");
        String email = UIView.getInput("Enter mentor email: ");

        MentorModel mentor = new MentorModel(name, surname, email);

        return mentor;
    }

    // public ArrayList<MentorModel> searchMentorBySurname() {
    //
    // }
    //
    //
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
