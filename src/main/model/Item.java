package model;

public class Item {

    private int ID;
    private final String name;
    private final String imageLocation;
    private final String description;
    private final String category;
    private final String gender;
    private final double price;
    private final String size;
    private final String colour;
    private final String stockStatus;
    private final int quantity;
    private final int newArrivalID;


    // Constructor for adding
    public Item(String name, String imageLocation, String description, 
                String category, String gender, double price, 
                String size, String colour, String stockStatus, 
                int quantity, int newArrivalID) {

        this.name = name;
        this.imageLocation = imageLocation;
        this.description = description;
        this.category = category;
        this.gender = gender;
        this.price = price;
        this.size = size;
        this.colour = colour;
        this.stockStatus = stockStatus;
        this.quantity = quantity;
        this.newArrivalID = newArrivalID;
    }

    // Constructor for fetching
    public Item(int ID, String name, String imageLocation, String description, 
                String category, String gender, double price, 
                String size, String colour, String stockStatus, 
                int quantity, int newArrivalID) {

        this.ID = ID;
        this.name = name;
        this.imageLocation = imageLocation;
        this.description = description;
        this.category = category;
        this.gender = gender;
        this.price = price;
        this.size = size;
        this.colour = colour;
        this.stockStatus = stockStatus;
        this.quantity = quantity;
        this.newArrivalID = newArrivalID;
    }


    // Getters
    public int getID () { return ID; }
    public String getName() { return name; }
    public String getImageLocation() { return imageLocation; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getGender() { return gender; }
    public double getPrice() { return price; }
    public String getSize() { return size; }
    public String getColour() { return colour; }
    public String getStockStatus() { return stockStatus; }
    public int getQuantity() { return quantity; }
    public int getNewArrivalID() { return newArrivalID; }

    // Setters
    public void setID (int ID) { this.ID = ID; }
}
