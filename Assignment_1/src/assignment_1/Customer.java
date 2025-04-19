/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

/**
 *
 * @author larai
 */
public class Customer {
    private String name;
    private String password;
    private OrderHistory orderHistory;  // The customer's order history
    private Money money;

    // Constructor
    public Customer(String name, String password) {
        this.name = name;
        this.password=password;
        this.orderHistory = new OrderHistory();
        this.money = new Money();
    }

    //Checking password
    public boolean checkPassword(String input){
        return password != null && password.equals(input);
    }
    
    // Getters and Setters for Customer Details (Encapsulation)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }
    
    // Display the order history of the customer
    public void saveOrder(Cart cart) {
        orderHistory.addOrder(cart);
        orderHistory.saveOrderToFile(name, cart);
    }
    
    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword() {
        return password;
    }
    
    public Money getMoney(){
        return this.money;
    }

}
