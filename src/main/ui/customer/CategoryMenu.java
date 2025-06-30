package ui.customer;

/*

import dao.DatabaseConnection;
import dao.ItemDAO;
import dao.ItemDAOImpl;
import java.sql.*;
import java.util.Scanner;
import model.Item;
import service.CustomerService;
import service.CustomerServiceImpl;


public class CategoryMenu {
    
    public void categoryMenu (Scanner scanner) {

            
        


        CustomerService service = new CustomerServiceImpl();

        String gender;
        String category;
        String size;
        String colour;

        while (true) {

            System.out.println("Enter gender (M/F/U) or press Enter to skip:");
            gender = scanner.nextLine().trim();
            if (gender.isEmpty()) gender = null;

            System.out.println("Enter category (shirt, pants...) or press Enter to skip:");
            category = scanner.nextLine().trim();
            if (category.isEmpty()) category = null;

            System.out.println("Enter size (S, M, L...) or press Enter to skip:");
            size = scanner.nextLine().trim();
            if (size.isEmpty()) size = null;

            System.out.println("Enter colour or press Enter to skip:");
            colour = scanner.nextLine().trim();
            if (colour.isEmpty()) colour = null;

            try {

            }

            catch (SQLException sqle) {
                System.out.println("Error: " + )
            }

            List<Item> results = service.filterProducts(gender, category, size, colour);

            System.out.println("Filtered products:");
            for (Product p : results) {
                System.out.println("- " + p.getName() + " [" + p.getCategory() + "] " + p.getSize() + " " + p.getColour());
            }

            System.out.println("Search again? (y/n)");
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                break;
            }
        }
    }

    private static String returnGender(String genderChoice) {
        return switch (genderChoice) {
            case "1" -> "M";
            case "2" -> "F";
            default -> "";
        };
    }

    private static String returnClothingCategory (String clothingCategoryInput, String gender) {

        return switch (clothingCategoryInput) {
            case "1" -> "Shirt";
            case "2" -> "Bottoms";
            case "3" -> "Long-Sleeve";
            case "4" -> gender.equals("Women's") ? "Dress" : "";
            default -> "";
        };
    }



















    private static void displayAllItems(String gender, String category) {

        String query = "SELECT ID, Name, Price, Size, Colour, Category, Gender, `Stock Status`, Quantity FROM items WHERE Gender = ? AND Category = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query)
        ) {

            statement.setString(1, gender);
            statement.setString(2, category);

            ResultSet rs = statement.executeQuery();

            System.out.println("============================== ITEM LIST ==============================\n");

            while (rs.next()) {
                System.out.printf(
                        "ID: %-5d | Name: %-35s | Category: %-15s | Gender: %-5s | Price: Php %-10.2f | Size: %-3s | Colour: %-15s | Stock: %-12s | Qty: %d%n",
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Category"),
                        rs.getString("Gender"),
                        rs.getDouble("Price"),
                        rs.getString("Size"),
                        rs.getString("Colour"),
                        rs.getString("Stock Status"),
                        rs.getInt("Quantity")
                );
            }
            System.out.println("\n=======================================================================\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayGenderItems(String gender) {

        String query = "SELECT ID, Name, Price, Size, Colour, Category, Gender, `Stock Status`, Quantity FROM items WHERE Gender = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query)
        ) {

            statement.setString(1, gender);

            ResultSet rs = statement.executeQuery();

            System.out.println("============================== ITEM LIST ==============================\n");

            while (rs.next()) {
                System.out.printf(
                        "ID: %-5d | Name: %-35s | Category: %-15s | Gender: %-5s | Price: Php %-10.2f | Size: %-3s | Colour: %-15s | Stock: %-12s | Qty: %d%n",
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Category"),
                        rs.getString("Gender"),
                        rs.getDouble("Price"),
                        rs.getString("Size"),
                        rs.getString("Colour"),
                        rs.getString("Stock Status"),
                        rs.getInt("Quantity")
                );
            }

            System.out.println("\n=======================================================================\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


*/














