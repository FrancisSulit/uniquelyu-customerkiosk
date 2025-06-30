package main;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import java.util.Scanner;
import service.ItemService;
import service.ItemServiceImpl;
import ui.customer.CustomerController;
import ui.customer.CustomerItemUI;
import ui.employee.EmployeeController;
import ui.employee.EmployeeItemUI;
import ui.employee.EmployeeLogin;

public class App {

    Scanner scanner = new Scanner(System.in);

    ItemDAO itemDAO = new ItemDAOImpl();
    ItemService itemService = new ItemServiceImpl(itemDAO);
    
    EmployeeItemUI employeeItemUI = new EmployeeItemUI(scanner, itemService);
    EmployeeController employeeMain = new EmployeeController(scanner, employeeItemUI);
    EmployeeLogin employeeLogin = new EmployeeLogin(scanner, employeeMain);

    CustomerItemUI customerItemUI = new CustomerItemUI(scanner, itemService);
    CustomerController customerMain = new CustomerController(scanner, customerItemUI, employeeLogin);

    public void run() {
        customerMain.displayCustomerMainMenu();
    }

    public static void main(String[] args) {
        new App().run();
    }
}