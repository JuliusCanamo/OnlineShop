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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ReceiptGenerator {

//    public static void writeCartSummary(String filename, Cart cart) {
//        
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
//            bw.write("====== Purchase Receipt ======\n");
//
//            for (Products p : cart.getCartItems()) {
//                bw.write(p.printInfo() + "\n");  
//            }
//            
//            double total=cart.getTotalCost();
//            
//            Discounts discounts =new Discounts();
//            double discountedTotal=discounts.discountTotal(cart);
//            
//            bw.write("\nTotal Amount: $" + String.format("%.2f", total));
//            
//            //If Discount Applied
//            if (cart.getCartItems().size() >= 3) {
//                double discountAmount = total - discountedTotal;
//                bw.write("\nDiscount (20%): -$" + String.format("%.2f", discountAmount));
//                bw.write("\nDiscounted Total: $" + String.format("%.2f", discountedTotal));
//            }
//            
//            bw.write("\n==============================\n");
//
//            System.out.println("Receipt saved to: " + filename);
//        } catch (IOException e) {
//            System.out.println("Error writing receipt: " + e.getMessage());
//        }
//    }
//    
    public static void writeCartSummary(Cart cart) {
        String folderName = "Receipts";
        File receiptDir = new File(folderName);

        // Create the Receipts folder if it doesn't exist
        if (!receiptDir.exists()) {
            receiptDir.mkdirs(); // create folder
        }

        // Full path: Receipts/filename.txt
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String filename = "receipt_" + timestamp + ".txt";
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

            bw.write("\n==============================\n");

            System.out.println("Receipt saved to: " + receiptFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }

}

