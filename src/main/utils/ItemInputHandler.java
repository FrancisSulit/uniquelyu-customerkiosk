package utils;

import java.util.Scanner;
import model.Item;
import model.ItemBounds;

public class ItemInputHandler {


    /* ================================================ ADD ITEM METHODS ================================================ */


    public static Item getNewItemInput (Scanner scanner) {
        
        String name;
        String imageLocation;
        String description;
        String category;
        String gender;
        
        double price;

        String size;
        String colour;
        String stockStatus;
        
        int quantity;

        String newArrivalInput;
        int newArrivalID = -1;


        /* ============================== USER INPUT ============================== */

        // User input the new item information
        System.out.print("Enter Item Name: ");
        name = scanner.nextLine().trim();
        name = QueryHandler.capitaliseEachWord(name);

        System.out.print("Enter Image Location (Path): ");
        imageLocation = scanner.nextLine();

        System.out.print("Enter Description: ");
        description = scanner.nextLine();

        category = getValidCategoryInput(scanner, "Enter Category: ");
        gender = getValidGenderInput(scanner, "Enter Gender: ");
        price = getValidDoubleInput(scanner, "Enter Price: ");
        size = getValidSizeInput(scanner, "Enter Size: ");

        System.out.print("Enter Colour: ");
        colour = scanner.nextLine();
        colour = QueryHandler.capitaliseEachWord(colour);

        stockStatus = getValidStockStatusInput(scanner, "Enter Stock Status: ");

        quantity = getValidIntInput(scanner, "Enter Quantity: ");

        System.out.print("Enter New Arrival ID (or leave blank): ");
        newArrivalInput = scanner.nextLine();

        System.out.println();

        if (!newArrivalInput.isEmpty()) {

            try {
                newArrivalID = Integer.parseInt(newArrivalInput);
            }

            catch (NumberFormatException nfe) {
                System.err.println("Invalid New Arrival ID Input. Number values only. ");
                System.err.println("Defaulting to -1.");
                System.out.println();
            }
        }

        return new Item(name, imageLocation, description, category, gender, price, size, colour, stockStatus, quantity, newArrivalID);
    }


    /* ================================================ FOR EDIT ITEM ================================================ */


    public static Item getUpdatedItemInput (Scanner scanner, Item existingItem) {

        String name = promptForUpdate(scanner, "Item Name", existingItem.getName());
        String imageLocation = promptForUpdate(scanner, "Image Location", existingItem.getImageLocation());
        String description = promptForUpdate(scanner, "Description", existingItem.getDescription());

        String category = getValidCategoryUpdate(scanner, "Category", existingItem.getCategory());
        String gender = getValidGenderUpdate(scanner, "Gender", existingItem.getGender());
        double price = getValidDoubleUpdate(scanner, "Price", existingItem.getPrice());
        String size = getValidSizeUpdate(scanner, "Size", existingItem.getSize());
        
        String colour = promptForUpdate(scanner, "Colour", existingItem.getColour());
        
        String stockStatus = getValidStockStatusUpdate(scanner, "Stock Status", existingItem.getStockStatus());
        
        int quantity = getValidIntUpdate(scanner, "Quantity", existingItem.getQuantity());
        int newArrivalID = getValidIntUpdate(scanner, "New Arrival ID", existingItem.getNewArrivalID());

        System.out.println();

        return new Item(name, imageLocation, description, category, gender,
                        price, size, colour, stockStatus, quantity, newArrivalID);
    }


    /* ================================================ GENERAL ITEM FUNCTION ================================================ */


    public static int getValidIntInput (Scanner scanner, String prompt) {

        String valueInput;
        int value;

        while (true) {

            System.out.print(prompt);
            valueInput = scanner.nextLine().trim();

            try {

                value = Integer.parseInt(valueInput);
                return value;
            } 
            
            catch (NumberFormatException nfe) {
                System.err.println("Invalid Input. Number values only.");
                System.out.println();
            }
        }
    }

    public static double getValidDoubleInput (Scanner scanner, String prompt) {

        String valueInput;
        double value;

        while (true) {

            System.out.print(prompt);
            valueInput = scanner.nextLine().trim();

            try {

                value = Double.parseDouble(valueInput);
                return value;
            }

            catch (NumberFormatException nfe) {
                System.err.println("Invalid Input. Number values only.");
                System.out.println();
            }
        }
    }

    public static String getValidCategoryInput (Scanner scanner, String prompt) {

        String input;

        while (true) {
            
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            input = QueryHandler.capitaliseEachWord(input);

            if (ItemBounds.validCategories.contains(input)) {
                return input;
            }

            System.out.println();
            System.err.println("Invalid Category.");
            System.out.println("Valid Selections:");
            System.out.println();

            for (String selection : ItemBounds.validCategories) {
                System.out.println(selection);
            }

            System.out.println();
        }
    }

    public static String getValidGenderInput (Scanner scanner, String prompt) {

        String input;

        while (true) {
            
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            input = QueryHandler.capitaliseEachWord(input);

            if (ItemBounds.validGenders.contains(input)) {
                return input;
            }

            System.out.println();
            System.err.println("Invalid Gender.");
            System.out.println("Valid Selections:");
            System.out.println();

            for (String selection : ItemBounds.validGenders) {
                System.out.println(selection);
            }

            System.out.println();
        }
    }

