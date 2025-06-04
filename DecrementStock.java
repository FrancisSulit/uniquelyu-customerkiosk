import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DecrementStock {
    
    //change values here, particularly jdbc:mariadb and DB_PASSWORD
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "manager";
    
    private static int quantity;
    private static int itemID;
    
    public static char proceedCheckoutConfirmation(){
        Scanner sc = new Scanner(System.in);
        
        char choice;
        String temp;
        
        System.out.print("Proceed to checkout [Y/N]: ");
        temp = sc.nextLine();
        choice = temp.charAt(0);
        choice = Character.toUpperCase(choice);
        return choice;
    }
    
    
    //set itemID value outside of this class
    public void setItemID(int itemID){
        this.itemID = itemID;
    }
    public static int getItemID(){return itemID;}
    
    public static void updateDatabaseQuantity(Connection conn){
        try{
            String updateQuery = "Update ITEMS set Quantity = " + quantity + " where ID = " + getItemID() + ";";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            ResultSet rs = pstmt.executeQuery();
            
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void DecrementStock() {
        if(proceedCheckoutConfirmation() == 'N'){
            System.out.println("Exiting code...");
            return;
        }
        
        String quantityQuery = "Select Quantity from ITEMS where ID = " + getItemID() +";";
        
        //connect to database
        try {   
            Class.forName("org.mariadb.jdbc.Driver");   //I use mariadb since I use XAMPP. Change this if you use mysql
            
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(quantityQuery);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                quantity = rs.getInt("Quantity");
            }
            
//            //debugging
//            System.out.println("Initial quantity: "+quantity);
//            quantity -= 1;
//            System.out.println("Decremented quantity: "+quantity);
            
            updateDatabaseQuantity(conn);

        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
