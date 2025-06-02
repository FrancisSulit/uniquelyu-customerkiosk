package backend;

import java.sql.*;
import utils.DisplayUtilities;

public class ViewItems {

    // JDBC database URL, user, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "            ";

    public static void viewItemsMenu () {

        String query = "SELECT ID, Name, Category, Gender, Price, Size, Colour, `Stock Status`, Quantity FROM ITEMS";

        try (

            // Connect to database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

        ) {

            // Clear Screen before showing view items menu
            DisplayUtilities.clearScreen();

            // Printing Item List
            System.out.println("============================== ITEM LIST ==============================");
            System.out.println();

            // Iterate through each row in the result set
            while (rs.next()) {

                // %-5d : left-align integer in 5-character width (e.g. '5  ', '12 ', '123', '12345')
                // %-35s : left-align string in 35-character width (pads short names)
                // %-8.2f : left-align decimal with 2 decimal points, total width 8 (e.g. '49.99 ', '12345.99')
                // %-3s : left-align string in 3-character width (e.g. 'M  ')
                // %-15s : left-align string in 15-character width (e.g. 'Red           ')
                // %-12s : left-align string in 12-character width (e.g. 'In Stock   ')
                // %d : integer, no padding

                System.out.printf("ID: %-5d | Name: %-35s | Category: %-15s | Gender: %-5s | Price: Php %-10.2f | Size: %-3s | Colour: %-15s | Stock: %-12s | Qty: %d\n",

                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Category"),
                    rs.getString("Gender"),
                    rs.getDouble("Price"),
                    rs.getString("Size"),
                    rs.getString("Colour"),
                    rs.getString("Stock Status"),
                    rs.getInt("Quantity")
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
}