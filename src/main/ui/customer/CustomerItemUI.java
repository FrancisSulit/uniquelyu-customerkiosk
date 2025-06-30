package ui.customer;

import java.sql.SQLException;
import static java.util.Collections.emptyList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Item;
import service.ItemService;
import utils.DisplayUtilities;
import utils.ItemDisplayFormatter;
import utils.QueryHandler;

public class CustomerItemUI {

    Scanner scanner;

    ItemService itemService;

    public CustomerItemUI (Scanner scanner, ItemService itemService) {
        this.scanner = scanner;
        this.itemService = itemService;
    }



    public void displayCategorySearchMainMenu () {

        String gender;
        String category;
        String searchQueryInput;

        List<Item> results;

        gender = this.displayCategoryGenderMenu();
        gender = QueryHandler.mapGenderKeyword(gender);
        category = this.displayCategoryClothingTypeMenu(gender);

        System.out.println();

        searchQueryInput = gender + " " + category;

        /* ============================== RETRIEVING ITEMS ============================== */

        // Retrieve Items that match the attribute typed in searchQueryInput
        try { results = itemService.getItemByAttribute(searchQueryInput); }
        
        catch (SQLException sqle) {
            results = emptyList();
            System.out.println("Error: " + sqle.getMessage());
        }

        /* ============================== DISPLAYING ITEMS ============================== */

        // No Matching Items
        if (results.isEmpty()) {

            ItemDisplayFormatter.printItemHeader();
            System.out.println("No matching items.");
            ItemDisplayFormatter.printItemFooter();
        }
        
        else {
            
            // Displaying the Items
            ItemDisplayFormatter.printItemHeader();

            for (Item item : results) {
                ItemDisplayFormatter.printItem(item);
            }

            ItemDisplayFormatter.printItemFooter();
            
            DisplayUtilities.returnToMainMenu();
        }
    }


    public String displayCategoryGenderMenu () {

        int maxOptions = 3;
        int genderMenuInput;

        String gender;

        DisplayUtilities.clearScreen();

        System.out.println("================================= SELECT A GENDER ======================================");
        System.out.println();

        System.out.println("<1> Male");
        System.out.println("<2> Female");
        System.out.println("<3> Back");
        System.out.println();

        while (true) {

            try {

                System.out.print("Enter Gender: ");
                genderMenuInput = scanner.nextInt();
                scanner.nextLine();

                if ((genderMenuInput >= 1) && (genderMenuInput <= maxOptions)) {
                    break;
                }

                else {
                    System.out.println("Invalid Input. Enter 1 of " + maxOptions + " options only.");
                    System.out.println();
                }
            }

            catch (InputMismatchException ime) {
                System.err.println("Invalid Input. Number values only.");
                System.out.println();
                scanner.next();
            }
        }

        switch (genderMenuInput) {
            case 1 -> gender = "male";
            case 2 -> gender = "female";
            default -> {
                return "";
            }
        }

        return gender;
    }

    public String displayCategoryClothingTypeMenu (String gender) {
        
        int maxOptions = 4;
        int clothingTypeMenuInput;

        String clothingType;

        DisplayUtilities.clearScreen();

        System.out.println("============================== SELECT A CLOTHING TYPE ==================================");
        System.out.println();

        System.out.println("<1> Shirt");
        System.out.println("<2> Bottoms");
        System.out.println("<3> Long Sleeve");
        
        if (gender.equals("F")) {

            maxOptions = 5;

            System.out.println("<4> Dress");
            System.out.println("<5> Back");
        }

        else {
            System.out.println("<4> Back");
        }

        System.out.println();

        while (true) {

            try {

                System.out.print("Enter Clothing Type: ");
                clothingTypeMenuInput = scanner.nextInt();
                scanner.nextLine();

                if ((clothingTypeMenuInput >= 1) && (clothingTypeMenuInput <= maxOptions)) {
                    break;
                }

                else {
                    System.out.println("Invalid Input. Enter 1 of " + maxOptions + " options only.");
                    System.out.println();
                }
            }

            catch (InputMismatchException ime) {
                System.err.println("Invalid Input. Number values only.");
                System.out.println();
                scanner.next();
            }
        }

        switch (clothingTypeMenuInput) {
            case 1 -> clothingType = "shirt";
            case 2 -> clothingType = "bottoms";
            case 3 -> clothingType = "long sleeve";
            case 4 -> clothingType = gender.equals("female") ? "Dress" : "";
            default -> {
                return "";
            }
        }

        return clothingType;
    }













    public void displaySearchBarMenu () {

        String searchQueryInput;

        List<Item> results;

        // Set<String> sizes;
        // Set<String> colours;

        DisplayUtilities.clearScreen();
        displayAllItems();

        System.out.println("Enter a search query (e.g., 'large red shirt', 'yellow shorts'). Type 'exit' to return to Main Menu.");
        System.out.println();

        while (true) {

            /* ============================== USER INPUT ============================== */
            
            // User enters search query
            System.out.print("Enter Search: ");
            searchQueryInput = scanner.nextLine();
            System.out.println();

            // Exits Search Bar Menu when exit is entered
            if (searchQueryInput.equalsIgnoreCase("exit")) break;

            DisplayUtilities.clearScreen();

            /* ============================== RETRIEVING ITEMS ============================== */

            // Retrieve Items that match the attribute typed in searchQueryInput
            try { results = itemService.getItemByAttribute(searchQueryInput); }
            
            catch (SQLException sqle) {
                results = emptyList();
                System.out.println("Error: " + sqle.getMessage());
            }

            /* ============================== DISPLAYING ITEMS ============================== */

            // No Matching Items
            if (results.isEmpty()) {

                ItemDisplayFormatter.printItemHeader();
                System.out.println("No matching items.");
                ItemDisplayFormatter.printItemFooter();
            }
            
            else {

                /*

                // Getting Available Sizes and Colours
                String category = results.stream().map(Item::getCategory).filter(Objects::nonNull).findFirst().orElse("N/A");

                try { sizes = itemService.getAvailableSizesForCategory(category); }
                catch (Exception e) { sizes =  Set.of(); }

                try { colours = itemService.getAvailableColoursForCategory(category); }
                catch (Exception e) { colours = Set.of(); }

                */
               
                // Displaying the Items
                ItemDisplayFormatter.printItemHeader();

                for (Item item : results) {
                    ItemDisplayFormatter.printItem(item);
                }

                ItemDisplayFormatter.printItemFooter();

                // System.out.println("Available Sizes: " + String.join(", ", sizes));
                // System.out.println("Available Colours: " + String.join(", ", colours));
            }
        }
    }
















































    public void displayAllItems () {

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


    public void displayAllItemsMenu () {

        displayAllItems();
        DisplayUtilities.returnToMainMenu();
    }
}