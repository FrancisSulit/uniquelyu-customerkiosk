package service;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import model.Item;
import utils.DisplayUtilities;
import utils.ItemInputHandler;


public class EmployeeServiceImpl implements EmployeeService {

    ItemDAO itemDAO = new ItemDAOImpl();


    /* ================================================ ADD ITEM FUNCTION ================================================ */

    
    @Override
    public void addItemMenu (Scanner scanner) {

        Item item;

        boolean addSuccess;

        try {

            // Clear Screen before showing add item menu
            DisplayUtilities.clearScreen();

            // View items before adding a new item
            viewItemsMenu();

            /* ============================== USER INPUT ============================== */

            // User enters new item's attributes and
            // Creating the object that will be passed 
            // to the DAO addItem function
            item = ItemInputHandler.getNewItemInput(scanner);

            /* ============================== ADDING ITEM ============================== */

            // Passing the object to the DAO addItem function 
            // and returns wether the add is successful or not
            addSuccess = itemDAO.addItem(item);

            if (addSuccess) {
                System.out.println("Item added successfully. ");
            }

            else {
                System.err.println("Failed to add item. ");
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error inserting item: " + sqle.getMessage());
        }
        
        catch (RuntimeException re) {
            System.err.println("Runtime Error: " + re.getMessage());
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            System.out.println();
            DisplayUtilities.returnToMainMenu();
        }
    }


    /* ================================================ EDIT ITEM FUNCTION ================================================ */


    @Override
    public void editItemMenu (Scanner scanner) {

        int itemID;
        Item existingItem;

        Item updatedItem;
        boolean editSuccess;

        try {

            // Clear Screen before showing edit item menu
            DisplayUtilities.clearScreen();

            // View Items before choosing what to delete
            viewItemsMenu();

            /* ============================== USER INPUT (FETCH ITEM) ============================== */

            // Ask user for Item ID to edit
            itemID = ItemInputHandler.getValidIntInput(scanner, "Enter item ID to edit: ");
            existingItem = itemDAO.getItem(itemID); 

            // Check if item exists
            if (existingItem == null) {
                System.err.println("Item with ID " + itemID + " does not exist. ");
                return;
            }

            /* ============================== USER INPUT (EDIT ITEM) ============================== */

            // Ask user the updated item details 
            // and ensure that the itemID stays the same
            updatedItem = ItemInputHandler.getUpdatedItemInput(scanner, existingItem);
            updatedItem.setID(itemID);

            /* ============================== EDITING ITEM ============================== */

            // Passing the object of the updated item to the DAO editItem function
            // and returns wether the edit is successful or not
            editSuccess = itemDAO.editItem(updatedItem);

            // System.out.println();

            if (editSuccess) {
                System.out.println("Item with ID " + itemID + " updated successfully.");
            } 
            
            else {
                System.out.println("No item updated.");
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error inserting item: " + sqle.getMessage());
        }

        catch (ClassNotFoundException cnfe) {
            System.err.println("A class was not found: " + cnfe.getMessage());
        }

        catch (RuntimeException re) {
            System.err.println("Runtime Error: " + re.getMessage());
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            System.out.println();
            DisplayUtilities.returnToMainMenu();
        }
    }


    /* ================================================ VIEW ITEMS FUNCTION ================================================ */


    @Override
    public void viewItemsMenu () {

        List<Item> items;

        try {

            // Clear Screen before showing view items menu
            DisplayUtilities.clearScreen();

            items = itemDAO.getAllItems();

            // Printing Item List
            System.out.println("============================== ITEM LIST ==============================");
            System.out.println();

            // Iterate through each row in the result set
            for (int i = 0; i < items.size(); i++) {

                // %-5d : left-align integer in 5-character width (e.g. '5  ', '12 ', '123', '12345')
                // %-35s : left-align string in 35-character width (pads short names)
                // %-8.2f : left-align decimal with 2 decimal points, total width 8 (e.g. '49.99 ', '12345.99')
                // %-3s : left-align string in 3-character width (e.g. 'M  ')
                // %-15s : left-align string in 15-character width (e.g. 'Red           ')
                // %-12s : left-align string in 12-character width (e.g. 'In Stock   ')
                // %d : integer, no padding

                System.out.printf("ID: %-5d | Name: %-35s | Category: %-15s | Gender: %-5s | Price: Php %-10.2f | Size: %-3s | Colour: %-15s | Stock: %-12s | Qty: %d\n",

                    /*
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Category"),
                    rs.getString("Gender"),
                    rs.getDouble("Price"),
                    rs.getString("Size"),
                    rs.getString("Colour"),
                    rs.getString("Stock Status"),
                    rs.getInt("Quantity")
                    */

                   items.get(i).getID(),
                   items.get(i).getName(),
                   items.get(i).getCategory(),
                   items.get(i).getGender(),
                   items.get(i).getPrice(),
                   items.get(i).getSize(),
                   items.get(i).getColour(),
                   items.get(i).getStockStatus(),
                   items.get(i).getQuantity()
                );
            }

            System.out.println();

            System.out.println("=======================================================================");
            System.out.println();
        }

        catch (SQLException sqle) {
            System.err.println("Error retrieving items: " + sqle.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }
    }


    /* ================================================ DELETE ITEM FUNCTION ================================================ */


    @Override
    public void deleteItemMenu (Scanner scanner) {

        int itemID;
        Item item;

        boolean confirm;
        boolean deleteSuccess;

        try {

            // Clear Screen before showing delete item menu
            DisplayUtilities.clearScreen();

            // Display all items before deletion
            viewItemsMenu();

            /* ============================== USER INPUT ============================== */

            // Enter Item ID to delete
            itemID = ItemInputHandler.getItemIDToDelete(scanner);
            System.out.println();


            // Check if the item exists in the DB before attempting to delete
            item = itemDAO.getItem(itemID);

            if (item == null) {
                System.err.println("Item with ID " + itemID + "does not exist.");
                return;
            }

            // Ask user to confirm deletion
            confirm = ItemInputHandler.confirmDeletion(scanner, item.getName());
            System.out.println();

            if (!confirm) {
                System.out.println("Deletion Cancelled.");
                return;
            }

            /* ============================== DELETING ITEM ============================== */
                
            // Passing the object to the DAO deleteItem function 
            // and returns wether the delete is successful or not
            deleteSuccess = itemDAO.deleteItem(itemID);

            if (deleteSuccess) {
                System.out.println("Item with ID " + itemID + " deleted successfully.");
            }
            
            else {
                System.out.println("No item was deleted.");
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error deleting item: " + sqle.getMessage());
        }
        
        catch (RuntimeException re) {
            System.err.println("Runtime Error: " + re.getMessage());
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            System.out.println();
            DisplayUtilities.returnToMainMenu();
        }
    }
}