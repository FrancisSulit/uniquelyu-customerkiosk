/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaaddfunction;

/**
 *
 * @author ciabattacereal
 */
public class Items {
    private Integer itemID;
    private String name;
    private String imgPath;
    private String description;
    private String category;
    private String gender;
    private Double price;
    private String size;
    private String color;
    private String stockStatus;
    private Integer quantity;
    private Integer newArrivalID;
    
    
    Items(Integer itemID, String name, String imgPath, String description, String category, String gender, Double price, String size, String color, String stockStatus, Integer quantity, Integer newArrivalID){
        this.itemID = itemID;
        this.name = name;
        this.imgPath = imgPath;
        this.description=description;
        this.category=category;
        this.gender=gender;
        this.price=price;
        this.size = size;
        this.color = color;
        this.stockStatus = stockStatus;
        this.quantity = quantity;
        this.newArrivalID = newArrivalID;
    }
    
    public Integer getItemID(){return itemID;}
    public String getName(){return name;}
    public String getImgPath(){return imgPath;}
    public String getDescription(){return description;}
    public String getCategory(){return category;}
    public String getGender(){return gender;}
    public Double getPrice(){return price;}
    public String getSize(){return size;}
    public String getColor(){return color;}
    public String getStockStatus(){return stockStatus;}
    public Integer getQuantity(){return quantity;}
    public Integer getNewArrivalID(){return newArrivalID;}
    
    public String toString(){
        return "=>"+
//               this.getItemID + ", "+
               this.getName() + ", "+
               this.getImgPath() + ", "+
               this.getDescription() + ", "+
               this.getCategory()+ ", "+
               this.getGender()+ ", "+
               this.getPrice()+ ", "+
               this.getSize() + ", "+
               this.getColor()+ ", "+
               this.getStockStatus()+ ", "+
               this.getQuantity()+ ", "+
               this.getNewArrivalID();
    }
}
