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
public class Category {

    public enum CategoryType {
        PANTS, //Added for jeans
        SHORTS, //Added for shorts
        HOODIES, //Added for hoodies
        SHIRTS, //Added for shirts
        SWEATSHIRTS, // Added for sweatshirts
        OUTERWEAR, // Added for jackets, blazers, coats, etc.
        ACTIVEWEAR, // Added for sports, leggings, gym shorts, etc.
        DRESSES, // Added for dresses, rompers, jumpsuits
        FOOTWEAR;     // Added for shoes, boots, sandals, etc.
    }

    public void printType(String tp, List<Products> product) {
        try {
            CategoryType select = CategoryType.valueOf(tp.toUpperCase());
            System.out.println("Products inside '" + select + "'");

            boolean match = false;
            for (Products pp : product) {
                if (pp != null && pp.getItemType() == select) {
                    System.out.println(pp.printInfo());
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
}
