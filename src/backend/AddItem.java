package backend;

import java.sql.*;
import java.util.Scanner;
import utils.DisplayUtilities;

public class AddItem {

    // JDBC database URL, user, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "            ";

    public static void addItemMenu (Scanner scanner) {

        String name;
        String imageLocation;
        String description;
        String category;
        String gender;
        
        String priceInput;
        double price;

        String size;
        String colour;
        String stockStatus;
        
        String qntyInput;
        int quantity;

        String newArrivalInput;
        int newArrivalID = -1;

        try {

            // Load MySQL JDBC Driver so DriverManager can find it
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Clear Screen before showing add item menu
            DisplayUtilities.clearScreen();

            // View items before adding a new item
            ViewItems.viewItemsMenu();


            /* ============================== USER INPUT ============================== */


            // User input the item information
            System.out.print("Enter Item Name: ");
            name = scanner.nextLine();

            System.out.print("Enter Image Location (Path): ");
            imageLocation = scanner.nextLine();

            System.out.print("Enter Description: ");
            description = scanner.nextLine();

            System.out.print("Enter Category: ");
            category = scanner.nextLine();

            System.out.print("Enter Gender: ");
            gender = scanner.nextLine();

            System.out.print("Enter Price: ");
            priceInput = scanner.nextLine();

            try {
                price = Double.parseDouble(priceInput);
            }

            catch (NumberFormatException nfe) {
                System.err.println("Invalid Price Input. Number values only. ");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
                return;
            }

            System.out.print("Enter Size: ");
            size = scanner.nextLine();

            System.out.print("Enter Colour: ");
            colour = scanner.nextLine();

            System.out.print("Enter Stock Status: ");
            stockStatus = scanner.nextLine();

            System.out.print("Enter Quantity: ");
            qntyInput = scanner.nextLine();

            try {
                quantity = Integer.parseInt(qntyInput);
            }

            catch (NumberFormatException nfe) {
                System.err.println("Invalid Quantity Input. Number values only. ");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
                return;
            }

            System.out.print("Enter New Arrival ID (or leave blank for none): ");
            newArrivalInput = scanner.nextLine();

            if (!newArrivalInput.isEmpty()) {

                try {
                    newArrivalID = Integer.parseInt(newArrivalInput);
                }

                catch (NumberFormatException nfe) {
                    System.err.println("Invalid New Arrival ID Input. Number values only. ");
                    System.out.println();

                    DisplayUtilities.returnToMainMenu();
                    return;
                }
            }

            System.out.println();


            /* ============================== START ADDING ITEM ============================== */


            // Insert the item inputted into the database
            insertItem(name, imageLocation, description, category, gender, price, size, colour, stockStatus, quantity, newArrivalID);
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


    // Adds / Inserts an item with specified attributes
    private static void insertItem (String name, String imageLocation, String description, 
                                    String category, String gender, double price, 
                                    String size, String colour, String stockStatus, 
                                    int quantity, int newArrivalID) {
        
        String insertSQL = "INSERT INTO ITEMS (`Name`, `Image Location`, `Description`, Category, Gender, Price, Size, Colour, `Stock Status`, Quantity, `New Arrival ID`) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int affectedRows;

        try (

            // Connect to Database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

        ) {


            /* ============================== PREPARING QUERY ============================== */


            // Setting arguments to query
            pstmt.setString(1, name);
            pstmt.setString(2, imageLocation);
            pstmt.setString(3, description);
            pstmt.setString(4, category);
            pstmt.setString(5, gender);
            
            pstmt.setDouble(6, price);
            
            pstmt.setString(7, size);
            pstmt.setString(8, colour);
            pstmt.setString(9, stockStatus);

            pstmt.setInt(10, quantity);

            if (newArrivalID != -1) {
                pstmt.setInt(11, newArrivalID);
            }

            else {
                pstmt.setNull(11, Types.INTEGER);
            }


            /* ============================== EXECUTING QUERY ============================== */


            // Executing the SQL Query
            affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Item added successfully. ");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
            }

            else {
                System.err.println("Failed to add item. ");
                System.out.println();

                DisplayUtilities.returnToMainMenu();
            }
        }

        catch (SQLException sqle) {
            System.err.println("Error inserting item: " + sqle.getMessage());
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