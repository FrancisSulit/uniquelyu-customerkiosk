import java.io.*;
import java.util.Scanner;

public class Login {

    private static final String LOGIN_FILE_NAME = "Data/LoginDetails.txt";

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);
        
        String newUsername = "";
        String newPassword = "";

        String inputUsername = "";
        String inputPassword = "";

        String storedDetails[] = {};

        while (true) {

            File file = new File(LOGIN_FILE_NAME);

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

                    // Program continues here (Main Menu Loop for Employees

                    break;
                }

                else {

                    System.out.println("Incorrect Username or Password. Try again.");
                    System.out.println();
                }
            }
        }

        scanner.close();
    }

    private static void saveDetails (String username, String password) {

        try (BufferedWriter writer = new BufferedWriter (new FileWriter(LOGIN_FILE_NAME))) {

            writer.write(username);
            writer.newLine();

            writer.write(password);
        }

        catch (IOException ioe) {

            System.out.println("Failed to save login details: " + ioe.getMessage());
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