package item;

import dao.ItemDAOImpl;
import java.util.Scanner;
import utils.DisplayUtilities;
import utils.ItemUtilities;

public class EditItem {

    public static void editItemMenu (Scanner scanner) {

        ItemDAOImpl itemDAO = new ItemDAOImpl();

        String input;
        int prefillItemID;

        Item prefillItem;

        String name;
        String imageLocation;
        String desc;
        String category;
        String gender;
        double price;
        String size;
        String colour;
        String stockStatus;
        int quantity;

        String newName;
        String newImageLocation;
        String newDesc;
        String newCategory;
        String newGender;

        String newPriceInput;
        double newPrice;
        
        String newSize;
        String newColour;
        String newStockStatus;

        String newQuantityInput;
        int newQuantity;

        Item toPassItem;

        boolean editSuccess;

        try {

            // Clear Screen before showing edit item menu
            DisplayUtilities.clearScreen();

            // View Items before choosing what to delete
            ViewItems.viewItemsMenu();

            /* ============================== USER INPUT (FETCH ITEM) ============================== */

            // Ask user for Item ID to edit
            System.out.print("Enter Item ID to edit: ");
            input = scanner.nextLine().trim();
            System.out.println();


            // Convert input to integer
            try {
                prefillItemID = Integer.parseInt(input);
            }

            catch (NumberFormatException nfe) {
                System.err.println("Invalid Input. Number values only. ");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
                return;
            }


            // Check if item exists
            if (!ItemUtilities.itemExists(prefillItemID)) {
                System.err.println("Item with ID " + prefillItemID + " does not exist. ");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
            }

            /* ============================== USER INPUT (EDIT ITEM) ============================== */

            // Proceed with Edit
            // editItemByID(scanner, itemID);

            // fetch prefill item from database
            prefillItem = itemDAO.getItem(prefillItemID);

            name = prefillItem.getName();
            imageLocation = prefillItem.getImageLocation();
            desc = prefillItem.getDescription();
            category = prefillItem.getCategory();
            gender = prefillItem.getGender();
            price = prefillItem.getPrice();
            size = prefillItem.getSize();
            colour = prefillItem.getColour();
            stockStatus = prefillItem.getStockStatus();
            quantity = prefillItem.getQuantity();
            

            // Prompt user with prefilled fields
            System.out.print("Enter new Name [" + name + "]: ");
            newName = scanner.nextLine().trim();
            if (newName.isEmpty()) newName = name;

            System.out.print("Enter new Image Location [" + imageLocation + "]: ");
            newImageLocation = scanner.nextLine().trim();
            if (newImageLocation.isEmpty()) newImageLocation = imageLocation;

            System.out.print("Enter new Description: ");
            newDesc = scanner.nextLine().trim();
            if (newDesc.isEmpty()) newDesc = desc;

            System.out.print("Enter new Category [" + category + "]: ");
            newCategory = scanner.nextLine().trim();
            if (newCategory.isEmpty()) newCategory = category;

            System.out.print("Enter new Gender [" + gender + "]: ");
            newGender = scanner.nextLine().trim();
            if (newGender.isEmpty()) newGender = gender;

            System.out.print("Enter new Price [" + price + "]: ");
            newPriceInput = scanner.nextLine().trim();
            newPrice = newPriceInput.isEmpty() ? price : Double.parseDouble(newPriceInput);

            System.out.print("Enter new Size [" + size + "]: ");
            newSize = scanner.nextLine().trim();
            if (newSize.isEmpty()) newSize = size;

            System.out.print("Enter new Colour [" + colour + "]: ");
            newColour = scanner.nextLine().trim();
            if (newColour.isEmpty()) newColour = colour;

            System.out.print("Enter new Stock Status [" + stockStatus + "]: ");
            newStockStatus = scanner.nextLine().trim();
            if (newStockStatus.isEmpty()) newStockStatus = stockStatus;

            System.out.print("Enter new Quantity [" + quantity + "]: ");
            newQuantityInput = scanner.nextLine().trim();
            newQuantity = newQuantityInput.isEmpty() ? quantity : Integer.parseInt(newQuantityInput);

            System.out.println();


            // Create object to pass to edit function
            toPassItem = new Item(newName, newImageLocation, newDesc,
                                  newCategory, newGender, newPrice,
                                  newSize, newColour, newStockStatus, newQuantity, -1);

            /* ============================== EDITING ITEM ============================== */

            // Passing the item to edit ID and object to the DAO editItem function
            // and returns wether the edit is successful or not
            editSuccess = itemDAO.editItem(prefillItemID, toPassItem);

            if (editSuccess) {
                System.out.println("Item with ID " + prefillItemID + " updated successfully.");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
            } 
            
            else {
                System.out.println("No item updated.");
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


    /*

    // Edit Item (with Prefill)
    private static void editItemByID (Scanner scanner, int itemID) {

        String editSQL = "SELECT * FROM ITEMS WHERE ID = ?";

        String name;
        String imageLocation;
        String desc;
        String category;
        String gender;
        double price;
        String size;
        String colour;
        String stockStatus;
        int quantity;

        String newName;
        String newImageLocation;
        String newDesc;
        String newCategory;
        String newGender;

        String newPriceInput;
        double newPrice;
        
        String newSize;
        String newColour;
        String newStockStatus;

        String newQuantityInput;
        int newQuantity;

        int affectedRows;

        try (

            // Connect to Database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(editSQL);

        ) {
             This query is for fetching
               the to-be-deleted item's data
               for editing its attributes
            


            ============================== PREPARING AND EXECUTING QUERY ==============================


            pstmt.setInt(1, itemID);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.err.println("Item with ID " + itemID + " not found. ");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
                return;
            }

            // Fetch Current Values
            name = rs.getString("Name");
            imageLocation = rs.getString("Image Location");
            desc = rs.getString("Description");
            category = rs.getString("Category");
            gender = rs.getString("Gender");
            price = rs.getDouble("Price");
            size = rs.getString("Size");
            colour = rs.getString("Colour");
            stockStatus = rs.getString("Stock Status");
            quantity = rs.getInt("Quantity");


            ============================== USER INPUT ==============================


            // Prompt user with prefilled fields
            System.out.print("Enter new Name [" + name + "]: ");
            newName = scanner.nextLine().trim();
            if (newName.isEmpty()) newName = name;

            System.out.print("Enter new Image Location [" + imageLocation + "]: ");
            newImageLocation = scanner.nextLine().trim();
            if (newImageLocation.isEmpty()) newImageLocation = imageLocation;

            System.out.print("Enter new Description: ");
            newDesc = scanner.nextLine().trim();
            if (newDesc.isEmpty()) newDesc = desc;

            System.out.print("Enter new Category [" + category + "]: ");
            newCategory = scanner.nextLine().trim();
            if (newCategory.isEmpty()) newCategory = category;

            System.out.print("Enter new Gender [" + gender + "]: ");
            newGender = scanner.nextLine().trim();
            if (newGender.isEmpty()) newGender = gender;

            System.out.print("Enter new Price [" + price + "]: ");
            newPriceInput = scanner.nextLine().trim();
            newPrice = newPriceInput.isEmpty() ? price : Double.parseDouble(newPriceInput);

            System.out.print("Enter new Size [" + size + "]: ");
            newSize = scanner.nextLine().trim();
            if (newSize.isEmpty()) newSize = size;

            System.out.print("Enter new Colour [" + colour + "]: ");
            newColour = scanner.nextLine().trim();
            if (newColour.isEmpty()) newColour = colour;

            System.out.print("Enter new Stock Status [" + stockStatus + "]: ");
            newStockStatus = scanner.nextLine().trim();
            if (newStockStatus.isEmpty()) newStockStatus = stockStatus;

            System.out.print("Enter new Quantity [" + quantity + "]: ");
            newQuantityInput = scanner.nextLine().trim();
            newQuantity = newQuantityInput.isEmpty() ? quantity : Integer.parseInt(newQuantityInput);

            System.out.println();

             This query is for updating
               the item's attributes
            


            ============================== UPDATING ITEM ==============================


            String updateSQL = "UPDATE ITEMS SET `Name`=?, `Image Location`=?, `Description`=?, `Category`=?, `Gender`=?, `Price`=?, `Size`=?, `Colour`=?, `Stock Status`=?, `Quantity`=? WHERE ID=?";

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                
                // Setting arguments to query
                updateStmt.setString(1, newName);
                updateStmt.setString(2, newImageLocation);
                updateStmt.setString(3, newDesc);
                updateStmt.setString(4, newCategory);
                updateStmt.setString(5, newGender);

                updateStmt.setDouble(6, newPrice);

                updateStmt.setString(7, newSize);
                updateStmt.setString(8, newColour);
                updateStmt.setString(9, newStockStatus);
                
                updateStmt.setInt(10, newQuantity);
                updateStmt.setInt(11, itemID);

                // Executing the SQL Query
                affectedRows = updateStmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Item with ID " + itemID + " updated successfully.");
                    System.out.println();

                    DisplayUtilities.returnToMainMenu();
                } 
                
                else {
                    System.out.println("No item updated.");
                    System.out.println();

                    DisplayUtilities.returnToMainMenu();
                }
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error editing item: " + sqle.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }

        catch (NumberFormatException nfe) {
            System.err.println("Invalid number format: " + nfe.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println();

            DisplayUtilities.returnToMainMenu();
        }
    }

    */
}