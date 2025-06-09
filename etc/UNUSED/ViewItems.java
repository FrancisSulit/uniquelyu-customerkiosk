package item;

import dao.ItemDAOImpl;
import java.sql.*;
import java.util.List;
import utils.DisplayUtilities;

public class ViewItems {

    public static void viewItemsMenu () {

        ItemDAOImpl itemDAO = new ItemDAOImpl();

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
}