package ui;

import model.Item;
import service.ItemService;
import service.ItemServiceImpl;
import utils.DisplayUtilities;
import utils.ItemInputHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemUI {

    private final ItemService itemService;

    public ItemUI (Scanner scanner, ItemService itemService) {
        this.scanner = scanner;
        this.itemService = itemService;
    }

    /*

    public void showMainMenu() {

        while (true) {

            DisplayUtilities.clearScreen();

            System.out.println("====== EMPLOYEE MENU ======");
            System.out.println("1. View Items");
            System.out.println("2. Add Item");
            System.out.println("3. Edit Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": viewItems(); break;
                case "2": addItem(); break;
                case "3": editItem(); break;
                case "4": deleteItem(); break;
                case "5": return;
                default: System.out.println("Invalid choice. Try again."); DisplayUtilities.waitForEnter(scanner);
            }
        }
    }

    */

    private static void displayAddItemMenu () {

        Item itemToAdd;

        boolean addSuccess;

        try {

            DisplayUtilities.clearScreen();
            displayAllItems(itemService);

            /* ============================== USER INPUT ============================== */

            // User enters new item's attributes and
            // creating the object that will be passed 
            // to the Service Layer's prepareNewItem function
            itemToAdd = ItemInputHandler.getNewItemInput(scanner);

            /* ============================== ITEM VALIDATION ============================== */

            // Passing the object to the Service Layer
            addSuccess = itemService.prepareNewItem(itemToAdd);

            if (addSuccess) {
                System.out.println("Item added successfully. ");
            }

            else {
                System.err.println("Failed to add item. ");
            }
        }

        catch (IllegalArgumentException iae) {
            System.err.println("Item Validation Error: " + iae.getMessage());
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




    private static void displayEditItemMenu (Scanner scanner, ItemService itemService) {

        int itemIDToEdit;
        Item existingItem;

        Item updatedItem;
        boolean editSuccess;

        try {

            DisplayUtilities.clearScreen();
            displayAllItems(itemService);

            /* ============================== USER INPUT (FETCH TO-EDIT ITEM) ============================== */

            // Ask user for Item ID to edit
            // and fetch the item to edit
            itemIDToEdit = ItemInputHandler.getValidIntInput(scanner, "Enter item ID to edit: ");
            existingItem = itemService.getItemByID(itemIDToEdit);

            // Check if item to edit exists
            if (existingItem == null) {
                System.err.println("Item with ID " + itemIDToEdit + " does not exist. ");
                return;
            }

            /* ============================== USER INPUT (EDIT ITEM) ============================== */

            // Ask user the updated item details 
            // and ensure that the itemID stays the same
            updatedItem = ItemInputHandler.getUpdatedItemInput(scanner, existingItem);
            updatedItem.setID(itemIDToEdit);

            /* ============================== ITEM VALIDATION ============================== */

            // Passing the object to the Service Layer
            editSuccess = itemService.prepareUpdatedItem(updatedItem);

            if (editSuccess) {
                System.out.println("Item with ID " + itemIDToEdit + " updated successfully.");
            }
            
            else {
                System.out.println("No item updated.");
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error updating item: " + sqle.getMessage());
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            System.out.println();
            DisplayUtilities.returnToMainMenu();
        }
    }



    private static void displayAllItems (ItemService itemService) {

        List<Item> items;

        try {

            DisplayUtilities.clearScreen();

            /* ============================== RETRIEVING ITEMS ============================== */

            // Retrieving Items from DAO -> Service
            items = itemService.getAllItems();

            if (items.isEmpty()) {
                System.out.println("No items found in the inventory.");
            }

            else {

                // Printing Item List
                ItemDisplayFormatter.printItemHeader();

                for (Item item : items) {
                    ItemDisplayFormatter.printItem(item);
                }

                ItemDisplayFormatter.printItemFooter();
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error retrieving item: " + sqle.getMessage());
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }





    private static void displayAllItemsMenu (ItemService itemService) {

        displayAllItems(itemService);
        DisplayUtilities.returnToMainMenu();
    }




    private static void displayDeleteItemMenu (Scanner scanner, ItemService itemService) {

        int itemIDtoDelete;
        Item itemToDelete;

        boolean deleteConfirmation;
        boolean deleteSuccess;

        try {

            DisplayUtilities.clearScreen();
            displayAllItems(itemService);

            /* ============================== USER INPUT (FETCH TO-DELETE ITEM) ============================== */

            // Ask user for item ID to delete
            // and fetch the item to delete
            itemIDtoDelete = ItemInputHandler.getValidIntInput(scanner, "Enter item ID to delete: ");
            itemToDelete = itemService.getItemByID(itemIDtoDelete);

            // Check if item to delete exists
            if (itemToDelete == null) {
                System.err.println("Item with ID " + itemIDtoDelete + " does not exist.");
                return;
            }

            // Display the item to delete
            System.out.println("You are about to delete: ");
            ItemDisplayFormatter.printItem(itemToDelete);

            // Ask user to confirm deletion
            deleteConfirmation = ItemInputHandler.confirmDeletion(scanner, itemToDelete.getName());

            if (!deleteConfirmation) {
                System.out.println("Deletion Cancelled.");
                return;
            }

            /* ============================== DELETING ITEM ============================== */

            // Passing the item to delete ID to the Service Layer
            deleteSuccess = itemService.prepareDeleteItem(itemIDtoDelete);

            if (deleteSuccess) {
                System.out.println("Item with ID " + itemIDtoDelete + "deleted successfully.");
            }

            else {
                System.out.println("No item was deleted.");
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error deleting item: " + sqle.getMessage());
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


*/