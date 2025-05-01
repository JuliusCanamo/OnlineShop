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

    //I removed the super class as in
    //my inital idea with Inventory was to
    //sub class it with Products
    //as I thought that they would have a relation with each other
    //But with Chatgpt, it explained that
    //Inventory doesn't have any relation with Product
    //as Inventory stores a products items not a alternative version of product
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

    public void addToInventory(Products product) {
        //My code from Programming 2 Assignment 1
        //Will try to rework it with the usage of an ArrayList
        //too make the size a bit more dynamic
        // as we don't the amount of items will be
        //stored
//        if (stored < this.inventory.length){
//            this.inventory[this.stored] = product;
//            stored++;
//        }

        if (product != null) {
            inventory.add(product);
        } else {
            System.out.println("Unable to be stored");
        }
    }

    public Products getProductByName(String name) {
        for (Products p : inventory) {
            if (p.getItemName().equalsIgnoreCase(name)) {
                return p;
            }
        }

        return null;
    }

//    public String printInventory(){
//        //String content = "";
//        
//        //this code was grabbed from assignment 1 from Programming 2
//        //the for loop increases until it matches the int value from stored
//        //then using the printInfo method from Products
//        //content will store each product that has been stored and print it out
//        //for (int storage = 0; storage < this.stored; storage++){
//            //content += (storage + 1) + ")" + this.inventory[storage].printInfo() + "\n";
//        //}
//        //return content;
//        
//        //Version 2
//        //return "All stock: \n" + this.inventory;
//        
//        //Version 3 with improves from Chatgpt
//        StringBuilder list = new StringBuilder("All Stock: \n");
//
//          if(inventory.isEmpty()){
//              return "Nothing inside the inventory";  // If inventory is empty, print this message
//          }
//
//          for(Products p : inventory){
//              if(p != null){  // This check should be redundant since ArrayList can only store non-null objects, but it's a safety measure
//                  list.append(p.printInfo()).append("\n");
//              }
//          }
//          return list.toString();
//    }
    
    public String printInventory() {
        if (inventory.isEmpty()) {
            return "Inventory is empty.";
        }

        StringBuilder list = new StringBuilder("All Stock:\n");

        for (int i = 0; i < inventory.size(); i++) {
            Products p = inventory.get(i);
            list.append((i + 1)).append(") ")
                    .append(p.printInfo())
                    .append("\n\n"); // Optional extra line break for spacing
        }

        return list.toString();
    }


}
