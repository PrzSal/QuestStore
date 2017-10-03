package com.codecool.dream_is_green.view;

import java.util.Scanner;

public class UIView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessage(int number) {
        System.out.println(number);
    }

    public String getInput(String message) {

        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.nextLine().trim();
    }

    public Boolean isType(String testStr, String type) {

        try {
            if (type.equalsIgnoreCase("float")) {
                Float.parseFloat(testStr);
            } else if (type.equalsIgnoreCase("int")) {
                Integer.parseInt(testStr);
            } else if (type.equalsIgnoreCase("double")) {
                Double.parseDouble(testStr);
            }
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public String getInputString(String informationText) {

        Boolean error;
        String userInp;
        Scanner input = new Scanner(System.in);

        do {
            error = false;
            System.out.print(informationText);
            userInp = input.nextLine().trim();
            if(userInp.length() == 0)
                error = true;

            char[] chars = userInp.toCharArray();
            for (char c : chars) {
                if(!Character.isLetter(c))
                    error = true;
            }
            if(error == true) {
                System.err.println("Error: Only letters allowed");
            }
            else
                error = false;

        } while (error == true);
        return userInp;
    }

    public int getInputInt(String informationText) {

        Boolean error;
        String userInp;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print(informationText);
            userInp = input.nextLine().trim();
            if (!this.isType(userInp, "int")) {
                error = true;
                System.err.println("Error: Only numbers allowed");
            } else {
                error = false;
            }
        } while (error == true);
        return Integer.parseInt(userInp);
    }

    public String getInputWithoutSpaces(String informationText) {

        Boolean error;
        String userInp;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print(informationText);
            userInp = input.nextLine().trim();

            if(userInp.length() == 0) {
                error = true;
                System.err.println("Error: empty input");
            }
            else if(userInp.contains(" ")) {
                error = true;
                System.err.println("Error: spaces not allowed");
            }
            else
                error = false;

        } while (error == true);

        return userInp;
    }

    public String getInputAllowSpaces(String informationText) {

        Boolean error;
        String userInp;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print(informationText);
            userInp = input.nextLine().trim();
            if(userInp.trim().length() == 0) {
                error = true;
                System.err.println("Error: empty input\n");
            }
            else
                error = false;

        } while (error == true);
        return userInp;
    }

    public void pressToContinue() {

        Scanner input = new Scanner(System.in);
        System.out.print("Press enter to continue ...");
        input.nextLine();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
