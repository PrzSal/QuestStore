package com.codecool.dream_is_green.view;

import java.util.Scanner;

public class UIView {

    private Scanner input = new Scanner(System.in);

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getInput(String message) {

        System.out.print(message);
        return input.nextLine().trim();
    }

    public int getInputInt(String message) {

        System.out.print(message);
        int userInput = Integer.parseInt(input.nextLine());
        return userInput;
    }

    public void pressToContinue() {

        System.out.print("Press enter to continue ...");
        input.nextLine();
    }

    public void clearScreen() {



//        for (int i = 0; i < 30; i++) {
//            System.out.println();
//        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
