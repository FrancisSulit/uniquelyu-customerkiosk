package utils;

import java.sql.*;

public class ItemUtilities {

    // JDBC constants (or you can centralize them in a Config later)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Reiji0124816*";

    // Checks if an item with a specific ID exists
    public static boolean itemExists (int itemId) {

        String query = "SELECT ID FROM ITEMS WHERE ID = ?";

        boolean exists = false;

        try (

            // Connect to Database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)
        
        ) {


            /* ============================== PREPARING AND EXECUTING QUERY ============================== */


            // Setting parameter to query and executing query
            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();

            // rs.next() returns true if at least one result was found
            exists = rs.next();
        } 
        
        catch (SQLException sqle) {
            System.err.println("Error checking item: " + sqle.getMessage());
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return exists;
    }
}