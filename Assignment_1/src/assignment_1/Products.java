/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.text.DecimalFormat;

public abstract class Products {
    
    private String itemName;
    private String type;
    private double price;
    
    public Products(String type, String itemName, double price){
        this.type = type;
        this.itemName = itemName;
        this.price = price;
    }
    
    public String getItemName(){
        return this.itemName;
    }
    
    public String getItemType(){
        return this.type;
    }
    
    public double getItemPrice(){
        return this.price;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    
    public void setItemPrice(double price){
        this.price = price;
    }
    
    public void setItemType (String type){
        this.type = type;
    }
    
    public void printInfo(){
        String out = "";
        
        DecimalFormat format = new DecimalFormat("#0.00");
        
        out = "Category: " + this.type + " Product Name: " + this.itemName + "Cost: $" + format.format(this.price);
        
        System.out.println(out);
    }
}
