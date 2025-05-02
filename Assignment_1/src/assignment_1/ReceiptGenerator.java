package assignment_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptGenerator {

    public static void writeCartSummary(Cart cart, Customer user) {
        if (user == null) {
            System.out.println("User not provided. Cannot save personalized receipt.");
            return;
        }

        String folderPath = "Receipts/" + user.getName();
        File receiptDir = new File(folderPath);

        if (!receiptDir.exists()) {
            receiptDir.mkdirs(); // create folder
        }

        int receiptNumber = receiptDir.list().length + 1;
        String filename = "receipt_" + receiptNumber + ".txt";
        File receiptFile = new File(receiptDir, filename);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(receiptFile))) {
            bw.write("====== Purchase Receipt ======\n");

            for (Products p : cart.getCartItems()) {
                bw.write(p.printInfo() + "\n");
            }

            double total = cart.getTotalCost();

            // Discount logic
            Discounts discount = cart.getItemCount() >= 3 ? new BulkDiscount() : new NoDiscount();
            double discountedTotal = discount.applyDiscount(cart);

            bw.write("\nTotal Amount: $" + String.format("%.2f", total));

            if (discount instanceof BulkDiscount) {
                double discountAmount = total - discountedTotal;
                bw.write("\nDiscount (20%): -$" + String.format("%.2f", discountAmount));
                bw.write("\nDiscounted Total: $" + String.format("%.2f", discountedTotal));
            }

            double beforeBalance = user.getMoney().getBalance() + discountedTotal;
            double afterBalance = user.getMoney().getBalance();

            bw.write("\n\nAccount Balance Before: $" + String.format("%.2f", beforeBalance));
            bw.write("\nAccount Balance After:  $" + String.format("%.2f", afterBalance));

            bw.write("\n==============================\n");

            System.out.println("Receipt saved to: " + receiptFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }

    public static void saveGuestReceipt(Cart cart) {
        String folderName = "GuestReceipts";
        File receiptFolder = new File(folderName);

        if (!receiptFolder.exists()) {
            receiptFolder.mkdirs();
        }

        String fileName = "guest_receipt.txt";
        File receiptFile = new File(receiptFolder, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
            writer.write("Guest Receipt\n");
            writer.write("------------------------\n");

            for (Products p : cart.getCartItems()) {
                writer.write(p.getItemName() + " | " + p.getItemType() + " | "
                        + p.getItemSize() + " | $" + String.format("%.2f", p.getItemPrice()) + "\n");
            }

            double total = cart.getTotalCost();
            Discounts discount = cart.getItemCount() >= 3 ? new BulkDiscount() : new NoDiscount();
            double discountedTotal = discount.applyDiscount(cart);

            writer.write("\nTotal Amount: $" + String.format("%.2f", total));

            if (discount instanceof BulkDiscount) {
                double discountAmount = total - discountedTotal;
                writer.write("\nDiscount (20%): -$" + String.format("%.2f", discountAmount));
                writer.write("\nDiscounted Total: $" + String.format("%.2f", discountedTotal));
            }

            writer.write("\n------------------------\n");

            System.out.println("Guest receipt saved to: " + receiptFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Failed to save guest receipt: " + e.getMessage());
        }
    }
}
