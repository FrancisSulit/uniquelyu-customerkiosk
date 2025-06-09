package utils;

import java.io.IOException;

public class DisplayUtilities {

    public static void clearScreen () {

        try {

            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } 
            
            else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
            
        } catch (IOException | InterruptedException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void returnToMainMenu () {

        // User Input return to Main Menu
        System.out.print("Press Enter to return to Main Menu...");
        
        try {
            System.in.read();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}