    public static String getValidSizeInput (Scanner scanner, String prompt) {

        String input;

        while (true) {
            
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            input = QueryHandler.capitaliseEachWord(input);

            if (ItemBounds.validSizes.contains(input)) {
                return input;
            }

            System.out.println();
            System.err.println("Invalid Size.");
            System.out.println("Valid Selections:");
            System.out.println();

            for (String selection : ItemBounds.validSizes) {
                System.out.println(selection);
            }

            System.out.println();
        }
    }

    public static String getValidStockStatusInput (Scanner scanner, String prompt) {

        String input;

        while (true) {
            
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            input = QueryHandler.capitaliseEachWord(input);

            if (ItemBounds.validStockStatuses.contains(input)) {
                return input;
            }

            System.out.println();
            System.err.println("Invalid Stock Status.");
            System.out.println("Valid Selections:");
            System.out.println();

            for (String selection : ItemBounds.validStockStatuses) {
                System.out.println(selection);
            }

            System.out.println();
        }
    }


    /* ================================================ FOR EDIT ITEM ================================================ */


    private static String promptForUpdate (Scanner scanner, String field, String currentValue) {

        String input;

        System.out.printf("Enter %s [%s]: ", field, currentValue);
        input = scanner.nextLine().trim();
        input = QueryHandler.capitaliseEachWord(input);

        return input.isEmpty() ? currentValue : input;
    }

    private static int getValidIntUpdate (Scanner scanner, String field, int currentValue) {

        String input;

        System.out.printf("Enter %s [%d]: ", field, currentValue);
        input = scanner.nextLine().trim();

        if (input.isEmpty()) return currentValue;

        try {
            return Integer.parseInt(input);
        }

        catch (NumberFormatException nfe) {
            System.err.println("Invalid Input. Keeping current value.");
            System.out.println();
            return currentValue;
        }
    }

    private static double getValidDoubleUpdate (Scanner scanner, String field, double currentValue) {

        String input;

        System.out.printf("Enter %s [%.2f]: ", field, currentValue);
        input = scanner.nextLine().trim();

        if (input.isEmpty()) return currentValue;

        try {
            return Double.parseDouble(input);
        }

        catch (NumberFormatException nfe) {
            System.err.println("Invalid Input. Keeping current value.");
            System.out.println();
            return currentValue;
        }
    }

    private static String getValidCategoryUpdate(Scanner scanner, String field, String currentValue) {
        
        String input;

        System.out.printf("Enter %s [%s]: ", field, currentValue);
        input = scanner.nextLine().trim();

        // If input is blank, keep current value
        if (input.isEmpty()) {
            return currentValue;
        }

        input = QueryHandler.capitaliseEachWord(input);

        if (ItemBounds.validCategories.contains(input)) {
            return input;
        }

        // If input is invalid, show error but return currentValue
        System.err.println("Invalid Category. Keeping current value.");
        System.out.println();

        return currentValue;
    }

    private static String getValidGenderUpdate(Scanner scanner, String field, String currentValue) {

        String input;

        System.out.printf("Enter %s [%s]: ", field, currentValue);
        input = scanner.nextLine().trim();

        // If input is blank, keep current value
        if (input.isEmpty()) {
            return currentValue;
        }

        input = QueryHandler.capitaliseEachWord(input);

        if (ItemBounds.validGenders.contains(input)) {
            return input;
        }

        // If input is invalid, show error but return currentValue
        System.err.println("Invalid Gender. Keeping current value.");
        System.out.println();

        return currentValue;
    }

    private static String getValidSizeUpdate(Scanner scanner, String field, String currentValue) {

        String input;

        System.out.printf("Enter %s [%s]: ", field, currentValue);
        input = scanner.nextLine().trim();

        // If input is blank, keep current value
        if (input.isEmpty()) {
            return currentValue;
        }

        input = QueryHandler.capitaliseEachWord(input);

        if (ItemBounds.validSizes.contains(input)) {
            return input;
        }

        // If input is invalid, show error but return currentValue
        System.err.println("Invalid Size. Keeping current value.");
        System.out.println();

        return currentValue;
    }

    private static String getValidStockStatusUpdate(Scanner scanner, String field, String currentValue) {
        
        String input;

        System.out.printf("Enter %s [%s]: ", field, currentValue);
        input = scanner.nextLine().trim();

        // If input is blank, keep current value
        if (input.isEmpty()) {
            return currentValue;
        }

        input = QueryHandler.capitaliseEachWord(input);

        if (ItemBounds.validStockStatuses.contains(input)) {
            return input;
        }

        // If input is invalid, show error but return currentValue
        System.err.println("Invalid Stock Status. Keeping current value.");
        System.out.println();

        return currentValue;
    }


    /* ================================================ DELETE ITEM METHODS ================================================ */


    public static int getItemIDToDelete (Scanner scanner) {
        return getValidIntInput(scanner, "Enter item ID to delete: ");
    }

    public static boolean confirmDeletion (Scanner scanner, String itemName) {

        String input;

        System.out.printf("Are you sure you want to delete \"%s\"? (y/n): ", itemName);
        input = scanner.nextLine().trim().toLowerCase();

        return input.equals("y") || input.equals("yes");
    }
}