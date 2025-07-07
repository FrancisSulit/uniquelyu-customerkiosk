package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import model.Item;

public interface ItemDAO {

    // Basic CRUD
    boolean addItem (Item item) throws SQLException;
    boolean editItem (Item item) throws SQLException;
    Item getItemByID (int itemID) throws SQLException;
    List<Item> getAllItems () throws SQLException;
    boolean deleteItem (int itemID) throws SQLException;

    // Category Search
    public List<Item> getItemByGenderCategory (String gender, String category) throws SQLException;

    // Search Bar
    List<Item> getItemByAttribute (String query) throws SQLException;
    Set<String> getAvailableSizes (String category) throws SQLException, NullPointerException;
    Set<String> getAvailableColours (String category) throws SQLException, NullPointerException;
}