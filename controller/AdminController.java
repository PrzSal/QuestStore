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

    public void editMentor(MentorModel mentor) {

        UIView view = new UIView();

        String mentorInfo = mentor.toString();
        view.clearScreen();
        view.printMessage(mentorInfo);
        view.printMessage("1) Edit email.\n2) Edit mentor class.");
        String option = view.getInput("Choose option: ");

        Boolean done = false;

        while(!done)
            if(option.equals("1")) {
                String email = view.getInput("Enter mentor email: ");
                mentor.setEmail(email);
                done = true;
            }
            else if(option.equals("2")) {
                String classID = view.getInput("Enter mentor class ID: ");
                mentor.setClassID(classID);
                done = true;
            }
            else {
                view.printMessage("Choose 1 or 2.");
                view.continueButton();
                view.clearScreen();
                view.printMessage(mentorInfo);
                view.printMessage("1) Edit email.\n2) Edit mentor class.");
                option = view.getInput("Choose option: ");
            }

    }


    // public static void main(String[] args) {
    //     createMentor();
    //
    // }


}
