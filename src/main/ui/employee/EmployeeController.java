package ui.employee;

import java.util.*;
import utils.DisplayUtilities;

public class EmployeeController {

    private final Scanner scanner;
    private final EmployeeItemUI itemUI;


    public EmployeeController (Scanner scanner, EmployeeItemUI itemUI) {
        this.scanner = scanner;
        this.itemUI = itemUI;
    }

    public void launchEmployeeMainMenu () {

        int mainMenuInput = 0;

        // Program loops until user enters 5
        while (mainMenuInput != 5) {

            mainMenuInput = displayMainMenu();

            switch (mainMenuInput) {

                case (1) ->  {
                    itemUI.displayAddItemMenu();
                }

                case (2) ->  {
                    itemUI.displayEditItemMenu();
                }

                case (3) ->  {
                    itemUI.displayAllItemsMenu();
                }

                case (4) ->  {
                    itemUI.displayDeleteItemMenu();
                }

                case (5) ->  {
                }

                default -> {}
            }
        }
    }


    private int displayMainMenu () {

        int mainMenuChoices = 5;
        int mainMenuInput;


        DisplayUtilities.clearScreen();

        System.out.println("============================== MAIN MENU ==============================");
        System.out.println();

        System.out.println("<1> Add Item");
        System.out.println("<2> Edit Item");
        System.out.println("<3> View Items");
        System.out.println("<4> Delete Item");
        System.out.println("<5> Back");
        System.out.println();

        while (true) {

            try {

                System.out.print("Enter Input: ");
                mainMenuInput = scanner.nextInt();
                scanner.nextLine();

                if ((mainMenuInput >= 1) && (mainMenuInput <= mainMenuChoices)) {
                    break;
                }

                else {
                    System.out.println("Invalid Input. Enter 1 of " + mainMenuChoices + " options only.");
                    System.out.println();
                }
            }

            catch (InputMismatchException ime) {
                System.err.println("Invalid Input. Number values only.");
                System.out.println();
                scanner.next();
            }
        }

        System.out.println();

        return mainMenuInput;
    }
}