/*

        System.out.println("Select Gender Category");
        System.out.println("<1> Men's");
        System.out.println("<2> Women's");
        System.out.println();

        System.out.print("Enter choice: ");



        String genderChoice = scanner.nextLine().trim();
        String gender = returnGender(genderChoice);

        if (gender.isEmpty()) {
            System.out.println("Invalid or empty input. Please select a valid category.");
            continue;
        }

        DisplayUtilities.clearScreen();

        displayGenderItems(gender);

        System.out.println("Select Clothing Category");
        System.out.println("<1> Shirt");
        System.out.println("<2> Bottoms");
        System.out.println("<3> Long-Sleeve");

        if (gender.equals("Women's")) {
            System.out.println("<4> Dress");
        }

        System.out.print("Enter Choice: ");




        String clothingCategoryInput = scanner.nextLine().trim();

        String clotheCategory = returnClothingCategory(clothingCategoryInput, gender);

        if (clotheCategory.isEmpty()) {
            System.out.println("Invalid choice. Please select from the available options.");
            continue;
        }

        displayAllItems(gender, clotheCategory);

        */































































































































































































    /*

    StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
    List<Object> parameters = new ArrayList<>();

    // Example user inputs (from UI or prompt)
    String gender = getUserInput("gender");       // could be null
    String category = getUserInput("category");   // could be null
    String size = getUserInput("size");           // could be null
    String colour = getUserInput("colour");       // could be null

    if (gender != null && !gender.isEmpty()) {
        sql.append(" AND gender = ?");
        parameters.add(gender);
    }
    if (category != null && !category.isEmpty()) {
        sql.append(" AND category = ?");
        parameters.add(category);
    }
    if (size != null && !size.isEmpty()) {
        sql.append(" AND size = ?");
        parameters.add(size);
    }
    if (colour != null && !colour.isEmpty()) {
        sql.append(" AND colour = ?");
        parameters.add(colour);
    }

    // Now prepare and bind
    PreparedStatement stmt = conn.prepareStatement(sql.toString());

    for (int i = 0; i < parameters.size(); i++) {
        stmt.setObject(i + 1, parameters.get(i));  // JDBC is 1-indexed
    }

    ResultSet rs = stmt.executeQuery();

    */



















   /*
    public class Product {
        private int id;
        private String name;
        private String gender;
        private String category;
        private String size;
        private String colour;

        // Getters and setters here...
    }
    */

   /*
    import java.sql.*;
    import java.util.*;

    public class ProductDAO {
        private Connection conn;

        public ProductDAO(Connection conn) {
            this.conn = conn;
        }

        public List<Product> searchProducts(String gender, String category, String size, String colour) throws SQLException {
            StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
            List<Object> params = new ArrayList<>();

            if (gender != null && !gender.isEmpty()) {
                sql.append(" AND gender = ?");
                params.add(gender);
            }
            if (category != null && !category.isEmpty()) {
                sql.append(" AND category = ?");
                params.add(category);
            }
            if (size != null && !size.isEmpty()) {
                sql.append(" AND size = ?");
                params.add(size);
            }
            if (colour != null && !colour.isEmpty()) {
                sql.append(" AND colour = ?");
                params.add(colour);
            }

            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            List<Product> results = new ArrayList<>();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setGender(rs.getString("gender"));
                p.setCategory(rs.getString("category"));
                p.setSize(rs.getString("size"));
                p.setColour(rs.getString("colour"));
                results.add(p);
            }

            return results;
        }
    }
    */


   /*

    import java.sql.SQLException;
    import java.util.List;

    public class ProductService {
        private ProductDAO dao;

        public ProductService(ProductDAO dao) {
            this.dao = dao;
        }

        public List<Product> filterProducts(String gender, String category, String size, String colour) throws SQLException {
            // You could do validation or logging here
            return dao.searchProducts(gender, category, size, colour);
        }
    }
    */


   /*

    import java.sql.*;
    import java.util.List;
    import java.util.Scanner;

    public class MainUI {
        public static void main(String[] args) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "user", "pass")) {
                ProductDAO dao = new ProductDAO(conn);
                ProductService service = new ProductService(dao);

                Scanner scanner = new Scanner(System.in);

                String gender = null, category = null, size = null, colour = null;

                while (true) {
                    System.out.println("Enter gender (M/F/U) or press Enter to skip:");
                    gender = scanner.nextLine().trim();
                    if (gender.isEmpty()) gender = null;

                    System.out.println("Enter category (shirt, pants...) or press Enter to skip:");
                    category = scanner.nextLine().trim();
                    if (category.isEmpty()) category = null;

                    System.out.println("Enter size (S, M, L...) or press Enter to skip:");
                    size = scanner.nextLine().trim();
                    if (size.isEmpty()) size = null;

                    System.out.println("Enter colour or press Enter to skip:");
                    colour = scanner.nextLine().trim();
                    if (colour.isEmpty()) colour = null;

                    List<Product> results = service.filterProducts(gender, category, size, colour);

                    System.out.println("Filtered products:");
                    for (Product p : results) {
                        System.out.println("- " + p.getName() + " [" + p.getCategory() + "] " + p.getSize() + " " + p.getColour());
                    }

                    System.out.println("Search again? (y/n)");
                    if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        break;
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }












   */
   
// }