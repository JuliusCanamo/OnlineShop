/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class OnlineShop {

    private UserManager userManager = new UserManager();

    public void ShopInterface() throws FileNotFoundException {

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

        System.out.println("Please enter 'x' at any stage to exit the program.\n");
        // --- Ask if user wants to login or not ---
        //This will continue asking until correct input or X to exit
        String answer = "";
        while (true) {
            System.out.println("WOULD YOU LIKE TO LOG IN? PLEASE ENTER ('YES'/'NO')");
            answer = scanner.nextLine().trim().toLowerCase();
            checkExit(answer);

            if (answer.equals("yes") || answer.equals("no")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        // If user wants to log in
        if (answer.equalsIgnoreCase("yes")) {
            Customer user = userManager.loginOrRegister(scanner);
            user.getMoney().UserBalance(user);

            // After login/register, continue to the shopping interface
            while (shop) {
                showMenu();
                menu = scanner.nextLine().toUpperCase();
                checkExit(menu);

                switch (menu) {
                    case "A":
                        System.out.println(storage.printInventory());

                        System.out.println("WOULD YOU LIKE TO ADD AN ITEM TO THE CART? PLEASE ENTER ('YES'/ 'NO')");
                        String choice = scanner.nextLine().trim();
                        checkExit(choice);

                        if (choice.equalsIgnoreCase("yes")) {
                            addToCart(scanner, storage.getInventory(), ct);
                        } else if (choice != ("yes") || choice != ("no")) {
                            System.out.println("Invalid input, Please enter yes or no!");
                        }
                        break;
                    case "B":
                        //showCategories(scanner, storage);
                        List<Products> filtered = showCategories(scanner, storage);
                        System.out.println("WOULD YOU LIKE TO ADD AN ITEM TO THE CART? PLEASE ENTER ('YES'/ 'NO')");
                        choice = scanner.nextLine().trim();
                        checkExit(choice);

                        if (choice.equalsIgnoreCase("yes")) {
                            addToCart(scanner, filtered, ct);
                        }
                        break;
                    case "C":
                        showDiscounts();
                        break;
                    case "D":
                        showBalance(user);
                        break;
                    case "E":
                        viewCart(scanner, ct, user);
                        break;
                    case "F":
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
        } else { // If user does not want to log in
            Customer guest = new Customer("Guest", "Guest"); //Guest login, password
            System.out.println("Proceeding as guest user...");
            guest.getMoney().UserBalance(guest);

            while (shop) {
                showMenu();
                menu = scanner.nextLine().toUpperCase();
                checkExit(menu);

                switch (menu) {
                    case "A":
                        System.out.println(storage.printInventory());

                        System.out.println("WOULD YOU LIKE TO ADD AN ITEM TO THE CART? PLEASE ENTER ('YES'/ 'NO')");
                        String choice = scanner.nextLine().trim();
                        checkExit(choice);

                        if (choice.equalsIgnoreCase("yes")) {
                            addToCart(scanner, storage.getInventory(), ct);
                        } else if (choice != ("yes") || choice != ("no")) {
                            System.out.println("Invalid input, Please enter yes or no!");
                        }
                        break;
                    case "B":
                        List<Products> filtered = showCategories(scanner, storage);
                        System.out.println("WOULD YOU LIKE TO ADD AN ITEM TO THE CART? PLEASE ENTER ('YES'/ 'NO')");
                        choice = scanner.nextLine().trim();
                        checkExit(choice);

                        if (choice.equalsIgnoreCase("yes")) {
                            addToCart(scanner, filtered, ct);
                        }
                        break;
                    case "C":
                        showDiscounts();
                        break;
                    case "D":
                        showBalance(guest);
                        break;
                    case "E":
                        viewCart(scanner, ct, guest); // No user logged in
                        break;
                    case "F":
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
        System.out.println("\nPLEASE ENTER THE LETTER TO SELECT YOUR OPTION:");
        System.out.println("------------------------------------------------");
        System.out.println("A: View & Add by: Inventory");
        System.out.println("B: View & Add By: Category");
        System.out.println("C: View Discounts");
        System.out.println("D: View Balance");
        System.out.println("E: View Cart / Checkout");
        System.out.println("F: View Order History");
        System.out.println("-------------------------------------------------");
        System.out.println("X: Exit System");
    }

// Method: To add to cart based on number selected
    private void addToCart(Scanner scanner, List<Products> items, Cart ct) {
        System.out.println("ENTER THE NUMBER OF THE ITEM YOU WOULD LIKE TO ADD: ");

        try {
            int itemNumber = Integer.parseInt(scanner.nextLine().trim());

           // List<Products> items = storage.getInventory();
            if (itemNumber >= 1 && itemNumber <= items.size()) {
                Products selected = items.get(itemNumber - 1); // Adjust for 0-based index
                ct.addToCart(selected);
                System.out.println("ITEM ADDED TO CART!");
            } else {
                System.out.println("Invalid item number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("PLEASE ENTER A VALID NUMBER.");
        }
        
    }

// Method: To show discounts
    private void showDiscounts() {
        System.out.println("CURRENT DISCOUNTS\n");
        System.out.println("Buy 3 or more items to receive 20% off your total order.");
    }

// Method: To show category options
    private List<Products> showCategories(Scanner scanner, Inventory storage) {
        System.out.println("Choose A Category:\n"
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
        List<Products> filtered = cat.getFilteredProducts(type, storage.getInventory());

        if (filtered.isEmpty()) {
            System.out.println("Nothing under your selected category.");
        } else {
            int itemNumber = 1;
            for (Products p : filtered) {
                System.out.println(itemNumber + ") " + p.printInfo());
                itemNumber++;
            }
        }

        return filtered;
    }

// Method: To view cart,Checkout / continue shopping, Get receipt, Clear cart
    private void viewCart(Scanner scanner, Cart ct, Customer user) {
        ct.viewCart();
        Discounts discount = new Discounts();

        if (!ct.getCartItems().isEmpty()) {
            System.out.println("\nWOULD YOU LIKE TO (1) CHECKOUT OR (2) CONTINUE SHOPPING?");
            String actionChoice = scanner.nextLine().trim();
            checkExit(actionChoice);

            if (actionChoice.equals("1")) { // Checkout
                if (user != null) {
                    Money money = user.getMoney();
                    double accountBalance = money.getBalance();
                    double cartTotal = discount.discountTotal(ct);

                    if (accountBalance >= cartTotal) {
                        // Deduct balance and save order
                        money.setBalance(accountBalance - cartTotal);
                        user.getMoney().saveUserBalance(user);

                        System.out.println("\nWOULD YOU LIKE TO SAVE A RECEIPT? ('YES'/'NO')");
                        String receiptChoice = scanner.nextLine().trim();
                        checkExit(receiptChoice);

                        if (receiptChoice.equalsIgnoreCase("yes")) {

                            if (user.getName().equalsIgnoreCase("Guest")) {
                                ReceiptGenerator.saveGuestReceipt(ct);
                            } else {
                                ReceiptGenerator.writeCartSummary(ct, user);
                                user.getOrderHistory().saveOrderToFile(user.getName(), ct);
                            }
                        }

                        ct.clearCart(); // Clear cart after checkout
                        System.out.println("THANK YOU FOR SHOPPING AT COLLECTION WORLD!");
                    } else {
                        System.out.printf("INSUFFICIENT BALANCE! You need $%.2f more.%n", cartTotal - accountBalance);
                        System.out.println("Please insert more funds via the balance menu (Option F).");
                    }

                } else {
                    System.out.println("You are not logged in, so the order cannot be saved.");
                }
            } else {
                System.out.println("Returning to main menu to continue shopping.");
            }
        }
    }

// Method: to show order history
    private void showOrderHistory(Customer user) {
        if (user != null) {
            user.viewOrderHistory();
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

    private void showBalance(Customer user) {
        if (user != null) {

            Money wallet = user.getMoney();
            double currentBalance = wallet.getBalance();
            System.out.printf("YOUR CURRENT BALANCE IS: $%.2f%n", currentBalance);

            double updatedBalance = wallet.insertAmount();
            System.out.printf("YOUR CURRENT BALANCE IS: $%.2f%n", updatedBalance);

            user.getMoney().saveUserBalance(user);

        } else {
            System.out.println("No order history available, as you are not logged in.");
        }
    }
}
