/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.List;

/**
 *
 * @author simpl
 */
public class Category{
    
    public enum CategoryType{
        PANTS, SHORTS, HOODIES, SHIRTS, SWEATSHIRTS;
    }

    public void printType(String tp, List<Products> product){
        try{
            CategoryType select = CategoryType.valueOf(tp.toUpperCase());
            System.out.println("Products inside '" + select + "'");

            boolean match = false;
            for(Products pp : product){
                if(pp != null && pp.getItemType() == select){
                    System.out.println(pp.printInfo());
                    match = true;
                }
            }

            if(!match){
                System.out.println("Nothing under your selected category");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid category! Please choose from: PANTS, SHORTS, HOODIES, SHIRTS, SWEATSHIRTS.");
        }
    }
}

