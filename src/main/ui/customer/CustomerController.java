package ui.customer;

import java.util.*;
import ui.employee.EmployeeLogin;
import utils.DisplayUtilities;

public class CustomerController {

    Scanner scanner;

    CustomerItemUI itemUI;
    EmployeeLogin employeeLogin;


    public CustomerController (Scanner scanner, CustomerItemUI itemUI, EmployeeLogin employeeLogin) {
        this.scanner = scanner;
        this.itemUI = itemUI;
        this.employeeLogin = employeeLogin;
    }

    public void displayCustomerMainMenu () {

        int mainMenuInput = 0;

        // Program loops until user enters 4
        while (mainMenuInput != 4) {
            mainMenuInput = displayMainMenu(scanner);
            switch (mainMenuInput) {
                case (1) ->  {
                    itemUI.displayCategorySearchMainMenu();
                    break;
                }
                case (2) ->  {
                    itemUI.displaySearchBarMenu();
                    break;
                }
                case (3) ->  {
                    employeeLogin.displayEmployeeLoginMenu();
                }
                case (4) ->  {
                    // Exit
                    break;
                }

                default -> {}
            }
        }
    }

    private static int displayMainMenu (Scanner scanner) {

        int mainMenuChoices = 4;
        int mainMenuInput = 0;

        // Clear Screen before showing main menu
        DisplayUtilities.clearScreen();
        System.out.println("============================== MAIN MENU ==============================");
        System.out.println();
        System.out.println("<1> Browse Categories");
        System.out.println("<2> Enter Search");
        System.out.println("<3> Admin Settings");
        System.out.println("<4> Exit");
        System.out.println();

        while (true) {
            
            try {
                System.out.print("Enter Input: ");
                mainMenuInput = scanner.nextInt();
                scanner.nextLine();

                if ((mainMenuInput >= 1) && (mainMenuInput <= mainMenuChoices)) break;
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