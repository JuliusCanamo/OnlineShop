/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

/**
 *
 * @author larai
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {

//    private List<Cart> pastOrders;

//    public OrderHistory() {
//
//        pastOrders = new ArrayList<>();
//    }

//    public void addOrder(Cart originalCart) {
//        Cart saveCart = new Cart();
//        for (Products p : originalCart.getCartItems()) {
//            saveCart.addToCart(p);
//        }
//        pastOrders.add(saveCart);
//    }

//    public void printOrderHistory() {
//        if (pastOrders.isEmpty()) {
//        System.out.println("No past orders found.");
//    } else {
//        System.out.println("----- Past Orders -----");
//        int orderNumber = 1;
//        for (Cart cart : pastOrders) {
//            System.out.println("\nOrder #" + orderNumber++);
//            cart.viewCart();  // this prints the cart content and total
//            System.out.println("-----------------------");
//        }
//    }
//    }
    //Save cart to file for user

     public void saveOrderToFile(String userName, Cart cart) {
        String userFolderPath = "OrderHistory/" + userName;
        File userFolder = new File(userFolderPath);

        if (!userFolder.exists()) {
            userFolder.mkdirs();
        }

        int orderNumber = userFolder.list().length + 1; // count files to assign new order number
        String filename = "order_" + orderNumber + ".txt";
        File orderFile = new File(userFolder, filename);

        try (FileWriter writer = new FileWriter(orderFile)) {
            for (Products p : cart.getCartItems()) {
                writer.write(p.getItemName() + " | " + p.getItemType() + " | "
                        + p.getItemSize() + " | $" + p.getItemPrice() + "\n");
            }
            System.out.println("Order saved to file: " + orderFile.getPath());
        } catch (IOException e) {
            System.out.println("Failed to save order to file: " + e.getMessage());
        }
    }
     
     
   // --- Load and print order history directly from files (avoids duplication) ---
    public void printOrderHistory(String userName) {
        File userFolder = new File("OrderHistory/" + userName);

        if (!userFolder.exists() || !userFolder.isDirectory()) {
            System.out.println("No saved order history for user: " + userName);
            return;
        }

        File[] orderFiles = userFolder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (orderFiles == null || orderFiles.length == 0) {
            System.out.println("No saved order history for user: " + userName);
            return;
        }

        System.out.println("----- Past Orders -----");
        int orderNumber = 1;
        for (File file : orderFiles) {
            Cart cart = new Cart();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        String pname = parts[0].trim();
                        Category.CategoryType category = Category.CategoryType.valueOf(parts[1].trim().toUpperCase());
                        String size = parts[2].trim();
                        double price = Double.parseDouble(parts[3].trim().replace("$", ""));
                        cart.addToCart(new Products(pname, category, size, price));
                    }
                }

                System.out.println("\nOrder #" + orderNumber++);
                cart.viewCart();
                System.out.println("-----------------------");
            } catch (IOException e) {
                System.out.println("Error reading file: " + file.getName() + " - " + e.getMessage());
            }
        }
    }

}
