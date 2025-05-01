/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.text.DecimalFormat;

/**
 *
 * @author simpl
 */
public class Discounts {
    
    private static final double DISCOUNT_RATE = 0.20;
    
    public double reducePrice(Products p){
        
        //sales  = p.getItemPrice() * 5/ 100;
        //return sales;
        //code above was something I quickly wrote out
        //it correctly calculated the new price but didn't set it
        
        double originalPrice = p.getItemPrice();
        double newPrice = Math.round(originalPrice * (1 - DISCOUNT_RATE) * 100.0) / 100.0;
        
        p.setItemPrice(newPrice);
        return newPrice;
    }
    
    public double discountTotal(Cart cart){
        DecimalFormat format = new DecimalFormat("#0.00");
        int itemCount= cart.getCartItems().size();
        double totalCost= cart.getTotalCost();
        
        if(itemCount>=3){
            double discount=totalCost * DISCOUNT_RATE;
            double discountedTotal= totalCost - discount;
            System.out.println("Discount applied! Original Total: " + format.format(totalCost));
            System.out.println("Discounted Total: " + format.format(discountedTotal));
            return discountedTotal;
        }else{
            return totalCost;
        }
        
    }
}
