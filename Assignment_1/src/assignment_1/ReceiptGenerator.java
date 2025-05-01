/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

/**
 *
 * @author larai
 */
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

        // Create the Receipts folder if it doesn't exist
        if (!receiptDir.exists()) {
            receiptDir.mkdirs(); // create folder
        }

        // Full path: Receipts/filename.txt
        int receiptNumber = receiptDir.list().length + 1;
        String filename = "receipt_" + receiptNumber + ".txt";
        File receiptFile = new File(receiptDir, filename);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(receiptFile))) {
            bw.write("====== Purchase Receipt ======\n");

            for (Products p : cart.getCartItems()) {
                bw.write(p.printInfo() + "\n");
            }

            double total = cart.getTotalCost();
            Discounts discounts = new Discounts();
            double discountedTotal = discounts.discountTotal(cart);

            bw.write("\nTotal Amount: $" + String.format("%.2f", total));

            if (cart.getCartItems().size() >= 3) {
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

    //Method: Save guest receipt
    public static void saveGuestReceipt(Cart cart) {
        String folderName = "GuestReceipts";
        File receiptFolder = new File(folderName);

        if (!receiptFolder.exists()) {
            receiptFolder.mkdirs(); // Create folder if it doesn't exist
        }

        String fileName = "guest_receipt_.txt";
        File receiptFile = new File(receiptFolder, fileName);

        try (FileWriter writer = new FileWriter(receiptFile)) {
            writer.write("Guest Receipt\n");
            writer.write("------------------------\n");
            for (Products p : cart.getCartItems()) {
                writer.write(p.getItemName() + " | " + p.getItemType() + " | "
                        + p.getItemSize() + " | $" + p.getItemPrice() + "\n");
            }
            writer.write("Total: $" + String.format("%.2f", cart.getTotalCost()) + "\n");
            writer.write("------------------------\n");
            System.out.println("Receipt saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to save guest receipt: " + e.getMessage());
        }
    }
}
