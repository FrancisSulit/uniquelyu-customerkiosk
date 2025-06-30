package utils;

import model.Item;

public class ItemDisplayFormatter {

    public static void printItemHeader() {
        System.out.println("============================== ITEM LIST ==============================");
        System.out.println();
    }

    public static void printItem(Item item) {

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

            item.getID(),
            item.getName(),
            item.getCategory(),
            item.getGender(),
            item.getPrice(),
            item.getSize(),
            item.getColour(),
            item.getStockStatus(),
            item.getQuantity()
        );
    }

    public static void printItemFooter() {
        System.out.println();
        System.out.println("=======================================================================");
        System.out.println();
    }
}
