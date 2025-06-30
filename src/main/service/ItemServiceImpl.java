package service;

import dao.ItemDAO;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import model.Item;


public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;


    public ItemServiceImpl (ItemDAO itemDAO) {

        this.itemDAO = itemDAO;
    }

    /* ================================================ ADD ITEM FUNCTION ================================================ */
    
    @Override
    public boolean prepareNewItem (Item newItem) throws SQLException {

        validateItem(newItem);

        /* ============================== ADDING ITEM ============================== */

        // Passing the object to the DAO addItem function 
        // and returns wether the add is successful or not
        return itemDAO.addItem(newItem);
    }

    /* ================================================ EDIT ITEM FUNCTION ================================================ */

    @Override
    public boolean prepareUpdatedItem (Item updatedItem) throws SQLException {

        validateItem(updatedItem);

        /* ============================== EDITING ITEM ============================== */

        // Passing the object of the updated item to the DAO editItem function
        // and returns wether the edit is successful or not
        return itemDAO.editItem(updatedItem);
    }

    /* ================================================ GET AN ITEM FUNCTION ================================================ */

    @Override
    public Item getItemByID (int itemIDToGet) throws SQLException {

        return itemDAO.getItemByID(itemIDToGet);
    }

    /* ================================================ VIEW ITEMS FUNCTION ================================================ */

    @Override
    public List<Item> getAllItems () throws SQLException {
        return itemDAO.getAllItems();
    }

    /* ================================================ DELETE ITEM FUNCTION ================================================ */

    @Override
    public boolean prepareDeleteItem (int itemIDToDelete) throws SQLException {

        Item item = itemDAO.getItemByID(itemIDToDelete);

        if (item == null) {
            return false;
        }

        return itemDAO.deleteItem(itemIDToDelete);
    }

    



























    /* ============================================== FOR SEARCH BAR FUNCTIONS ============================================== */

    @Override
    public List<Item> getItemByAttribute(String query) throws SQLException {
        return itemDAO.getItemByAttribute(query);
    }




    @Override
    public Set<String> getAvailableSizesForCategory(String category) throws Exception {
        
        // try {
        
            return itemDAO.getAllItems().stream()
                    .filter(item -> item.getCategory() != null && item.getCategory().equalsIgnoreCase(category))
                    .map(Item::getSize)
                    .filter(Objects::nonNull)
                    .map(s -> s.toUpperCase(Locale.ROOT))
                    .collect(Collectors.toSet());
        
        // } catch (Exception e) {
        //     return Set.of();
        // }
    }

    @Override
    public Set<String> getAvailableColoursForCategory(String category) throws Exception {
        
        // try {
        
            return itemDAO.getAllItems().stream()
                    .filter(item -> item.getCategory() != null && item.getCategory().equalsIgnoreCase(category))
                    .map(Item::getColour)
                    .filter(Objects::nonNull)
                    .map(s -> s.toLowerCase(Locale.ROOT))
                    .collect(Collectors.toSet());
        
        // } catch (Exception e) {
        //     return Set.of();
        // }
    }






































    /* ============================================== HELPER FUNCTIONS ============================================== */

    private static void validateItem (Item item) {

        if (item.getName() == null || item.getName().isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }

        if (item.getPrice() < 0) {
            throw new IllegalArgumentException("Item price cannot be negative.");
        }

        if (item.getQuantity() < 0) {
            throw new IllegalArgumentException("Item quantity cannot be negative.");
        }
    }
}