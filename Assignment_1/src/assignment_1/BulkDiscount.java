/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

/**
 *
 * @author larai
 */
public class BulkDiscount extends Discounts {
    
    @Override
     public double applyDiscount(Cart cart) {
        if (cart.getCartItems().size() >= 3) {
            return cart.getTotalCost() * 0.8; // 20% off
        }
        return cart.getTotalCost();
    }
}
