package login.test;

import java.sql.*;

public class ItemDelete {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";      
    private static final String DB_PASSWORD = "pass123";      

    public static int delete(int itemID) {
        
        String checkQuery; 
        String deleteQuery;
        
        checkQuery = "SELECT ID FROM ITEMS WHERE ID = ?";
        deleteQuery = "DELETE ID FROM ITEMS WHERE ID = ?";

        try {
            
            Connection connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                
            
            PreparedStatement checkStmnt = connect.prepareStatement(checkQuery);
            
            
            checkStmnt.setInt(1, itemID);
            try (ResultSet result = checkStmnt.executeQuery()) {
                if (!result.next()) {
                    return 0; // item not found
                }
            }

            try (PreparedStatement deleteStmnt = connect.prepareStatement(deleteQuery)) {
                
                deleteStmnt.setInt(1, itemID);
                deleteStmnt.executeUpdate();
                return 1; // item deleted
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // errors
        }
    }
}
