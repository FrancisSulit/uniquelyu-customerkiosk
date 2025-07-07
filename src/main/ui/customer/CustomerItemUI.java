package ui.customer;

import java.sql.SQLException;
import static java.util.Collections.emptyList;
import java.util.List;
import java.util.Scanner;
import model.Item;
import service.ItemService;
import utils.DisplayUtilities;
import utils.ItemDisplayFormatter;
import utils.MenuInputHandler;
import utils.QueryHandler;

public class CustomerItemUI {

    private final Scanner scanner;
    private final ItemService itemService;

    public CustomerItemUI(Scanner scanner, ItemService itemService) {
        this.scanner = scanner;
        this.itemService = itemService;
    }

    public void displayCategorySearchMainMenu() {

        String genderInput;
        String gender;
        String category;

        List<Item> results;

        DisplayUtilities.clearScreen();

        genderInput = displayCategoryGenderMenu();
        if (genderInput.isEmpty()) return;

        gender = QueryHandler.mapGenderKeyword(genderInput);
        category = displayCategoryClothingTypeMenu(gender);
        if (category.isEmpty()) return;

        System.out.println();

        try { results = itemService.getItemByGenderCategory(gender, category); }
        catch (SQLException sqle) {
            results = emptyList();
            System.out.println("Error: " + sqle.getMessage());
        }

        if (results.isEmpty()) {
            ItemDisplayFormatter.printItemHeader();
            System.out.println("No matching items.");
            ItemDisplayFormatter.printItemFooter();
        } 
        else {
            ItemDisplayFormatter.printItemHeader();
            results.forEach(ItemDisplayFormatter::printItem);
            ItemDisplayFormatter.printItemFooter();
        }

        DisplayUtilities.returnToMainMenu();
    }

    public void displaySearchBarMenu() {

        String searchQueryInput;

        List<Item> results;

        DisplayUtilities.clearScreen();
        displayAllItems();

        System.out.println("Enter a search query (e.g., 'large red shirt', 'yellow shorts'). Type 'exit' to return to Main Menu.");
        System.out.println();

        while (true) {
            System.out.print("Enter Search: ");
            searchQueryInput = scanner.nextLine().trim();
            System.out.println();

            if (searchQueryInput.equalsIgnoreCase("exit")) break;

            DisplayUtilities.clearScreen();

            try { results = itemService.getItemByAttribute(searchQueryInput); }
            catch (SQLException sqle) {
                results = emptyList();
                System.out.println("Error: " + sqle.getMessage());
            }
            
            if (results.isEmpty()) {
                ItemDisplayFormatter.printItemHeader();
                System.out.println("No matching items.");
                ItemDisplayFormatter.printItemFooter();
            }
            else {
                ItemDisplayFormatter.printItemHeader();
                results.forEach(ItemDisplayFormatter::printItem);
                ItemDisplayFormatter.printItemFooter();
            }
        }
    }




    private String displayCategoryGenderMenu() {

        int choice;

        DisplayUtilities.clearScreen();
        System.out.println("================================= SELECT A GENDER ======================================");
        System.out.println();
        System.out.println("<1> Male");
        System.out.println("<2> Female");
        System.out.println("<3> Back");
        System.out.println();

        choice = MenuInputHandler.getValidMenuInput(scanner, 3, "Enter Gender: ");
        return QueryHandler.mapGenderInput(choice);
    }

    private String displayCategoryClothingTypeMenu(String gender) {

        int maxOptions;
        int choice;

        DisplayUtilities.clearScreen();
        System.out.println("============================== SELECT A CLOTHING TYPE ==================================");
        System.out.println();
        System.out.println("<1> Shirt");
        System.out.println("<2> Bottoms");
        System.out.println("<3> Long Sleeve");

        if (gender.equals("F")) {
            System.out.println("<4> Dress");
            System.out.println("<5> Back");
            maxOptions = 5;
        }
        else {
            System.out.println("<4> Back");
            maxOptions = 4;
        }

        System.out.println();

        choice = MenuInputHandler.getValidMenuInput(scanner, maxOptions, "Enter Clothing Type: ");
        return QueryHandler.mapClothingTypeInput(choice, gender);
    }




    public void displayAllItems () {

        List<Item> items;

        try {
            DisplayUtilities.clearScreen();

            items = itemService.getAllItems();
            if (items.isEmpty()) {
                System.out.println("No items found in the inventory.");
            }
            else {
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
