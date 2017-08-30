package controller;

import java.util.Scanner;

public class AdminController {

    public MentorModel createMentor() {

        String name = getInput("Enter mentor name: ")
        String surname = getInput("Enter mentor surname: ")
        String email = getInput("Enter mentor email: ")

        MentorModel mentor = new MentorModel(name, surname, email);

        return MentorModel
    }

    // public static void main(String[] args) {
    //     createMentor();
    //
    // }


}
