package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("CallToPrintStackTrace")
public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "DITO NYO PALITANG NG PASSWORD NG SA DATABASE NYO";

    
    // Load MySQL JDBC Driver so DriverManager can find it
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Returns the connection to the Database
    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}