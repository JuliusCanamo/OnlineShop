/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author larai
 */
public class UserManager {

    private HashMap<String, Customer> users; // check this
    private Customer loggedInUser = null;

    public UserManager() {
        users = new HashMap<>();
        loadUsersFromFile();

    }

    public boolean register(String name, String password) {
        if (!users.containsKey(name)) {
            Customer newCustomer = new Customer(name, password);
            users.put(name, newCustomer);
            // Save the user to a file
            saveUserToMasterFile(newCustomer);
            System.out.println("Registration successful for user: " + name);
            return true;

        } else {
            System.out.println("Username already exists.");
            return false;
        }
    }

    private void saveUserToMasterFile(Customer user) {
        try {
            FileWriter writer = new FileWriter("users.txt", true); // true = append
            writer.write(user.getName() + "," + user.getPassword() + "\n");
            writer.close();
            System.out.println("User added to master file.");
        } catch (IOException e) {
            System.out.println("Error saving user to master file: " + e.getMessage());
        }
    }

    public void loadUsersFromFile() {
        File file = new File("users.txt");
        if (!file.exists()) {
            System.out.println("No existing user file found. Starting fresh.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    users.put(username, new Customer(username, password));
                }
            }
            System.out.println("Loaded users from file.");
        } catch (IOException e) {
            System.out.println("Error reading users from file: " + e.getMessage());
        }
    }

    public boolean login(String name, String password) {
        Customer c = users.get(name);
        if (c != null && c.checkPassword(password)) {
            loggedInUser = c;
            System.out.println("Welcome back, " + name + "!");
            return true;
        }
        //System.out.println("Invalid login credentials");
        return false;
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("Logged out: " + loggedInUser.getName());
            loggedInUser = null;
        }
    }

    public Customer getLoggedInUser() {
        return loggedInUser;
    }

     public boolean userExists(String name) {
        return users.containsKey(name);
    }
}
