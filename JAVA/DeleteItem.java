/*


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteItem {

    // Database Connection Details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Enter item ID: ");

            try {

                int itemID = scanner.nextInt();
                System.out.println();
            }

            catch (InputMismatchException ime) {

                System.err.println("Invalid Input. Number values only.");
                System.out.println();
            }
        }

        

        deleteItemByID(itemID);
    }

    public static void deleteItemByID (int itemID) {

        String deleteSQL = "DELETE FROM ITEMS WHERE ID = ?";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        }
       
        catch (ClassNotFoundException cnfe) {

            System.err.println("Class Not Found: " + cnfe.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, itemID);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {

                System.out.println("Item with ID " + itemID + " deleted successfully.");
            }

            else {

                System.out.println("No item found with ID: " + itemID + ".");
            }
        }

        catch (SQLException sqle) {

            System.err.println("Error Deleting Item: " + sqle.getMessage());
        }
    }
}


*/














/*


import java.sql.*;
import java.util.Scanner;

public class DeleteItem {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver is loaded

            listAllItems();

            System.out.print("\nEnter the ID of the item you want to delete: ");
            String input = scanner.nextLine();

            // Input validation
            if (!input.matches("\\d+")) {
                System.out.println("Invalid ID. Please enter a numeric value.");
                return;
            }

            int itemId = Integer.parseInt(input);

            // Check if item exists
            if (!itemExists(itemId)) {
                System.out.println("Item with ID " + itemId + " does not exist.");
                return;
            }

            // Ask for confirmation
            System.out.print("Are you sure you want to delete this item? (Y/N): ");
            String confirm = scanner.nextLine().trim().toUpperCase();

            if (confirm.equals("Y")) {
                deleteItemById(itemId);
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void listAllItems() {
        String query = "SELECT ID, Name, Price, Size, Colour, `Stock Status`, Quantity FROM ITEMS";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("========== ITEM LIST ==========");
            while (rs.next()) {
                System.out.printf("ID: %-3d | Name: %-25s | Price: $%-6.2f | Size: %-3s | Colour: %-10s | Stock: %-12s | Qty: %d\n",
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getString("Size"),
                        rs.getString("Colour"),
                        rs.getString("Stock Status"),
                        rs.getInt("Quantity"));
            }
            System.out.println("================================");

        } catch (SQLException e) {
            System.err.println("Error retrieving items: " + e.getMessage());
        }
    }

    private static boolean itemExists(int itemId) {
        String query = "SELECT ID FROM ITEMS WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Error checking item: " + e.getMessage());
            return false;
        }
    }

    private static void deleteItemById(int itemId) {
        String deleteSQL = "DELETE FROM ITEMS WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, itemId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Item with ID " + itemId + " deleted successfully.");
            } else {
                System.out.println("No item was deleted.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting item: " + e.getMessage());
        }
    }
}


*/

















/* 


import java.sql.*;
import java.util.Scanner;

public class DeleteItem {

    // JDBC database URL, user, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input, confirm;
        int itemId;

        try {
            // Loads the MySQL JDBC driver so DriverManager can find it
            // Required in older versions of Java and JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Display all items before deletion
            listAllItems();

            System.out.print("\nEnter the ID of the item you want to delete: ");
            input = scanner.nextLine();

            // Validate that input is all digits (regex: \d+ means 1+ digit characters)
            if (!input.matches("\\d+")) {
                System.out.println("Invalid ID. Please enter a numeric value.");
                return;
            }

            itemId = Integer.parseInt(input);

            // Check if the item exists in the DB before attempting to delete
            if (!itemExists(itemId)) {
                System.out.println("Item with ID " + itemId + " does not exist.");
                return;
            }

            // Ask user to confirm the deletion
            System.out.print("Are you sure you want to delete this item? (Y/N): ");
            confirm = scanner.nextLine().trim().toUpperCase();

            if (confirm.equals("Y")) {
                deleteItemById(itemId);
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Displays all items from the ITEMS table
    private static void listAllItems() {
        String query = "SELECT ID, Name, Price, Size, Colour, `Stock Status`, Quantity FROM ITEMS";

        // We use Statement here because the query is static and safe from SQL injection
        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            System.out.println("========== ITEM LIST ==========");

            // Iterate through each row in the result set
            while (rs.next()) {
                // %-25s means: left-align string in 25-character width (for clean table-like output)
                // %-3d means: left-align integer in 3-character width
                // %6.2f means: float with 6-character width, 2 decimals
                System.out.printf("ID: %-3d | Name: %-25s | Price: $%-6.2f | Size: %-3s | Colour: %-10s | Stock: %-12s | Qty: %d\n",
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getString("Size"),
                        rs.getString("Colour"),
                        rs.getString("Stock Status"),
                        rs.getInt("Quantity"));
            }

            System.out.println("================================");

        } catch (SQLException e) {
            System.err.println("Error retrieving items: " + e.getMessage());
        }
    }

    // Checks if an item with a specific ID exists
    private static boolean itemExists(int itemId) {
        String query = "SELECT ID FROM ITEMS WHERE ID = ?";
        boolean exists = false;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();

            // rs.next() returns true if at least one result was found
            exists = rs.next();

        } catch (SQLException e) {
            System.err.println("Error checking item: " + e.getMessage());
        }

        return exists;
    }

    // Deletes an item with the specified ID
    private static void deleteItemById(int itemId) {
        String deleteSQL = "DELETE FROM ITEMS WHERE ID = ?";
        int affectedRows;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL)
        ) {
            pstmt.setInt(1, itemId);
            affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Item with ID " + itemId + " deleted successfully.");
            } else {
                System.out.println("No item was deleted.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting item: " + e.getMessage());
        }
    }
}











*/





