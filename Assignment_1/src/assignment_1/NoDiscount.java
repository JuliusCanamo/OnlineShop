/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

/**
 *
 * @author larai
 */
public class NoDiscount extends Discounts {

    @Override
    public double applyDiscount(Cart cart) {
        return cart.getTotalCost();
    }
}
