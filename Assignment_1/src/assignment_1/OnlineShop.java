/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.Scanner;

public class OnlineShop {

    public static void main(String args[]) {
        //Still haven't fixed it unlike the other classes
        char menu;
        String type;
        Scanner scanner = new Scanner(System.in);
        Inventory storage = new Inventory();
        
        //Test items
        Products p1 = new Products("Acne Jeans Light Blue", Category.CategoryType.PANTS, "36", 350.99);
        Products p2 = new Products("Tommy Jeans Tjm Boxy Pop Varisty Crewneck Blue", Category.CategoryType.SWEATSHIRTS, "Medium", 350.99);
        Products p3 = new Products("adidas Essentials Small Logo Feelcozy Sweatshirt Black", Category.CategoryType.SWEATSHIRTS, "Small", 350.99);
        Products p4 = new Products("H&H Women's Wide Leg Trackpants Cream", Category.CategoryType.PANTS, "18", 350.99);
        Products p5 = new Products("Acne Jeans", Category.CategoryType.PANTS, "Medium", 350.99);
        Products p6 = new Products("Acne Jeans", Category.CategoryType.PANTS, "Medium", 350.99);
        Products p7 = new Products("Acne Jeans", Category.CategoryType.PANTS, "Medium", 350.99);
        Products p8 = new Products("Acne Jeans", Category.CategoryType.PANTS, "Medium", 350.99);
        
        storage.addToInvetory(p1);
        storage.addToInvetory(p2);
        storage.addToInvetory(p3);
        storage.addToInvetory(p4);
        storage.addToInvetory(p5);
        storage.addToInvetory(p6);
        storage.addToInvetory(p7);
        storage.addToInvetory(p8);
        
        
        System.out.println("Welcome to the Online Shop");
        
        System.out.println("Please select an Option:");
        System.out.println("A: View Inventory");
        System.out.println("B: View Discounts");
        System.out.println("C: View Category");
        System.out.println("D: Exit System");
        menu = scanner.next().charAt(0);
        
        switch(menu){
            case 'A':
                System.out.println(storage.printInventory());
                break;
            case 'B':
                //haven't made the code/class for this yet lol
                break;
            case 'C':
                scanner.nextLine();
                System.out.println("Pants");
                System.out.println("Shorts");
                System.out.println("Hoodies");
                System.out.println("Shirts");
                System.out.println("Sweatshirts");
                type = scanner.nextLine().trim().toUpperCase();
                Category cat = new Category();
                cat.printType(type, storage.getInventory());
                break;
            case 'D':
                System.out.println("Bye Bye");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Option");
                System.exit(0);
        }
 
    }
}
