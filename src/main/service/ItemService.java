package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import model.Item;

public interface ItemService {

    boolean prepareNewItem (Item newItem) throws SQLException;
    boolean prepareUpdatedItem (Item updatedItem) throws SQLException;
    List<Item> getAllItems () throws SQLException;
    boolean prepareDeleteItem (int itemIDToDelete) throws SQLException;

    Item getItemByID (int itemIDToGet) throws SQLException;



    // ================ Search Bar ==================

    List<Item> getItemByAttribute(String query) throws SQLException;

    Set<String> getAvailableSizesForCategory(String category) throws Exception;
    Set<String> getAvailableColoursForCategory(String category) throws Exception;
}