package login.test;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Driver {
    
    
    public static void main(String[] args) {
        
        
        Scanner scan = new Scanner(System.in);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        
        try {
            
            
            System.out.println("=== Employee Login ===");
            
            System.out.print("Enter your Employee ID: ");
            
            
            int empID = 0;
            empID = scan.nextInt();

            int loginStatus = 0;
            loginStatus = Login.authenticate(empID);

            
            if (loginStatus == 1) {
                System.out.println("Login successful. Welcome Employee #" + empID);

                System.out.print("Enter Item ID to delete: ");
                
                int itemID = 0;
                itemID = scan.nextInt();

                int deleteStatus = 0;
                deleteStatus = ItemDelete.delete(itemID);
                
                if (deleteStatus == 1) {
                    
                    System.out.println("Item ID " + itemID + " deleted successfully.");
                    
                } else if (deleteStatus == 0) {
                    
                    System.out.println("Item ID " + itemID + " not found.");
                    
                } else {
                    
                    
                    System.out.println("Error occurred when deleting an item.");
                }

            } else if (loginStatus == 0) {
                
                
                System.out.println("Invalid Employee ID.  Please Try Again.");
            } else {
                
                
                System.out.println("Error occurred during login.");
            }

            
            
        } catch (Exception e) {
            
            
            System.out.println("Invalid input. Please enter numbers only.");
            
            
        } finally {
            
            try {
                
                
                reader.close();
                
                
            } catch (IOException e) {
                
                
                System.out.println("Error closing BufferedReader");
            }
            scan.close();
        }
    }
}
