import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale; // Used for toLowerCase(Locale.ROOT) for consistent case conversion
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

// --- Item Class: Represents a record in our simulated database ---
class Item {
    private int id;
    private String name;
    private String imagePath; // String for file path
    private String description;
    private String category;    // clothing type (e.x shirt, short, shoe etc)
    private String gender;
    private BigDecimal price;
    private String size;
    private String colour;
    private String stockStatus; // e.g., "In Stock", "Out of Stock"
    private int quantity;
    private int newArrivalId;

    public Item(int id, String name, String imagePath, String description, String category,
                String gender, BigDecimal price, String size, String colour,
                String stockStatus, int quantity, int newArrivalId) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.category = category;
        this.gender = gender;
        this.price = price;
        this.size = size;
        this.colour = colour;
        this.stockStatus = stockStatus;
        this.quantity = quantity;
        this.newArrivalId = newArrivalId;
    }

    // Getters (all values are retrieved and then converted to lowercase for comparison if needed)
    public String getName() { return name; }
    public String getImagePath() { return imagePath; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public String getSize() { return size; }
    public String getColour() { return colour; }
    public String getStockStatus() { return stockStatus; }
    public int getQuantity() { return quantity; }
}

// --- DatabaseSimulator Class: Holds and searches our in-memory data ---
class DatabaseSimulator {
    List<Item> items;

    public DatabaseSimulator() {
        items = new ArrayList<>();
        // Populate with sample data mimicking the ITEMS table
        // Ensure some items match "Shirt", "yellow Short", "large jacket" scenarios
        items.add(new Item(1, "Basic Tee", "images/clothing/basic_tee.jpg", "Comfortable cotton tee", "Shirt", "Unisex", new BigDecimal("15.99"), "M", "Blue", "In Stock", 50, 101));
        items.add(new Item(2, "V-Neck Shirt", "images/clothing/v_neck_shirt.jpg", "Stylish V-neck shirt", "Shirt", "Female", new BigDecimal("18.50"), "S", "Red", "In Stock", 30, 101));
        items.add(new Item(3, "Casual Yellow Shorts", "images/bottoms/yellow_shorts.jpg", "Lightweight summer shorts", "Short", "Male", new BigDecimal("25.00"), "L", "Yellow", "In Stock", 20, 102));
        items.add(new Item(4, "Denim Shorts", "images/bottoms/denim_shorts.jpg", "Classic denim shorts", "Short", "Unisex", new BigDecimal("35.00"), "M", "Blue", "In Stock", 0, 102)); // Example for SOLD OUT
        items.add(new Item(5, "Winter Jacket", "images/outerwear/winter_jacket.jpg", "Warm and durable winter jacket", "Jacket", "Unisex", new BigDecimal("99.99"), "L", "Black", "In Stock", 15, 103));
        items.add(new Item(6, "Lightweight Jacket", "images/outerwear/light_jacket.jpg", "Ideal for spring and autumn", "Jacket", "Female", new BigDecimal("65.00"), "S", "Green", "In Stock", 10, 103));
        items.add(new Item(7, "Polo Shirt", "images/clothing/polo_shirt.jpg", "Classic polo style", "Shirt", "Male", new BigDecimal("29.99"), "XL", "Green", "In Stock", 40, 104)); // Changed to XL
        items.add(new Item(8, "Graphic Tee", "images/clothing/graphic_tee.jpg", "Cool graphic design shirt", "Shirt", "Unisex", new BigDecimal("22.00"), "M", "Black", "Out of Stock", 0, 101)); // Another SOLD OUT example
        items.add(new Item(9, "Running Shorts", "images/bottoms/running_shorts.jpg", "Athletic running shorts", "Short", "Unisex", new BigDecimal("20.00"), "M", "Grey", "In Stock", 25, 102));
        items.add(new Item(10, "Bomber Jacket", "images/outerwear/bomber_jacket.jpg", "Trendy bomber style", "Jacket", "Male", new BigDecimal("85.00"), "XL", "Blue", "In Stock", 12, 103));
    }

