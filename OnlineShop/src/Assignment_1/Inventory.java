/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.ArrayList;

/**
 *
 * @author simpl
 */
public class Inventory extends Products{
    
     private ArrayList<Products> inventory;
    
    public Inventory() {
        super("Null", "Null", 0.0);
        inventory = new ArrayList<>();
    }
    
      public void addToInventory(Products product) {
        inventory.add(product);
    }
    
    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory List:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ")");
            inventory.get(i).printInfo();
            System.out.println(); // Adds spacing between products
        }
    
}   
}
