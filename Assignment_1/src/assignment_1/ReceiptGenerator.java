package assignment_1;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;

public class ReceiptGenerator {

    public static String generateReceipt(Cart cart) {
        DecimalFormat df = new DecimalFormat("#0.00");
        StringBuilder receipt = new StringBuilder();

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

        return receipt.toString();
    }

    public static void displayUserReceipt(Cart cart, Customer user) {
        String receiptText = generateReceipt(cart);
        JOptionPane.showMessageDialog(null, receiptText, "Receipt for " + user.getName(), JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayGuestReceipt(Cart cart) {
        String receiptText = generateReceipt(cart);
        JOptionPane.showMessageDialog(null, receiptText, "Guest Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}
