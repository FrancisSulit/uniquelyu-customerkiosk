/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package javaaddfunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author ciabattacereal
 */
public class Driver {

    static Scanner sc = new Scanner(System.in);

    static public void displayMenu() {
        System.out.print("""
                           Employee access database confirmed;
                           [1] Add items
                           [2] Exit program
                           """);
        System.out.print("Choose: ");
    }

    static public int selectChoiceMenu() {
        int choice = 0;

        try {
            choice = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            e.getMessage();
            return -1;
        }
        return choice;
    }

    public static int menu() {
        int choice = 0;
        while (true) {
            displayMenu();
            choice = selectChoiceMenu();

            if (choice != -1) {
                break;
            }
        }

        return choice;
    }

    public static void printItemTreeMap(TreeMap<Integer, Items> itemTreeMap) {
        Set<Map.Entry<Integer, Items>> itemEntries = itemTreeMap.entrySet();

        for (Map.Entry<Integer, Items> itemEntry : itemEntries) {
            System.out.println(itemEntry.getKey() + ": " + itemEntry.getValue());
        }
    }

    public static boolean itemKeyExists(TreeMap<Integer, Items> itemTreeMap) {
        Integer itemID = null;
        System.out.print("Enter ID: ");

        while (true) {
            try {
                itemID = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }
            break;
        }

        Boolean itemKeyFound = itemTreeMap.containsKey(itemID);
        return itemKeyFound;
    }

    public static String turnEmptyStringToNull(String str){
        if (str == ""){
            str = "null";
        }
        return str;
    }
    public static void addNewEntries(TreeMap<Integer, Items> itemTreeMap) {
        Integer newItemID = itemTreeMap.lastKey() + 1;

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter Image Path: ");
        String imgPath = sc.nextLine();
        turnEmptyStringToNull(imgPath);
        
        System.out.print("Enter description: ");
        String description = sc.nextLine();
        turnEmptyStringToNull(description);
        
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();
        
        System.out.print("Enter price: ");
        Double price = sc.nextDouble();
        sc.nextLine();
        
        System.out.print("Enter size: ");
        String size = sc.nextLine();
        
        System.out.print("Enter color: ");
        String color = sc.nextLine();
        
        System.out.print("Enter Stock Status: ");
        String stockStatus = sc.nextLine();
        
        System.out.print("Enter Quantity: ");
        Integer quantity = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter New Arrival ID: ");
        Integer newArrivalID = sc.nextInt();
        sc.nextLine();


        itemTreeMap.put(newItemID, new Items(newItemID, name, imgPath, description, category, gender, price, size, color, stockStatus, quantity, newArrivalID));
    }

    public static void main(String[] args) {
        TreeMap<Integer, Items> itemTreeMap = new TreeMap<>();

        //Feel free to change values here to make your MySQL Workbench work
        String url = "jdbc:mysql://localhost:3306/Uniqlo_Kiosk_System";
        String username = "root";
        String password = "Cereal";

        int choice = menu();

        switch (choice) {
            case 1:
                System.out.println("Add Items Selected");

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, username, password);

                    if (con != null) {
                        System.out.println("Connection to database established\n");
                    }

                    LoadItemEntries itemEntries = new LoadItemEntries();
                    itemTreeMap = itemEntries.loadItems(con, itemTreeMap);

                    printItemTreeMap(itemTreeMap);
                    
                    //for debugging purposes
//                    printItemTreeMap(itemTreeMap);
//                    if(itemKeyExists(itemTreeMap) == true){
//                        System.out.println("Item Key already exists, enter another ID");
//                    }

                    addNewEntries(itemTreeMap);
                    
                    printItemTreeMap(itemTreeMap);
                    con.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                break;
            case 2:
                System.out.println("Exiting program");
                break;
            default:
                System.out.println("Error: Choice selected is not an invalid option");
        }
    }
}
