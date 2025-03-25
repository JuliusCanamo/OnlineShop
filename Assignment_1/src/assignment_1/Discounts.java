/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

/**
 *
 * @author simpl
 */
public class Discounts {
    
    private double sales;
    
    public double reducePrice(Products p){
        
        //sales  = p.getItemPrice() * 5/ 100;
        //return sales;
        //code above was something I quickly wrote out
        //it correctly calculated the new price but didn't set it
        
        sales = p.getItemPrice();
        double newPrice = sales * 20/100;
        
        p.setItemPrice(newPrice);
        return newPrice;
    }
}
