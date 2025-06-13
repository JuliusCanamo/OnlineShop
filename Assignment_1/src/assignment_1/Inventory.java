/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simpl
 */
public class Inventory {

    private List<Products> inventory;

    private static final String FILE_NAME = "./resources/products.txt";

    public Inventory() {
        this.inventory = loadInventory(FILE_NAME);;
    }

    private List<Products> loadInventory(String filename) {
        List<Products> inventoryList = new ArrayList<>();
        File file = new File(filename);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] parts = line.split(",\\s*");
                    if (parts.length == 4) {
                        String itemName = parts[0];
                        String typeStr = parts[1];
                        double price;
                        try {
                            price = Double.parseDouble(parts[2]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price, skipping line: " + line);
                            continue;
                        }
                        String size = parts[3];

                        // Convert string to CategoryType enum
                        try {
                            Category.CategoryType type = Category.CategoryType.valueOf(typeStr.toUpperCase());
                            Products product = new Products(itemName, type, size, price);
                            inventoryList.add(product);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid category, skipping line: " + line);
                        }
                    } else {
                        System.out.println("Invalid format, skipping line: " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("File does not exist: " + filename);
        }

        return inventoryList;
    }

    public List<Products> getInventory() {
        return inventory;
    }


    public Products getProductByName(String name) {
        for (Products p : inventory) {
            if (p.getItemName().equalsIgnoreCase(name)) {
                return p;
            }
        }

        return null;
    }


    
    public String printInventory() {
        if (inventory.isEmpty()) {
            return "Inventory is empty.";
        }

        StringBuilder list = new StringBuilder("All Stock:\n");

        for (int i = 0; i < inventory.size(); i++) {
            Products p = inventory.get(i);
            list.append((i + 1)).append(") ")
                    .append(p.printInfo())
                    .append("\n\n"); 
        }

        return list.toString();
    }


}
