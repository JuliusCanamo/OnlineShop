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
    //Products product;
    
    public Category(String type){
        super("itemName","type",0.0);
        this.type = type;
    }
    
    public void printType(){
        //Please optimize later on
        //Rough sketch of the ideology of the method
        switch(type){
            case "Pants":
                if(this.type == "Pants" && getItemType() == this.type){
                    printInfo();
                }
                break;
            case "Shorts":
                if(this.type == "Shorts" && getItemType() == this.type){
                    printInfo();
                }
                break;
            case "Shirts":
                if(this.type == "Shirts" && getItemType() == this.type){
                    printInfo();
                }
                break;
            case "Hoodies":
                if(this.type == "Hoodies" && getItemType() == this.type){
                    printInfo();
                }
                break;
                
            case "Sweatshirts":
                if(this.type == "Sweatshirts" && getItemType() == this.type){
                    printInfo();
                }
                break;
            default:
                System.out.println("Category given is invalid");
                System.exit(0);
        }
    }
}
