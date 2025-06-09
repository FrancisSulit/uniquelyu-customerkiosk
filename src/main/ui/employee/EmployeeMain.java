package ui.employee;

import java.util.*;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import utils.DisplayUtilities;

public class EmployeeMain {

    // private static final Scanner scanner = new Scanner(System.in);

    public void employeeMainMenu (Scanner scanner) {

        EmployeeService employeeService = new EmployeeServiceImpl();

        int mainMenuInput = 0;

        // Program loops until user enters 5
        while (mainMenuInput != 5) {

            mainMenuInput = mainMenuDisplay(scanner);

            switch (mainMenuInput) {

                case (1) ->  {
                    employeeService.addItemMenu(scanner);
                }

                case (2) ->  {
                    employeeService.editItemMenu(scanner);
                }

                case (3) ->  {
                    employeeService.viewItemsMenu();
                    DisplayUtilities.returnToMainMenu();
                }

                case (4) ->  {
                    employeeService.deleteItemMenu(scanner);
                }

                case (5) ->  {
                }

                default -> {}
            }
        }
    }


    /* ============================== MENU FUNCTIONS ============================== */


    private static int mainMenuDisplay (Scanner scanner) {

        int mainMenuChoices = 5;
        int mainMenuInput = 0;

        // Clear Screen before showing main menu
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