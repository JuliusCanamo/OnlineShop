/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.text.DecimalFormat;

public class Products {
    //Had this as an abstract class before
    //as in my idea of this would be to extend category and inventory with products
    //but through fixing and enhancing the code I wrote, it explained to me that
    //those other classes is not a type of product but simply a container for it
    
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
    
    public String printInfo(){
        //Code that Laraib wrote
        //As she tested it on her laptop but
        //was unable to push due to an error code
        //so she sent me the update code
        //where it actual prints out the info for the product items
        DecimalFormat format = new DecimalFormat("#0.00");
        
        return "Category: " + this.type + " Product Name: " + this.itemName + "Cost: $" + format.format(this.price);
    }
}

