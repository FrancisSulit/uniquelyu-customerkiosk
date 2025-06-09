package dao;

import java.sql.SQLException;
import java.util.List;
import model.Item;

public interface ItemDAO {

    boolean addItem (Item item) throws SQLException;
    boolean editItem (Item item) throws SQLException;
    List<Item> getAllItems () throws SQLException;
    boolean deleteItem (int itemID) throws SQLException;

    Item getItem (int itemID) throws SQLException, Exception;
}