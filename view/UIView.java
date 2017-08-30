package view;

import java.util.Scanner;

public class UIView {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static String getInput(String message) {

        try(Scanner input = new Scanner(System.in)) {
            System.out.print(message);

            return input.nextLine();
        }
    }

    public static void continueButton() {

        Scanner input = new Scanner(System.in);
        System.out.print("Press enter to continue ...");
        input.nextLine();
    }

    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
