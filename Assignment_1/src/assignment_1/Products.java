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
    private Category.CategoryType type;
    private double price;
    private String size;
    
    public Products(String itemName, Category.CategoryType  type, String size, double price){
        this.itemName = itemName;
        this.type = type;
        this.size = size;
        this.price = price;
    }
    
    public String getItemName(){
        return this.itemName;
    }
    
    public Category.CategoryType getItemType(){
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
    
    public void setItemType (Category.CategoryType type){
        this.type = type;
    }
    
    public String getItemSize() {
    return this.size;
}
    public String printInfo(){ 
        //Code that Laraib wrote
        //As she tested it on her laptop but
        //was unable to push due to an error code
        //so she sent me the update code
        //where it actual prints out the info for the product items
        DecimalFormat format = new DecimalFormat("#0.00");
        
        return " PRODUCT NAME: " + this.itemName + "\n"
              + " CATEGORY: " + this.type + "\n"
              + " SIZE: " + this.size +  "\n"
              + " COST: $" + format.format(this.price);
    }
}

