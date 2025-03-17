/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simpl
 */
public class Inventory{
    //I removed the super class as in
    //my inital idea with Inventory was to
    //sub class it with Products
    //as I thought that they woudl have a relation with each other
    //But with Chatgpt, it explained that
    //Inventory doesn't have any relation with Product
    //as Inventory stores a products items not a alternative version of product
    List<Products> inventory;
    
    public Inventory (){
        this.inventory = new ArrayList<>();
    }
    
    public void addToInvetory(Products product){
        //My code from Programming 2 Assignment 1
        //Will try to rework it with the usage of an ArrayList
        //too make the size a bit more dynamic
        // as we don't the amount of items will be
        //stored
//        if (stored < this.inventory.length){
//            this.inventory[this.stored] = product;
//            stored++;
//        }

        if(inventory.isEmpty()){
            System.out.println("Nothing inside our Inventory");
            System.exit(0);
        }
        else{
            inventory.add(product);
        }
    }
    
    public String printInventory(){
        //String content = "";
        
        //this code was grabbed from assignment 1 from Programming 2
        //the for loop increases until it matches the int value from stored
        //then using the printInfo method from Products
        //content will store each product that has been stored and print it out
        //for (int storage = 0; storage < this.stored; storage++){
            //content += (storage + 1) + ")" + this.inventory[storage].printInfo() + "\n";
        //}
        //return content;
        
        //Version 2
        //return "All stock: \n" + this.inventory;
        
        //Version 3 with improves from Chatgpt
        StringBuilder list = new StringBuilder();
        
        list.append("All Stock: \n");
        
        for(Products p : inventory){
            if(p != null){
                list.append(p.printInfo()).append("\n");
            }
            System.out.println("Nothing inside our inventory");
        }
        return list.toString();
    }
    
}
