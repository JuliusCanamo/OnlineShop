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

    private List<Cart> pastOrders;

    public OrderHistory() {

        pastOrders = new ArrayList<>();
    }

    public void addOrder(Cart originalCart) {
        Cart saveCart = new Cart();
        for (Products p : originalCart.getCartItems()) {
            saveCart.addToCart(p);
        }
        pastOrders.add(saveCart);
    }

    public void printOrderHistory() {
        if (pastOrders.isEmpty()) {
            System.out.println("No past orders found.");
        } else {

            for (Cart cart : pastOrders) {
                cart.viewCart();
            }
        }
    }
    //Save cart to file for user

    public void saveOrderToFile(String name, Cart cart) {
        try (FileWriter writer = new FileWriter("orderhistory_" + name + ".txt", true)) {
            writer.write("Order:\n");
            for (Products p : cart.getCartItems()) {
                writer.write(p.getItemName() + " | " + p.getItemType() + " | "
                        + p.getItemSize() + " | $" + p.getItemPrice() + "\n");
            }
            writer.write("----\n"); // separator
        } catch (IOException e) {
            System.out.println("Failed to save order to file: " + e.getMessage());
        }
    }
     // Load past orders from file for user
    public void loadOrdersFromFile(String name) {
        File file = new File("orderhistory_" + name + ".txt");
        if (!file.exists()) {
            System.out.println("No saved order history for user: " + name);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Cart tempCart = new Cart();
            while ((line = reader.readLine()) != null) {
                if (line.equals("----")) {
                    pastOrders.add(tempCart);
                    tempCart = new Cart(); // reset
                } else if (!line.equals("Order:")) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        String pname = parts[0].trim();
                        Category.CategoryType category = Category.CategoryType.valueOf(parts[1].trim().toUpperCase());
                        String size = parts[2].trim();
                        double price = Double.parseDouble(parts[3].trim().replace("$", ""));
                        tempCart.addToCart(new Products(pname, category, size, price));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading order history: " + e.getMessage());
        }
    }
}
