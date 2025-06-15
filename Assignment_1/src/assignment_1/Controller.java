/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author juliuscanamo
 */
public class Controller {
    private final Database db;
    private final Cart cart;
    private Customer currentUser;
    private final View view;
    private final OrderHistory orderHistory;

    public Controller(Database db, Cart cart, Customer currentUser, View view, OrderHistory orderHistory) {
        this.db = db;
        this.cart = cart;
        this.currentUser = currentUser;
        this.view = view;
        this.orderHistory = orderHistory;
    }

    public void setCurrentUser(Customer user) {
        this.currentUser = user;
    }
    
    // Add product to cart based on selection
    public void addToCart(int itemIndex, List<Products> displayedProducts) {
        if (displayedProducts == null || displayedProducts.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nothing displayed to add.");
            return;
        }
        if (itemIndex < 1 || itemIndex > displayedProducts.size()) {
            JOptionPane.showMessageDialog(view, "Invalid item number.");
        } else {
            Products selected = displayedProducts.get(itemIndex - 1);
            cart.addToCart(selected);
            JOptionPane.showMessageDialog(view, selected.getItemName() + " added to cart.");
        }
    }

    // Full checkout process 
    public void checkout() {
        if (cart.getItemCount() == 0) {
            JOptionPane.showMessageDialog(view, "Your cart is empty.");
            return;
        }

        Discounts discount = (cart.getItemCount() >= 3) ? new BulkDiscount() : new NoDiscount();
        double discountedTotal = discount.applyDiscount(cart);

        db.loadUserBalance(currentUser);
        double currentBalance = currentUser.getMoney().getBalance();

        if (currentBalance < discountedTotal) {
            JOptionPane.showMessageDialog(view,
                    "Insufficient funds.\nTotal: $" + String.format("%.2f", discountedTotal) +
                            "\nCurrent balance: $" + String.format("%.2f", currentBalance));

            while (currentBalance < discountedTotal) {
                String input = JOptionPane.showInputDialog(view,
                        "Please insert additional funds to continue:", "Add Funds", JOptionPane.PLAIN_MESSAGE);
                if (input == null) {
                    JOptionPane.showMessageDialog(view, "Checkout cancelled.");
                    return;
                }
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0) {
                        currentUser.getMoney().depositMoney(amount);
                        db.saveUserBalance(currentUser);
                        currentBalance = currentUser.getMoney().getBalance();
                    } else {
                        JOptionPane.showMessageDialog(view, "Enter a valid positive amount.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Invalid input.");
                }
            }
        }

        if (currentUser.getName().equalsIgnoreCase("Guest")) {
            ReceiptGenerator.displayGuestReceipt(cart);
        } else {
            ReceiptGenerator.displayUserReceipt(cart, currentUser);
            orderHistory.saveOrderToFile(currentUser.getName(), cart);
        }

        currentUser.getMoney().setBalance(currentBalance - discountedTotal);
        db.saveUserBalance(currentUser);
        view.updateBalanceDisplay();
        cart.clearCart();
        view.showCart();
    }

    public void addBalance() {
        db.loadUserBalance(currentUser);
        double balance = currentUser.getMoney().getBalance();

        JPanel panel = new JPanel();
        JTextField amountField = new JTextField(10);
        panel.add(new JLabel("Current Balance: $" + String.format("%.2f", balance)));
        panel.add(new JLabel("Add Funds:"));
        panel.add(amountField);

        int result = JOptionPane.showConfirmDialog(view, panel, "Add Balance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                if (amount > 0) {
                    currentUser.getMoney().depositMoney(amount);
                    db.saveUserBalance(currentUser);
                    JOptionPane.showMessageDialog(view, "Balance updated.");
                    view.updateBalanceDisplay();
                } else {
                    JOptionPane.showMessageDialog(view, "Enter a valid positive amount.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Invalid input.");
            }
        }
    }
}