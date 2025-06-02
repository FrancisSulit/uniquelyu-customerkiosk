package backend;

import java.sql.*;
import java.util.Scanner;
import utils.DisplayUtilities;
import utils.ItemUtilities;

public class DeleteItem {

    // JDBC database URL, user, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "            ";

    public static void deleteItemMenu (Scanner scanner) {

        String input, confirm;
        int itemID;

        try {

            // Load MySQL JDBC Driver so DriverManager can find it
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Clear Screen before showing delete item menu
            DisplayUtilities.clearScreen();

            // Display all items before deletion
            ViewItems.viewItemsMenu();


            /* ============================== USER INPUT ============================== */


            // Enter Item ID to delete
            System.out.print("Enter item ID to delete: ");
            input = scanner.nextLine();
            System.out.println();

            // Validate that input is all digits
            try {
                itemID = Integer.parseInt(input);
            } 
            
            catch (NumberFormatException nfe) {
                System.err.println("Invalid ID. Number values only.");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
                return;
            }

            // Check if the item exists in the DB before attempting to delete
            if (!ItemUtilities.itemExists(itemID)) {
                System.err.println("Item with ID " + itemID + " does not exist.");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
                return;
            }

            // Ask user to confirm deletion
            System.out.print("Are you sure you want to delete this item? (Y/N): ");
            confirm = scanner.nextLine().trim().toUpperCase();
            System.out.println();


            /* ============================== DELETING ITEM ============================== */


            if (confirm.equals("Y")) {
                deleteItemByID(itemID);
            } 
            
            else {
                System.out.println("Deletion cancelled.");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
            }

        }
        
        catch (ClassNotFoundException cnfe) {
            System.err.println("A class was not found: " + cnfe.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }
        
        catch (RuntimeException re) {
            System.err.println("Runtime Error: " + re.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }
    }


    // Deletes an item with the specified ID
    private static void deleteItemByID (int itemID) {

        String deleteSQL = "DELETE FROM ITEMS WHERE ID = ?";

        int affectedRows;

        try (

            // Connect to Database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL)
        
        ) {


            /* ============================== PREPARING AND EXECUTING QUERY ============================== */
            
            
            // Setting parameter to query and executing query
            pstmt.setInt(1, itemID);
            affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Item with ID " + itemID + " deleted successfully.");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
            }
            
            else {
                System.out.println("No item was deleted.");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
            }

        } 
        
        catch (SQLException sqle) {
            System.err.println("Error deleting item: " + sqle.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }
    }
}