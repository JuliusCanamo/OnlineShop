/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.util.List;
import java.util.Scanner;

public class OnlineShop {

    private UserManager userManager = new UserManager();

    public void ShopInterface() {

        //So I changed it since main got away too long and messy
        //All main handles will be in App :)
        String menu;
        Scanner scanner = new Scanner(System.in);
        Inventory storage = new Inventory();
        Cart ct = new Cart();
        boolean shop = true;

        System.out.println("-----------------------------");
        System.out.println(" WELCOME TO COLLECTION WORLD ");
        System.out.println("-----------------------------");

        // --- Ask if user wants to login or not ---
        System.out.println("Would you like to log in? (yes/no)");
        String answer = scanner.nextLine().toUpperCase();
        checkExit(answer);

        // If user wants to log in
        if (answer.equalsIgnoreCase("yes")) {
            Customer user = userManager.loginOrRegister(scanner);

            // After login/register, continue to the shopping interface
            while (shop) {
                showMenu();
                menu = scanner.nextLine().toUpperCase();
                checkExit(menu);

                switch (menu) {
                    case "A":
                        System.out.println(storage.printInventory());

                        System.out.println("Would you like to add an item to the cart? (yes/no)");
                        String choice = scanner.nextLine().trim();
                        checkExit(choice);

                        if (choice.equalsIgnoreCase("yes")) {
                            addToCart(scanner, storage, ct);
                        } else if(choice != ("yes") || choice !=("no"))
                        {
                            System.out.println("Invalid input, Please enter yes or no!");
                        }
                        break;
                    case "B":
                        showDiscounts();
                        break;
                    case "C":
                        showCategories(scanner, storage);
                        break;
                    case "D":
                        viewCart(scanner, ct, user);
                        break;
                    case "E":
                        showOrderHistory(user);
                        break;
                    case "X":
                        System.out.println("\nTHANK YOU FOR SHOPPING WITH US!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option");
                        break;
                }
            }
        } // If user does not want to log in
        else {
            while (shop) {
                showMenu();
                menu = scanner.nextLine().toUpperCase();
                checkExit(menu);

                switch (menu) {
                    case "A":
                        System.out.println(storage.printInventory());

                        System.out.println("Would you like to add an item to the cart? (yes/no)");
                        String choice = scanner.nextLine().trim();
                        checkExit(choice);

                        if (choice.equalsIgnoreCase("yes")) {
                            addToCart(scanner, storage, ct);
                        }
                        break;
                    case "B":
                        showDiscounts();
                        break;
                    case "C":
                        showCategories(scanner, storage);
                        break;
                    case "D":
                        viewCart(scanner, ct, null); // No user logged in
                        break;
                    case "E":
                        System.out.println("No order history available, as you are not logged in.");
                        break;
                    case "X":
                        System.out.println("\nTHANK YOU FOR SHOPPING WITH US!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid Option");
                        break;
                }
            }
        }
    }

// Method: To display set shopping menu
    private void showMenu() {
        System.out.println("\nPlease enter the letter to select your option:");
        System.out.println("A: View Inventory");
        System.out.println("B: View Discounts");
        System.out.println("C: View Category");
        System.out.println("D: View Cart");
        System.out.println("E: View Order History");
        System.out.println("X: Exit System");
    }

// Method: To add to cart based on number selected
    private void addToCart(Scanner scanner, Inventory storage, Cart ct) {
        System.out.println("Enter the number of the item you would like to add: ");

        try {
            int itemNumber = Integer.parseInt(scanner.nextLine().trim());

            List<Products> items = storage.getInventory();
            if (itemNumber >= 1 && itemNumber <= items.size()) {
                Products selected = items.get(itemNumber - 1); // Adjust for 0-based index
                ct.addToCart(selected);
                System.out.println("Item added to cart.");
            } else {
                System.out.println("Invalid item number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

// Method: To show discounts
    private void showDiscounts() {
        System.out.println("CURRENT DISCOUNTS\n");
        System.out.println("Buy 3 or more items to receive 20% off your total order.");
    }

// Method: To show category options
    private void showCategories(Scanner scanner, Inventory storage) {
        System.out.println("Choose a category:\n"
                + "-PANTS\n"
                + "-SHORTS\n"
                + "-HOODIES\n"
                + "-SHIRTS\n"
                + "-SWEATSHIRTS\n"
                + "-OUTERWEAR\n"
                + "-ACTIVEWEAR\n"
                + "-DRESSES\n"
                + "-FOOTWEAR");
        String type = scanner.nextLine().trim().toUpperCase();
        checkExit(type);
        Category cat = new Category();
        cat.printType(type, storage.getInventory());
    }

// Method: To view cart,Checkout / continue shopping, Get receipt, Clear cart
    private void viewCart(Scanner scanner, Cart ct, Customer user) {
        ct.viewCart();

        if (!ct.getCartItems().isEmpty()) {
            System.out.println("Would you like to (1) Checkout or (2) Continue Shopping?");
            String actionChoice = scanner.nextLine().trim();
            checkExit(actionChoice);

            if (actionChoice.equals("1")) { // Checkout
                if (user != null) {
                    user.saveOrder(ct); // Save to memory
                    System.out.println("Would you like to save a receipt? (yes/no)");

                    String receiptChoice = scanner.nextLine().trim();
                    checkExit(receiptChoice);
                    if (receiptChoice.equalsIgnoreCase("yes")) {

                        ReceiptGenerator.writeCartSummary(ct);
                        user.getOrderHistory().saveOrderToFile(user.getName(), ct); // Save to file
                    }
                } else {
                    System.out.println("You are not logged in, so the order cannot be saved.");
                }

                ct.clearCart(); // Clear cart after checkout
                System.out.println("Thank you for your purchase!");
            } else {
                System.out.println("Returning to main menu to continue shopping.");
            }
        }
    }

// Method: to show order history
    private void showOrderHistory(Customer user) {
        if (user != null) {
            user.getOrderHistory().loadOrdersFromFile(user.getName());
            user.getOrderHistory().printOrderHistory();
        } else {
            System.out.println("No order history available, as you are not logged in.");
        }
    }

    //Method: To exit at any stage
    private void checkExit(String input) {
        if (input.equalsIgnoreCase("X")) {
            System.out.println("\nTHANK YOU FOR SHOPPING WITH US!");
            System.exit(0);
        }
    }

}
