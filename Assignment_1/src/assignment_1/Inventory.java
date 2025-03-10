/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

/**
 *
 * @author simpl
 */
public class Inventory extends Products{
    
    private Products[] inventory;
    private int stored;
    
    public Inventory (){
        super("Null", "Null", 0.0);
        inventory = new Products[100];
    }
    
    public void addToInvetory(Products product){
        //My code from Programming 2 Assignment 1
        //Will try to rework it with the usage of an ArrayList
        //too make the size a bit more dynamic
        // as we don't the amount of items will be
        //stored
        if (stored < this.inventory.length){
            this.inventory[this.stored] = product;
            stored++;
        }
    }
    
    public String printInventory(){
        String content = "";
        
        //this code was grabbed from assignment 1 from Programming 2
        //the for loop increases until it matches the int value from stored
        //then using the printInfo method from Products
        //content will store each product that has been stored and print it out
        for (int storage = 0; storage < this.stored; storage++){
            content += (storage + 1) + ")" + this.inventory[storage].printInfo() + "\n";
        }
        return content;
    }
    
}
