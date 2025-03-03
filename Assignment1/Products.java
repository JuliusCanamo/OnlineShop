/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author simpl
 */
public class Products {
    
    private String itemName;
    private String type;
    private double price;
    
    public Products(String type, String itemName, double price){
        this.type = type;
        this.itemName = itemName;
        this.price = price;
    }
    
    public double computeDiscount(double price){
        //test for discount
        //change for different types of items
        
        if(this.type == "Shirts"){
            double oldprice = this.price;
            double newprice = oldprice / 2.0;
            return newprice;
        }
        else if(this.type == "Pants"){
            double pantsprice = this.price;
            double huhprice = pantsprice / 2.5;
            return huhprice;
        }
        
        return this.price;
    }
}
