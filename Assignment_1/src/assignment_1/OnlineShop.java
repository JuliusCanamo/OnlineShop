/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.Scanner;

public class OnlineShop {

    private UserManager userManager = new UserManager();

    public void ShopInterface() {

        //So I changed it since main got away too long and messy
        //All main handles will be in App :)
        char menu;
        String type;
        Scanner scanner = new Scanner(System.in);
        Inventory storage = new Inventory();
        Cart ct = new Cart();
        boolean shop = true;

//        //Test items
//        Products p1 = new Products("Acne Jeans Light Blue", Category.CategoryType.PANTS, "36", 350.99);
//        Products p2 = new Products("Tommy Jeans Boxy Pop Varisty Crewneck Blue", Category.CategoryType.SWEATSHIRTS, "Medium", 50.99);
//        Products p3 = new Products("adidas Essentials Small Logo Feelcozy Sweatshirt Black", Category.CategoryType.SWEATSHIRTS, "Small", 44.99);
//        Products p4 = new Products("Women's Wide Leg Trackpants Cream", Category.CategoryType.PANTS, "18", 350.99);
//        Products p5 = new Products("Class White Tee", Category.CategoryType.SHIRTS , "Medium", 33.99);
//        Products p6 = new Products("Heavy Weight Black Tee", Category.CategoryType.SHIRTS, "Medium", 49.99);
//        Products p7 = new Products("Low Rise Relaxed Straight Leg Jean", Category.CategoryType.PANTS, "24", 89.99);
//        Products p8 = new Products("Classic Mens Sweater, Light Blue", Category.CategoryType.PANTS, "Small", 75.99);
//        
//        storage.addToInvetory(p1);
//        storage.addToInvetory(p2);
//        storage.addToInvetory(p3);
//        storage.addToInvetory(p4);
//        storage.addToInvetory(p5);
//        storage.addToInvetory(p6);
//        storage.addToInvetory(p7);
//        storage.addToInvetory(p8);
//        
        System.out.println("WELCOME TO COLLECTION WORLD");

        // --- LOGIN SYSTEM ---
        Customer user = null;

        while (user == null) {
            System.out.println("Please Log In:");
            System.out.print("Username: ");
            String name = scanner.nextLine().trim();

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            if (userManager.login(name, password)) {
                user = userManager.getLoggedInUser();
                break;
            } else {
                System.out.print("User Not Found. Would you like to register instead? (yes/no): ");
                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("yes")) {
                    userManager.register(name, password);
                    System.out.println("Registration successful. Logging in ...");
                    userManager.login(name, password); //logs user in automatically
                    user = userManager.getLoggedInUser();
                } else {
                    System.out.println("Login failed. Please try again");
                }
            }
        }

        System.out.println("Login successful! Welcome, " + user.getName());

        while (shop) {
            System.out.println("\nPlease enter the letter to select your option:");
            System.out.println("A: View Inventory");
            System.out.println("B: View Discounts");
            System.out.println("C: View Category");
            System.out.println("D: View Cart");
            System.out.println("E: Exit System");
            System.out.println("F: View Order History");
            menu = scanner.nextLine().toUpperCase().charAt(0);
            //scanner.nextLine();
            switch (menu) {
                case 'A':
                    System.out.println(storage.printInventory());

                    System.out.println("Would you like to add an item to the cart? (yes/no)");
                    String choice = scanner.nextLine().trim();

                    if (choice.equalsIgnoreCase("yes")) {
                        System.out.println("Which product would you like to add? ");
                        String pName = scanner.nextLine().trim();

                        Products selected = storage.getProductByName(pName);

                        if (selected != null) {
                            ct.addToCart(selected);
                            System.out.println("Item added to cart.");
                        } else {
                            System.out.println("Item is not in inventory");

                        }
                    }

                    break;
                case 'B':
                    Discounts ds = new Discounts();
                    System.out.println("Discounted Prices");
                    for (Products product : storage.getInventory()) {
                        double original = product.getItemPrice();
                        double discounted = ds.reducePrice(product);
                        System.out.printf("%s - Original: $%.2f, Discounted: $%.2f%n",
                                product.getItemName(), original, discounted);
                    }

                    break;
                case 'C':
                    //scanner.nextLine();
                    System.out.println("Choose a category(Pants, Shorts,Hoodies, Shirts,Sweatshirts):");
                    type = scanner.nextLine().trim().toUpperCase();
                    Category cat = new Category();
                    cat.printType(type, storage.getInventory());
//                System.out.println("Pants");
//                System.out.println("Shorts");
//                System.out.println("Hoodies");
//                System.out.println("Shirts");
//                System.out.println("Sweatshirts");
//                type = scanner.nextLine().trim().toUpperCase();
//                Category cat = new Category();
//                cat.printType(type, storage.getInventory());
                    break;
                case 'D':

//                    ct.viewCart();
//
//                    Customer loggedUser = userManager.getLoggedInUser(); //change variable name
//                    if (loggedUser != null) {
//                        loggedUser.saveOrder(ct);//save a copy of the cart
//                       loggedUser.getOrderHistory().saveOrderToFile(user.getName(), ct);
//                        ct.clearCart(); //clear cart after purchase
//                       
//                    }
//
//                    System.out.println("Would you like to save a receipt? (yes/no)");
//                    String receiptChoice = scanner.nextLine().trim();
//
//                    if (receiptChoice.equalsIgnoreCase("yes")) {
//                        ReceiptGenerator.writeCartSummary("receipt.txt", ct);
//                    }
//                     cart.viewCart();
                    if (!ct.getCartItems().isEmpty()) {
                        user.saveOrder(ct); // Save to memory
                        System.out.println("Would you like to save a receipt? (yes/no)");
                        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                            ReceiptGenerator.writeCartSummary("receipt.txt", ct);
                            user.getOrderHistory().saveOrderToFile(user.getName(), ct); // <-- add this
                        }
                        ct.clearCart(); // Clear cart
                    } else {
                        System.out.println("Cart is empty.");
                    }

                    break;
                case 'E':
                    System.out.println("Thank You for Shopping with us!");
                    System.exit(0);
                    break;
                case 'F':
                    user.getOrderHistory().loadOrdersFromFile(user.getName());
                    user.getOrderHistory().printOrderHistory();

                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}
