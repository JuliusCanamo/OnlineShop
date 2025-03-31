/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.Scanner;

public class OnlineShop {
    
    public void ShopInterface(){
        
        //So I changed it since main got away too long and messy
        //All main handles will be in App :)
        char menu;
        String type;
        Scanner scanner = new Scanner(System.in);
        Inventory storage = new Inventory();
        Cart ct = new Cart();
        boolean shop = true;
        
        //Test items
        Products p1 = new Products("Acne Jeans Light Blue", Category.CategoryType.PANTS, "36", 350.99);
        Products p2 = new Products("Tommy Jeans Boxy Pop Varisty Crewneck Blue", Category.CategoryType.SWEATSHIRTS, "Medium", 50.99);
        Products p3 = new Products("adidas Essentials Small Logo Feelcozy Sweatshirt Black", Category.CategoryType.SWEATSHIRTS, "Small", 44.99);
        Products p4 = new Products("Women's Wide Leg Trackpants Cream", Category.CategoryType.PANTS, "18", 350.99);
        Products p5 = new Products("Class White Tee", Category.CategoryType.SHIRTS , "Medium", 33.99);
        Products p6 = new Products("Heavy Weight Black Tee", Category.CategoryType.SHIRTS, "Medium", 49.99);
        Products p7 = new Products("Low Rise Relaxed Straight Leg Jean", Category.CategoryType.PANTS, "24", 89.99);
        Products p8 = new Products("Classic Mens Sweater, Light Blue", Category.CategoryType.PANTS, "Small", 75.99);
        
        storage.addToInvetory(p1);
        storage.addToInvetory(p2);
        storage.addToInvetory(p3);
        storage.addToInvetory(p4);
        storage.addToInvetory(p5);
        storage.addToInvetory(p6);
        storage.addToInvetory(p7);
        storage.addToInvetory(p8);
        
        
        System.out.println("Welcome to the Online Shop");
        
        while(shop){        
            System.out.println("\nPlease select an Option:");
            System.out.println("A: View Inventory");
            System.out.println("B: View Discounts");
            System.out.println("C: View Category");
            System.out.println("D: View Cart");
            System.out.println("E: Exit System");
            menu = scanner.next().toUpperCase().charAt(0);
            
            switch(menu){
            case 'A':
                System.out.println(storage.printInventory());
                
                scanner.nextLine();
                
                System.out.println("Would you like to add an item to the cart? (yes/no)");
                String choice = scanner.nextLine().trim();
                
                if(choice.equalsIgnoreCase("yes")){
                    System.out.println("Which product would you like to add? ");
                    String name = scanner.nextLine().trim();
                    
                    Products selected = storage.getProductByName(name);
                    
                    if(selected != null){
                        ct.addToCart(selected);
                    }
                    else{
                        System.out.println("Item is not in inventory");
                
                    }
                }
                
                break;
            case 'B':
                Discounts ds = new Discounts();
                System.out.println("Reduce prices: " + "" + p8.getItemName() + " $" + ds.reducePrice(p8));
                System.out.println("Reduce prices: " + "" + p2.getItemName() + " $" + ds.reducePrice(p2));
                System.out.println("Reduce prices:  "+ "" + p6.getItemName() + " $"  + ds.reducePrice(p6));
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
                ct.viewCart();
                break;
            case 'E':
                System.out.println("Bye Bye");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Option");
                break;
            }
        } 
    }
}
