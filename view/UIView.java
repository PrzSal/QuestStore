package view;

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

    public void continueButton() {

        System.out.print("Press enter to continue ...");
        input.nextLine();
    }

    public void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
