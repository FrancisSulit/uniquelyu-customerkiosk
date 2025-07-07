/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kioskgui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author genco
 */
public class LoginHandler {
    
    private static final String LOGIN_FILE_NAME = "/login-data/LoginDetails.txt";

    public static void saveDetails(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_FILE_NAME))) {
            writer.write(username);
            writer.newLine();
            writer.write(password);
        } catch (IOException ioe) {
            System.err.println("Failed to save login details: " + ioe.getMessage());
        }
    }

    public static String[] loadDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_FILE_NAME))) {
            String username = reader.readLine();
            String password = reader.readLine();
            return new String[]{username, password};
        } catch (IOException ioe) {
            return null;
        }
    }

}