    public List<Item> searchItems(String query) {
        // Convert the entire query to lowercase for case-insensitive matching
        String lowerQuery = query.toLowerCase(Locale.ROOT);
        String searchCategory = null;
        String searchSize = null;
        String searchColour = null;

        // Define keywords for parsing, all in lowercase
        Set<String> possibleSizes = new HashSet<>(Arrays.asList("xs", "s", "m", "l", "xl", "2xl", "3xl", "small", "medium", "large"));
        Set<String> possibleColors = new HashSet<>(Arrays.asList("red", "blue", "green", "yellow", "black", "white", "grey", "pink", "purple", "orange"));
        Set<String> possibleCategories = new HashSet<>(Arrays.asList("shirt", "short", "jacket", "shoe", "pant", "dress", "skirt", "hoodie", "sweater"));

        // Split query into words
        List<String> queryWords = new ArrayList<>(Arrays.asList(lowerQuery.split("\\s+")));

        // Iterate over query words to identify components
        List<String> remainingWords = new ArrayList<>();
        for (String word : queryWords) {
            if (possibleSizes.contains(word)) {
                searchSize = mapSizeKeyword(word); // Map e.g. "large" to "L"
            } else if (possibleColors.contains(word)) {
                searchColour = word;
            } else if (possibleCategories.contains(word)) {
                searchCategory = word;
            } else {
                remainingWords.add(word); // Words that didn't match size, color, or primary category
            }
        }

        // If no primary category was found, try to derive it from remaining words or assume
        if (searchCategory == null && !remainingWords.isEmpty()) {
            // A more sophisticated approach might try to combine remaining words or use a dictionary
            // For simplicity, we'll try to find a category within remaining words
            for (String word : remainingWords) {
                if (possibleCategories.contains(word)) {
                    searchCategory = word;
                    break;
                }
            }
        }


        // Filter items based on parsed criteria, ensuring all comparisons are lowercase
        final String finalSearchCategory = searchCategory;
        final String finalSearchSize = searchSize;
        final String finalSearchColour = searchColour;

        return items.stream()
                .filter(item -> finalSearchCategory == null || item.getCategory().toLowerCase(Locale.ROOT).contains(finalSearchCategory))
                .filter(item -> finalSearchSize == null || item.getSize().toLowerCase(Locale.ROOT).equals(finalSearchSize))
                .filter(item -> finalSearchColour == null || item.getColour().toLowerCase(Locale.ROOT).equals(finalSearchColour))
                .collect(Collectors.toList());
    }

    // Helper to map size keywords to standard sizes (e.g., "large" -> "L")
    private String mapSizeKeyword(String keyword) {
        switch (keyword) {
            case "small": return "S";
            case "medium": return "M";
            case "large": return "L";
            // For "xs", "s", "m", "l", "xl", "2xl", "3xl" assume they are already correct
            default: return keyword.toUpperCase(Locale.ROOT);
        }
    }
}

// --- Main Application Class ---
public class ClothingSearchApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseSimulator db = new DatabaseSimulator();

        System.out.println("Welcome to the Clothing Search App!");
        System.out.println("Enter items to search (e.g., 'Shirt', 'yellow Short', 'Large jacket'). Type 'exit' to quit.");

        while (true) {
            System.out.print("\nEnter your search query: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application. Goodbye!");
                break;
            }

            List<Item> searchResults = db.searchItems(userInput);

            if (searchResults.isEmpty()) {
                System.out.println("No items found matching '" + userInput + "'.");
            } else {
                System.out.println("\n--- Search Results for '" + userInput + "' ---");
                // Collect all available sizes and colors for the *first found category*
                // to provide a relevant list of overall options for that type of item.
                Set<String> availableSizesOverall = new HashSet<>();
                Set<String> availableColorsOverall = new HashSet<>();
                String mainCategoryForDisplay = searchResults.get(0).getCategory(); // Get category from the first result
                for (Item item : db.items) { // Iterate through ALL items in the database
                    if (item.getCategory().equalsIgnoreCase(mainCategoryForDisplay)) {
                        availableSizesOverall.add(item.getSize().toUpperCase(Locale.ROOT)); // Ensure consistent case
                        availableColorsOverall.add(item.getColour().toLowerCase(Locale.ROOT));
                    }
                }

                for (Item item : searchResults) {
                    String name = item.getName();
                    String imagePath = item.getImagePath();
                    String price = "$" + item.getPrice().toString();

                    // Check stock status
                    boolean isSoldOut = item.getStockStatus().equalsIgnoreCase("Out of Stock") || item.getQuantity() <= 0;

                    if (isSoldOut) {
                        System.out.println(name + " - SOLD OUT");
                    } else {
                        // For image path, getting the first 2 folder names (e.g., "images/clothing/basic_tee.jpg" -> "images/clothing/basic_tee.jpg")
                        // If you only want "images/clothing", you'd adjust the logic to only take parts 0 and 1.
                        String[] pathParts = imagePath.split("/");
                        String shortImagePath = imagePath; // Default to full path
                        if (pathParts.length >= 3) {
                            // This gets the root folder, the next folder, and the file name (e.g., "images/clothing/basic_tee.jpg")
                            // If you literally just want "first two folder names", it would be pathParts[0] + "/" + pathParts[1]
                            shortImagePath = pathParts[0] + "/" + pathParts[1] + "/" + pathParts[2];
                        }

                        System.out.println(
                                "(" + name + ", " +
                                        shortImagePath + ", " +
                                        price + ", " +
                                        "available sizes(" + String.join(", ", availableSizesOverall.stream().sorted().collect(Collectors.toList())) + "), " +
                                        "colors(" + String.join(", ", availableColorsOverall.stream().sorted().collect(Collectors.toList())) + "))"
                        );
                    }
                }
            }
        }
        scanner.close();
    }
}