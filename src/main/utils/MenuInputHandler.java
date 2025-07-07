package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInputHandler {

    public static int getValidMenuInput(Scanner scanner, int maxOption, String prompt) {
        
        int input;

        while (true) {

            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                scanner.nextLine(); // clear newline

                if (input >= 1 && input <= maxOption) {
                    return input;
                } 
                else {
                    System.out.println("Invalid Input. Enter input between 1 and " + maxOption + ".");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Number values only.");
                scanner.next();
            }
        }
    }
}