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
    
    public double reducePrice(Products p){
        
        //sales  = p.getItemPrice() * 5/ 100;
        //return sales;
        //code above was something I quickly wrote out
        //it correctly calculated the new price but didn't set it
        
        double sales = p.getItemPrice();
        double newPrice = sales - (sales * 20/100);
        DecimalFormat format = new DecimalFormat("#0.00");
        newPrice = Double.parseDouble(format.format(newPrice));
        
        p.setItemPrice(newPrice);
        return newPrice;
    }
}
