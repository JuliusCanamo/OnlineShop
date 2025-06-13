package assignment_1;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;

public class ReceiptGenerator {

    public static void displayUserReceipt(Cart cart, Customer user) {
        DecimalFormat df = new DecimalFormat("#0.00");

        StringBuilder receipt = new StringBuilder();
        receipt.append("====== Purchase Receipt ======\n\n");

        List<Products> items = cart.getCartItems();
        for (Products p : items) {
            receipt.append(p.printInfo()).append("\n\n");
        }

        double total = cart.getTotalCost();
        double discountedTotal = cart.getDiscountedTotal();
        double discountAmount = total - discountedTotal;

        receipt.append("Total: $").append(df.format(total)).append("\n");
        receipt.append("Discount: $").append(df.format(discountAmount)).append("\n");
        receipt.append("Final Total: $").append(df.format(discountedTotal)).append("\n");

        JOptionPane.showMessageDialog(null, receipt.toString(), "Receipt for " + user.getName(), JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayGuestReceipt(Cart cart) {
        DecimalFormat df = new DecimalFormat("#0.00");

        StringBuilder receipt = new StringBuilder();
        receipt.append("====== Guest Receipt ======\n\n");

        List<Products> items = cart.getCartItems();
        for (Products p : items) {
            receipt.append(p.printInfo()).append("\n\n");
        }

        double total = cart.getTotalCost();
        double discountedTotal = cart.getDiscountedTotal();
        double discountAmount = total - discountedTotal;

        receipt.append("Total: $").append(df.format(total)).append("\n");
        receipt.append("Discount: $").append(df.format(discountAmount)).append("\n");
        receipt.append("Final Total: $").append(df.format(discountedTotal)).append("\n");

        JOptionPane.showMessageDialog(null, receipt.toString(), "Guest Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}
