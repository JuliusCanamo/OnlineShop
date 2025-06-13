/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simpl
 */
public class Category{

    public enum CategoryType {
        PANTS, //Added for jeans
        SHORTS, //Added for shorts
        HOODIES, //Added for hoodies
        SHIRTS, //Added for shirts
        SWEATSHIRTS, // Added for sweatshirts
        OUTERWEAR, // Added for jackets, blazers, coats, etc.
        ACTIVEWEAR, // Added for sports, leggings, gym shorts, etc.
        DRESSES, // Added for dresses, rompers, jumpsuits
        FOOTWEAR,     // Added for shoes, boots, sandals, etc.
        PERFUME; //for the perfume yerr gang
    }

    public void printType(String tp, List<Products> product) {
        try {
            CategoryType select = CategoryType.valueOf(tp.toUpperCase());
            System.out.println("Products inside '" + select + "'");

            boolean match = false;
            int itemNumber = 1; 

            for (Products pp : product) {
                if (pp != null && pp.getItemType() == select) {
                    //System.out.println(pp.printInfo());
                    System.out.println(itemNumber + ") " + pp.printInfo());
                itemNumber++;
                    match = true;
                }
            }

            if (!match) {
                System.out.println("Nothing under your selected category");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category! Please choose from:  \n"
                    + "PANTS, \n"
                    + "SHORTS, \n"
                    + "HOODIES, \n"
                    + "SHIRTS, \n"
                    + "SWEATSHIRTS,\n"
                    + "OUTERWEAR,\n"
                    + "ACTIVEWEAR,\n"
                    + "DRESSES,\n"
                    + "FOOTWEAR");
        }
    }
    
public List<Products> getFilteredProducts(String tp, List<Products> products) {
    List<Products> filtered = new ArrayList<>();
    try {
        CategoryType select = CategoryType.valueOf(tp.toUpperCase());
        for (Products p : products) {
            if (p != null && p.getItemType() == select) {
                filtered.add(p);
            }
        }
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid category.");
    }
    return filtered;
}
}
