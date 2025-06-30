package model;

import java.util.Set;

public class ItemBounds {

    // Static Set declared and initialized outside methods
    public static final Set<String> validCategories = Set.of("Shirt", "Bottoms", "Long Sleeve", "Dress");
    public static final Set<String> validGenders = Set.of("M", "F");
    public static final Set<String> validSizes = Set.of("S", "M", "L", "XL");
    public static final Set<String> validStockStatuses = Set.of("In Stock", "Low Stock", "Out Of Stock");
}