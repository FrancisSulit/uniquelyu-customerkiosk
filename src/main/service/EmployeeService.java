package service;

import java.util.Scanner;

public interface EmployeeService {

    void addItemMenu (Scanner scanner);
    void editItemMenu (Scanner scanner);
    void viewItemsMenu ();
    void deleteItemMenu (Scanner scanner);
}