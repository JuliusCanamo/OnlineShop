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

    public void printType(String tp, List<Products> product){
        
        System.out.println("Products inside '" + tp + "'");
        
        boolean match = false;
        for(Products pp : product){
            if(pp != null && pp.getItemType().equalsIgnoreCase(tp)){
                System.out.println(pp.printInfo());
                match = true;
            }
        }
        
        if(!match){
            System.out.println("Nothing under your selected category");
            System.exit(0);
        }
    }
}

