package ui.employee;

import java.io.*;
import java.util.Scanner;
import utils.DisplayUtilities;

public class EmployeeLogin {

    private static final String LOGIN_FILE_NAME = "data/LoginDetails.txt";

    public void employeeLoginMenu (Scanner scanner) {

        EmployeeMain employeeMain = new EmployeeMain();

        try {

            String newUsername;
            String newPassword;
            
            String inputUsername;
            String inputPassword;
            
            String storedDetails[];
            
            while (true) {

                // Clear Screen before showing employee login menu
                DisplayUtilities.clearScreen();
                
                File file = new File(LOGIN_FILE_NAME);
                
                // This lets the user (employee) enter new login details
                // If the login details file exists and the login contains any details
                if (!file.exists() || file.length() == 0) {
                    
                    System.out.println("No Login Details found. Please set a new Username and Password");
                    System.out.println();
                    
                    System.out.print("Enter new Username: ");
                    newUsername = scanner.nextLine();
                    
                    System.out.print("Enter new Password: ");
                    newPassword = scanner.nextLine();
                    
                    System.out.println();
                    
                    saveDetails(newUsername, newPassword);
                    
                    System.out.println("Login Details saved. Please restart the program to log in.");
                    
                    break;
                }

                else {
                    
                    System.out.println("Please enter your login details. Type 'exit' to return.");
                    System.out.println();
                    
                    System.out.print("Username: ");
                    inputUsername = scanner.nextLine();
                    if (inputUsername.equalsIgnoreCase("exit")) break;
                    
                    System.out.print("Password: ");
                    inputPassword = scanner.nextLine();
                    if (inputPassword.equalsIgnoreCase("exit")) break;
                    
                    storedDetails = loadDetails();
                    
                    System.out.println();
                    
                    if (storedDetails == null) {
                        System.out.println("Error reading credentials file. ");
                        System.out.println();
                        break;
                    }
                    
                    if (inputUsername.equals(storedDetails[0]) && inputPassword.equals(storedDetails[1])) {
                        employeeMain.employeeMainMenu(scanner);
                        break;
                    }
                    
                    else {
                        System.out.print("Incorrect Username or Password. Press enter to Try again.");
                        
                        try {
                            System.in.read();
                        } catch (IOException e) {
                            System.err.println("Error: " + e.getMessage());
                        }

                        scanner.nextLine();
                        
                        System.out.println();
                    }
                }
            }
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    private static void saveDetails (String username, String password) {

        try (BufferedWriter writer = new BufferedWriter (new FileWriter(LOGIN_FILE_NAME))) {

            writer.write(username);
            writer.newLine();

            writer.write(password);
        }

        catch (IOException ioe) {
            System.err.println("Failed to save login details: " + ioe.getMessage());
        }   
    }


    private static String[] loadDetails () {

        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_FILE_NAME))) {

            String username = reader.readLine();
            String password = reader.readLine();

            return new String[] {username, password};
        }

        catch (IOException ioe) {
            return null;
        }
    }
}