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
public class Cart {
    //the template of the code was found from Stack Overflow
    //https://stackoverflow.com/questions/18473130/shopping-cart-java-application-addtocart
    //it has been slightly altered to fit the classes that I have made

    private Products[] cart;
    private int capacity;
    private int count;
    private double cost;

    public Cart() {
        this.capacity = 10;
        cart = new Products[capacity];
        this.cost = 0.0;
        this.count = 0;
    }

    public List<Products> getCartItems() {
        List<Products> cartItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cartItems.add(cart[i]);
        }
        return cartItems;
    }

    public void addToCart(Products p) {
        cart[count] = p;
        cost += p.getItemPrice();
        count++;

        System.out.println(p.getItemName() + " has been added to cart");
    }

    public void addToCart(int itemNumber, List<Products> availableProducts) {
        if (itemNumber < 1 || itemNumber > availableProducts.size()) {
            System.out.println("Invalid item number. Please try again.");
            return;
        }

        Products selectedProduct = availableProducts.get(itemNumber - 1); // 1-based index
        cart[count] = selectedProduct;
        
        
        //cost += selectedProduct.getItemPrice();
        //count++;

       // System.out.println(selectedProduct.getItemName() + " has been added to cart.");

    
    }

    public void viewCart() {

        Discounts d = new Discounts();
        double finalTotal = d.discountTotal(this);

        System.out.println("Inside the Cart:");

        if (count == 0) {
            System.out.println("Cart is empty.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + " " + cart[i].printInfo());
            }
            System.out.println("Total Cost: $.2f%n" + cost);
        }
        if (count >= 3) {
            System.out.printf("Discounted Total (20%% off): $%.2f%n", finalTotal);
        }
    }

    public void clearCart() {
        for (int i = 0; i < count; i++) {
            cart[i] = null;
        }
        count = 0;
        cost = 0.0;
        System.out.println("Cart has been cleared");
    }

    public double getTotalCost() {
        return cost;
    }

}
