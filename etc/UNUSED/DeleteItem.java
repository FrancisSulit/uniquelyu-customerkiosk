package item;

import dao.ItemDAOImpl;
import java.sql.*;
import java.util.Scanner;
import utils.DisplayUtilities;
import utils.ItemUtilities;

public class DeleteItem {

    public static void deleteItemMenu (Scanner scanner) {

        ItemDAOImpl itemDAO = new ItemDAOImpl();

        String input, confirm;
        int itemID;

        boolean deleteSuccess;

        try {

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
            try {

                if (!ItemUtilities.itemExists(itemID)) {
                    System.err.println("Item with ID " + itemID + " does not exist.");
                    System.out.println();

                    DisplayUtilities.returnToMainMenu();
                    return;
                }
            }

            catch (SQLException sqle) {
                System.err.println("Error checking item: " + sqle.getMessage());
            }

            // Ask user to confirm deletion
            System.out.print("Are you sure you want to delete this item? (Y/N): ");
            confirm = scanner.nextLine().trim().toUpperCase();
            System.out.println();

            /* ============================== DELETING ITEM ============================== */

            if (confirm.equals("Y")) {
                
                // Passing the object to the DAO deleteItem function 
                // and returns wether the delete is successful or not
                deleteSuccess = itemDAO.deleteItem(itemID);

                if (deleteSuccess) {
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

        catch (SQLException sqle) {
            System.err.println("Error deleting item: " + sqle.getMessage());
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


    /*

    // Deletes an item with the specified ID
    private static void deleteItemByID (int itemID) {

        String deleteSQL = "DELETE FROM ITEMS WHERE ID = ?";

        int affectedRows;

        try (

            // Connect to Database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL)
        
        ) {


            ============================== PREPARING AND EXECUTING QUERY ==============================
            
            
            // Setting parameter to query and executing query
            pstmt.setInt(1, itemID);
            affectedRows = pstmt.executeUpdate();
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }
    }
    
    */
}