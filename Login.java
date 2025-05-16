package login.test;

import java.sql.*;

public class Login {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";      
    private static final String DB_PASSWORD = "pass123";      

    public static int authenticate(int empID) {
        
        String query;
        
        query = "SELECT ID FROM EMPLOYEES WHERE ID = ?";
        
        try {
                
            Connection connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                
            PreparedStatement stmnt = connect.prepareStatement(query);
            
            stmnt.setInt(1, empID);
            
            try (ResultSet result = stmnt.executeQuery()) {
                
                if (result.next()) {
                    return 1; ///success
                } else {
                    return 0; ///not found
                }
            }
        } catch (SQLException e) {
            
            
            e.printStackTrace();
            return -1;  //errors
        }
    }
}
