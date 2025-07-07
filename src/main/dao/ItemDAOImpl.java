package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Item;
import utils.QueryHandler;

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
    public Item getItemByID (int itemID) throws SQLException {

        String query = "SELECT * FROM ITEMS WHERE ID = ?";

        ResultSet rs;

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
                return new Item(
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
                return null;
            }
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




    /* ======================================================================= SEARCH BAR FUNCTIONS ======================================================================= */




    @Override
    public List<Item> getItemByAttribute(String query) throws SQLException {
        
        List<Item> results = new ArrayList<>();

        String[] keywords;
        boolean allMatch;
        String mappedSize;
        String mappedGender;

        boolean wordMatched;

        // Split query into lowercase keywords
        // \\s+ means whitespaces, + means one or more of the preceding element (in this case, whitespace)
        keywords = query.toLowerCase().split("\\s+");

        for (Item item : getAllItems()) {
            
            // Ensure all keywords match at least one field
            allMatch = true;

            for (String word : keywords) {

                // Apply mapping for known size phrases
                mappedSize = QueryHandler.mapSizeKeyword(word);
                mappedGender = QueryHandler.mapGenderKeyword(word);

                wordMatched =
                    (item.getName() != null && item.getName().toLowerCase().contains(word)) ||
                    (item.getCategory() != null && item.getCategory().toLowerCase().contains(word)) ||
                    (item.getSize() != null && item.getSize().toLowerCase().contains(word)) ||
                    (item.getColour() != null && item.getColour().toLowerCase().contains(word)) ||
                    (item.getGender() != null && item.getGender().toLowerCase().contains(word)) ||
                    (mappedSize != null && item.getSize() != null && item.getSize().equalsIgnoreCase(mappedSize)) ||
                    (mappedGender != null && item.getGender() != null && item.getGender().equalsIgnoreCase(mappedGender));

                if (!wordMatched) {
                    allMatch = false;
                    break; // No need to continue if one word didn’t match
                }
            }

            if (allMatch) {
                results.add(item);
            }
        }

        return results;
    }


    @Override
    public Set<String> getAvailableSizes (String category) throws SQLException, NullPointerException {

        String searchSQL = "SELECT DISTINCT Size FROM ITEMS WHERE LOWER(Category) = ?";

        ResultSet rs;
        Set<String> sizes = new HashSet<>();

        try (
            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(searchSQL);
        ) {
            pstmt.setString(1, category.toLowerCase());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                sizes.add(rs.getString("Size").toUpperCase());
            }
        }

        return sizes;
    }

    @Override
    public Set<String> getAvailableColours (String category) throws SQLException, NullPointerException {

        String searchSQL = "SELECT DISTINCT Colour FROM ITEMS WHERE LOWER(Category) = ?";

        ResultSet rs;
        Set<String> colours = new HashSet<>();

        try (
            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(searchSQL);
        ) {
            pstmt.setString(1, category.toLowerCase());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                colours.add(rs.getString("Colour").toLowerCase());
            }
        }

        return colours;
    }




    /* ======================================================================= CATEGORY SEARCH ======================================================================= */




    @Override
    public List<Item> getItemByGenderCategory (String gender, String category) throws SQLException {

        StringBuilder sql = new StringBuilder("SELECT * FROM ITEMS WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        ResultSet rs;
        List<Item> results;

        if (gender != null && !gender.isEmpty()) {
            sql.append(" AND gender = ?");
            parameters.add(gender);
        }

        if (category != null && !category.isEmpty()) {
            sql.append(" AND category = ?");
            parameters.add(category);
        }

        try (
            // Connect to Database
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ) {
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            rs = pstmt.executeQuery();
            results = new ArrayList<>();

            while (rs.next()) {
                results.add(new Item(
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

        return results;
    }
}