// NO LONGER USED FOR ACTUAL APP
package assignment_1;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class OnlineShop {

    private UserManager userManager = new UserManager();

    public void ShopInterface() throws FileNotFoundException {
        String menu;
        Scanner scanner = new Scanner(System.in);
        Inventory storage = new Inventory();
        Cart ct = new Cart();

        System.out.println("-----------------------------");
        System.out.println(" WELCOME TO COLLECTION WORLD ");
        System.out.println("-----------------------------");
        System.out.println("Please enter 'x' at any stage to exit the program.\n");

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

        if (answer.equalsIgnoreCase("yes")) {
            Customer user = userManager.loginOrRegister(scanner);
           // user.getMoney().UserBalance(user);

            while (true) {
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
                        } else if (!choice.equalsIgnoreCase("no")) {
                            System.out.println("Invalid input, Please enter yes or no!");
                        }
                        break;
                    case "B":
                        List<Products> filtered = showCategories(scanner, storage);
                        if (!filtered.isEmpty()) {
                            System.out.println("WOULD YOU LIKE TO ADD AN ITEM TO THE CART? PLEASE ENTER ('YES'/ 'NO')");
                            choice = scanner.nextLine().trim();
                            checkExit(choice);
                            if (choice.equalsIgnoreCase("yes")) {
                                addToCart(scanner, filtered, ct); //  Always pass the filtered list here!!
                            } else if (!choice.equalsIgnoreCase("no")) {
                                System.out.println("Invalid input, Please enter yes or no!");
                            }
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
        } else {
            Customer guest = new Customer("Guest", "Guest");
            System.out.println("Proceeding as guest user...");
           // guest.getMoney().UserBalance(guest);

            while (true) {
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
                        } else if (!choice.equalsIgnoreCase("no")) {
                            System.out.println("Invalid input, Please enter yes or no!");
                        }
                        break;
                    case "B":
                        List<Products> filtered = showCategories(scanner, storage);
                        if (!filtered.isEmpty()) {
                            System.out.println("WOULD YOU LIKE TO ADD AN ITEM TO THE CART? PLEASE ENTER ('YES'/ 'NO')");
                            choice = scanner.nextLine().trim();
                            checkExit(choice);
                            if (choice.equalsIgnoreCase("yes")) {
                                addToCart(scanner, filtered, ct);
                            } else if (!choice.equalsIgnoreCase("no")) {
                                System.out.println("Invalid input, Please enter yes or no!");
                            }
                        }
                        break;
                    case "C":
                        showDiscounts();
                        break;
                    case "D":
                        showBalance(guest);
                        break;
                    case "E":
                        viewCart(scanner, ct, guest);
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

    private void addToCart(Scanner scanner, List<Products> items, Cart ct) {
        System.out.println("ENTER THE NUMBER OF THE ITEM YOU WOULD LIKE TO ADD: ");
        try {
            int itemNumber = Integer.parseInt(scanner.nextLine().trim());
            if (itemNumber >= 1 && itemNumber <= items.size()) {
                Products selected = items.get(itemNumber - 1);
                ct.addToCart(selected);
                System.out.println("ITEM ADDED TO CART!");
            } else {
                System.out.println("Invalid item number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("PLEASE ENTER A VALID NUMBER.");
        }
    }

    private void showDiscounts() {
        System.out.println("CURRENT DISCOUNTS\n");
        System.out.println("Buy 3 or more items to receive 20% off your total order.");
    }

    private List<Products> showCategories(Scanner scanner, Inventory storage) {
        System.out.println("Enter the Category name:\n"
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

    private void viewCart(Scanner scanner, Cart ct, Customer user) {
        ct.viewCart();

        if (!ct.getCartItems().isEmpty()) {
            String actionChoice;
            do {
                System.out.println("\nWOULD YOU LIKE TO (1) CHECKOUT OR (2) CONTINUE SHOPPING?");
                actionChoice = scanner.nextLine().trim();
                checkExit(actionChoice);

                if (!actionChoice.equals("1") && !actionChoice.equals("2")) {
                    System.out.println("INVALID INPUT. PLEASE ENTER '1' TO CHECKOUT OR '2' TO CONTINUE SHOPPING.");
                }
            } while (!actionChoice.equals("1") && !actionChoice.equals("2"));

            if (actionChoice.equals("1")) {
                if (user != null) {
                    Money money = user.getMoney();
                    double accountBalance = money.getBalance();

                    Discounts discount = (ct.getCartItems().size() >= 3) ? new BulkDiscount() : new NoDiscount();
                    double cartTotal = discount.applyDiscount(ct);

                    if (accountBalance >= cartTotal) {
                        money.setBalance(accountBalance - cartTotal);
                      //  user.getMoney().saveUserBalance(user);

                        System.out.println("\nWOULD YOU LIKE TO SAVE A RECEIPT? ('YES'/'NO')");
                        String receiptChoice = scanner.nextLine().trim();
                        checkExit(receiptChoice);

                        if (receiptChoice.equalsIgnoreCase("yes")) {
                            if (user.getName().equalsIgnoreCase("Guest")) {
                                ReceiptGenerator.displayGuestReceipt(ct);
                            } else {
                               // ReceiptGenerator.writeCartSummary(ct, user);
                                user.getOrderHistory().saveOrderToFile(user.getName(), ct);
                            }
                        } else if (receiptChoice.equalsIgnoreCase("no")) {
                            System.out.println("You opted to not save your receipt");
                        } else {
                            System.out.println("Invalid input, Please enter 'YES' or 'NO'");
                        }

                        ct.clearCart();
                        System.out.println("THANK YOU FOR SHOPPING AT COLLECTION WORLD!");
                    } else {
                        System.out.printf("INSUFFICIENT BALANCE! You need $%.2f more.%n", cartTotal - accountBalance);
                        System.out.println("Please insert more funds via the balance menu (Option D).");
                    }

                } else {
                    System.out.println("You are not logged in, so the order cannot be saved.");
                }
            } else {
                System.out.println("Returning to main menu to continue shopping.");
            }
        }
    }

    private void showOrderHistory(Customer user) {
        if (user != null) {
            user.viewOrderHistory();
        } else {
            System.out.println("No order history available, as you are not logged in.");
        }
    }

    public static void checkExit(String input) {
        if (input.equalsIgnoreCase("X")) {
            System.out.println("\nTHANK YOU FOR SHOPPING WITH US!");
            System.exit(0);
        }
    }

    private void showBalance(Customer user) {
        DecimalFormat format = new DecimalFormat("#0.00");
        if (user != null) {
            Money wallet = user.getMoney();
            double currentBalance = wallet.getBalance();
            System.out.println("YOUR CURRENT BALANCE IS: " +  format.format(currentBalance));

           // double updatedBalance = wallet.insertAmount();
//            System.out.println("YOUR CURRENT BALANCE IS: " + format.format(updatedBalance));

           // user.getMoney().saveUserBalance(user);
        } else {
            System.out.println("No order history available, as you are not logged in.");
        }
    }
}
