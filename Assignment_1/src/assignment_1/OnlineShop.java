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
        Inventory stock = new Inventory();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Online Shop");
        
        System.out.println("Please select an Option:");
        System.out.println("A: View Inventory");
        System.out.println("B: View Discounts");
        System.out.println("C: View Category");
        System.out.println("D: Exit System");
        menu = scanner.next().charAt(0);
        
        switch(menu){
            case 'A':
                stock.printInventory();
                break;
            case 'B':
                //haven't made the code/class for this yet lol
                break;
            case 'C':
                System.out.println("Pants");
                System.out.println("Shorts");
                System.out.println("Hoodies");
                System.out.println("Shirts");
                System.out.println("Sweatshirts");
                type = scanner.nextLine();
                //Category cat = new Category(type);
                
                if(type != null){
                    //cat.printType(type);
                }
                else{
                    System.out.println("Error, invalid Category");
                    System.exit(0);
                }
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
