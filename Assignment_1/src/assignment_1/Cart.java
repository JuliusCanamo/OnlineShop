/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.List;

/**
 *
 * @author simpl
 */
public class Cart {
    //the template of the code was found from Stack Overflow
    //https://stackoverflow.com/questions/18473130/shopping-cart-java-application-addtocart
    //it has been slightly altered to fit the classes that I have made
    
    private Products[] cart;
    private int capacity;
    private int count;
    private double cost;
    
    public Cart(){
        this.capacity = 10;
        cart = new Products[capacity];
        this.cost = 0.0;
        this.count = 0;
    }
    
    public void addToCart(Products p){
        cart[count] = p;
        cost += p.getItemPrice();
        count++;
        
        System.out.println(p.getItemName() + " has been added to cart");
    }
    
    public void viewCart(){
        System.out.println("Inside the Cart:");
        
        for(int i = 0; i < count; i++){
            System.out.println((i + 1) + " " + cart[i].printInfo());
        }
        System.out.println("Total Cost: $"+ cost);
    }
}
