/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaaddfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 *
 * @author ciabattacereal
 */
public class LoadItemEntries implements LoadEntries{
    private int itemID, quantity, newArrivalID;
    private String name, imgPath, description, category, gender, size, color, stockStatus;
    private double price;
    
    public Integer getKey(ResultSet rs){
        Integer itemKey = null;
        try {
            itemKey = rs.getInt("ID");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return itemKey;
    }

    public TreeMap<Integer, Items> loadItems(Connection con, TreeMap<Integer, Items> itemTreeMap) {
        Integer itemKey = null;
        String query = "SELECT * from ITEMS";

        
        try {
            PreparedStatement pStatement = con.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            
            while(rs.next()){
                itemKey = getKey(rs);
                
                itemID = rs.getInt("ID");
                name = rs.getString("Name");
                imgPath = rs.getString("Image");
                description = rs.getString("Description");
                category = rs.getString("Category");
                gender = rs.getString("Gender");
                price = rs.getDouble("Price");
                size = rs.getString("Size");
                color = rs.getString("Colour");
                stockStatus = rs.getString("Stock Status");
                quantity = rs.getInt("Quantity");
                newArrivalID = rs.getInt("New Arrival ID");
                itemTreeMap.put(itemKey, new Items(itemID, name, imgPath, description, category, gender, price, size, color, stockStatus, quantity, newArrivalID));
            }
                
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return itemTreeMap;
    }

}
