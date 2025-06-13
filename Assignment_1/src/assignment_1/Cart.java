package assignment_1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Products> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addToCart(Products p) {
        cartItems.add(p);
        System.out.println(p.getItemName() + " has been added to cart.");
    }

    public void addToCart(int itemNumber, List<Products> availableProducts) {
        if (itemNumber < 1 || itemNumber > availableProducts.size()) {
            System.out.println("Invalid item number. Please try again.");
            return;
        }

        Products selectedProduct = availableProducts.get(itemNumber - 1);
        addToCart(selectedProduct);
    }

    public List<Products> getCartItems() {
        return cartItems;
    }

    public double getTotalCost() {
        double total = 0.0;
        for (Products p : cartItems) {
            total += p.getItemPrice();
        }
        return total;
    }

    public double getDiscountedTotal() {
        double total = getTotalCost();
        Discounts discount = getApplicableDiscount();
        return discount.applyDiscount(this);
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public void clearCart() {
        cartItems.clear();
        System.out.println("Cart has been cleared.");
    }

    private Discounts getApplicableDiscount() {
        if (cartItems.size() >= 3) {
            return new BulkDiscount();
        }
        return new NoDiscount();
    }

    public void viewCart() {
        DecimalFormat format = new DecimalFormat("#0.00");

        System.out.println("Inside the Cart:");
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            int i = 1;
            for (Products p : cartItems) {
                System.out.println(i++ + ". " + p.printInfo());
            }
            System.out.println("Total Cost: $" + format.format(getTotalCost()));

            if (getApplicableDiscount() instanceof BulkDiscount) {
                System.out.println("Discounted Total (20% off): $" + format.format(getDiscountedTotal()));
            }
        }
    }
}
