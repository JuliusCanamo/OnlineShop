/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

/**
 *
 * @author simpl
 */
public class Category extends Products {
    
    private String type;
    Products product;
    
    public Category(String type){
        super(type);
        this.type = type;
    }
    
    public void printType(){
        //Please optimize later on
        //Rough sketch of the ideology of the method
        switch(type){
            case "Pants":
                if(this.type == "Pants" && product.getItemType() == this.type){
                    product.printInfo();
                }
                break;
            case "Shorts":
                if(this.type == "Shorts" && product.getItemType() == this.type){
                    product.printInfo();
                }
                break;
            case "Shirts":
                if(this.type == "Shirts" && product.getItemType() == this.type){
                    product.printInfo();
                }
                break;
            case "Hoodies":
                if(this.type == "Hoodies" && product.getItemType() == this.type){
                    product.printInfo();
                }
                break;
                
            case "Sweatshirts":
                if(this.type == "Sweatshirts" && product.getItemType() == this.type){
                    product.printInfo();
                }
                break;
            default:
                System.out.println("Category given is invalid");
                System.exit(0);
        }
    }
}