import java.sql.*;
import java.util.Scanner;

public class DeleteItem {

    // JDBC database URL, user, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Reiji0124816*";

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String input, confirm;
        int itemId;

        try {

            // Load MySQL JDBC driver so DriverManager can find it
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Display all items before deletion
            listAllItems();

            System.out.print("Enter the ID of the item you want to delete: ");
            input = scanner.nextLine();
            System.out.println();

            // Validate that input is all digits (regex: \d+ means 1+ digit characters)
            if (!input.matches("\\d+")) {

                System.out.println("Invalid ID. Please enter a numeric value.");
                return;
            }

            itemId = Integer.parseInt(input);

            // Check if the item exists in the DB before attempting to delete
            if (!itemExists(itemId)) {

                System.out.println("Item with ID " + itemId + " does not exist.");
                return;
            }

            // Ask user to confirm the deletion
            System.out.print("Are you sure you want to delete this item? (Y/N): ");
            confirm = scanner.nextLine().trim().toUpperCase();
            System.out.println();

            if (confirm.equals("Y")) {
                deleteItemById(itemId);
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Displays all items from the ITEMS table
    private static void listAllItems() {

        String query = "SELECT ID, Name, Price, Size, Colour, `Stock Status`, Quantity FROM ITEMS";

        // We use Statement here because the query is static and safe from SQL injection
        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {

            System.out.println("========== ITEM LIST ==========");
            System.out.println();

            // Iterate through each row in the result set
            while (rs.next()) {

                // %-25s means: left-align string in 25-character width (for clean table-like output)
                // %-3d means: left-align integer in 3-character width
                // %6.2f means: float with 6-character width, 2 decimals
                System.out.printf("ID: %-3d | Name: %-25s | Price: $%-6.2f | Size: %-3s | Colour: %-10s | Stock: %-12s | Qty: %d\n",
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getString("Size"),
                        rs.getString("Colour"),
                        rs.getString("Stock Status"),
                        rs.getInt("Quantity"));
            }

            System.out.println();

            System.out.println("================================");
            System.out.println();

        } catch (SQLException e) {
            System.err.println("Error retrieving items: " + e.getMessage());
        }
    }

    // Checks if an item with a specific ID exists
    private static boolean itemExists(int itemId) {

        String query = "SELECT ID FROM ITEMS WHERE ID = ?";
        boolean exists = false;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)
        ) {

            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();

            // rs.next() returns true if at least one result was found
            exists = rs.next();

        } catch (SQLException e) {
            System.err.println("Error checking item: " + e.getMessage());
        }

        return exists;
    }

    // Deletes an item with the specified ID
    private static void deleteItemById(int itemId) {

        String deleteSQL = "DELETE FROM ITEMS WHERE ID = ?";
        int affectedRows;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL)
        ) {
            
            pstmt.setInt(1, itemId);
            affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Item with ID " + itemId + " deleted successfully.");
            } else {
                System.out.println("No item was deleted.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting item: " + e.getMessage());
        }
    }
}
