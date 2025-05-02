package assignment_1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Products[] cart;
    private int capacity;
    private int count;
    private double cost;

    public Cart() {
        this.capacity = 10;
        this.cart = new Products[capacity];
        this.count = 0;
        this.cost = 0.0;
    }

    public List<Products> getCartItems() {
        List<Products> cartItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cartItems.add(cart[i]);
        }
        return cartItems;
    }

    public void addToCart(Products p) {
        if (count >= capacity) {
            expandCart();
        }
        cart[count] = p;
        cost += p.getItemPrice();
        count++;
        System.out.println(p.getItemName() + " has been added to cart.");
    }

    public void addToCart(int itemNumber, List<Products> availableProducts) {
        if (itemNumber < 1 || itemNumber > availableProducts.size()) {
            System.out.println("Invalid item number. Please try again.");
            return;
        }

        Products selectedProduct = availableProducts.get(itemNumber - 1);
        addToCart(selectedProduct); // Reuse existing method
    }

    public void viewCart() {
        DecimalFormat format = new DecimalFormat("#0.00");
        Discounts discount = getApplicableDiscount();

        double discountedTotal = discount.applyDiscount(this);
        System.out.println("Inside the Cart:");

        if (count == 0) {
            System.out.println("Cart is empty.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + cart[i].printInfo());
            }
            System.out.println("Total Cost: $" + format.format(cost));
            if (discount instanceof BulkDiscount) {
                System.out.println("Discounted Total (20% off): $" + format.format(discountedTotal));
            }
        }
    }

    public void clearCart() {
        for (int i = 0; i < count; i++) {
            cart[i] = null;
        }
        count = 0;
        cost = 0.0;
        System.out.println("Cart has been cleared.");
    }

    public double getTotalCost() {
        return cost;
    }

    public int getItemCount() {
        return count;
    }

    private void expandCart() {
        capacity *= 2;
        Products[] newCart = new Products[capacity];
        System.arraycopy(cart, 0, newCart, 0, count);
        cart = newCart;
    }

    // Choose appropriate discount based on cart size
    private Discounts getApplicableDiscount() {
        if (count >= 3) {
            return new BulkDiscount();
        }
        return new NoDiscount();
    }
}