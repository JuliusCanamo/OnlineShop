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
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReceiptGenerator {

    public static void writeCartSummary(String filename, Cart cart) {
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("====== Purchase Receipt ======\n");

            for (Products p : cart.getCartItems()) {
                bw.write(p.printInfo() + "\n");  
            }
            
            double total=cart.getTotalCost();
            
            Discounts discounts =new Discounts();
            double discountedTotal=discounts.discountTotal(cart);
            
            bw.write("\nTotal Amount: $" + String.format("%.2f", total));
            
            //If Discount Applied
            if (cart.getCartItems().size() >= 3) {
                double discountAmount = total - discountedTotal;
                bw.write("\nDiscount (20%): -$" + String.format("%.2f", discountAmount));
                bw.write("\nDiscounted Total: $" + String.format("%.2f", discountedTotal));
            }
            
            bw.write("\n==============================\n");

            System.out.println("Receipt saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
    
}

