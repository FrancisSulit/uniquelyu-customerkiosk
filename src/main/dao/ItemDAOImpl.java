package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean addItem (Item item) throws SQLException {

        String insertSQL = "INSERT INTO ITEMS (`Name`, `Image Location`, `Description`, Category, Gender, Price, Size, Colour, `Stock Status`, Quantity, `New Arrival ID`) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int affectedRows;

        try (

            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)
            
        ) {

            /* ============================== PREPARING QUERY ============================== */

            // Setting arguments to query
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getImageLocation());
            pstmt.setString(3, item.getDescription());
            pstmt.setString(4, item.getCategory());
            pstmt.setString(5, item.getGender());

            pstmt.setDouble(6, item.getPrice());

            pstmt.setString(7, item.getSize());
            pstmt.setString(8, item.getColour());
            pstmt.setString(9, item.getStockStatus());

            pstmt.setInt(10, item.getQuantity());

            if (item.getNewArrivalID() != -1) {
                pstmt.setInt(11, item.getNewArrivalID());
            }

            else {
                pstmt.setNull(11, Types.INTEGER);
            }

            /* ============================== EXECUTING QUERY ============================== */

            // Executing the SQL Query
            affectedRows = pstmt.executeUpdate();

            // Return true if any rows are affected
            return affectedRows > 0;
        }
    }

    @Override
    public boolean editItem (Item item) throws SQLException {

        String editSQL = "UPDATE ITEMS SET `Name`=?, `Image Location`=?, `Description`=?, `Category`=?, `Gender`=?, `Price`=?, `Size`=?, `Colour`=?, `Stock Status`=?, `Quantity`=? WHERE ID=?";

        int affectedRows;

        try (

            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(editSQL);

        ) {

            /* ============================== PREPARING QUERY ============================== */

            // Setting arguments to query
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getImageLocation());
            pstmt.setString(3, item.getDescription());
            pstmt.setString(4, item.getCategory());
            pstmt.setString(5, item.getGender());

            pstmt.setDouble(6, item.getPrice());

            pstmt.setString(7, item.getSize());
            pstmt.setString(8, item.getColour());
            pstmt.setString(9, item.getStockStatus());

            pstmt.setInt(10, item.getQuantity());
            pstmt.setInt(11, item.getID());

            /* ============================== PREPARING QUERY ============================== */

            // Executing the SQL Query
            affectedRows = pstmt.executeUpdate();

            // Return true if any rows are affected
            return affectedRows > 0;
        }
    }

    @Override
    public List<Item> getAllItems () throws SQLException {

        String searchSQL = "SELECT * FROM ITEMS";

        List<Item> items = new ArrayList<>();

        try (

            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(searchSQL);

        ) {

            // Iterate through each row in the result set
            while (rs.next()) {

                items.add(new Item(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Image Location"),
                    rs.getString("Description"),
                    rs.getString("Category"),
                    rs.getString("Gender"),
                    rs.getDouble("Price"),
                    rs.getString("Size"),
                    rs.getString("Colour"),
                    rs.getString("Stock Status"),
                    rs.getInt("Quantity"),
                    rs.getInt("New Arrival ID")
                ));
            }
        }

        return items;
    }

    @Override
    public boolean deleteItem (int itemID) throws SQLException {

        String deleteSQL = "DELETE FROM ITEMS WHERE ID = ?";

        int affectedRows;

        try (

            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
        
        ) {

            /* ============================== PREPARING AND EXECUTING QUERY ============================== */
            
            // Setting parameter to query and executing query
            pstmt.setInt(1, itemID);
            affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        }
    }




    @Override
    public Item getItem (int itemID) throws SQLException, Exception {

        String query = "SELECT * FROM ITEMS WHERE ID = ?";

        ResultSet rs;
        Item item;

        try (

            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)
        
        ) {

            /* ============================== PREPARING AND EXECUTING QUERY ============================== */

            // Setting parameter to query and executing query
            pstmt.setInt(1, itemID);
            rs = pstmt.executeQuery();

            // Setting the return object to the item found
            if (rs.next()) {

                item = new Item(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Image Location"),
                    rs.getString("Description"),
                    rs.getString("Category"),
                    rs.getString("Gender"),
                    rs.getDouble("Price"),
                    rs.getString("Size"),
                    rs.getString("Colour"),
                    rs.getString("Stock Status"),
                    rs.getInt("Quantity"),
                    rs.getInt("New Arrival ID")
                );
            } 
            
            else {
                throw new Exception("Item with ID " + itemID + " not found.");
            }

            return item;
        }
    }
}