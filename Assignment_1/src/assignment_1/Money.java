/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author simpl
 */
public class Money {
    private double balance;
    private double amount;
    
    public Money(){
        this.balance = 0.0;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public double getBalance(){
        return this.balance;
    }
    
    public boolean depositMoney(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }
    
    public double insertAmount(){
        Scanner scanner = new Scanner(System.in);
        DecimalFormat format = new DecimalFormat("#0.00");
        
        System.out.println("Would you like to insert money into your account? (yes/no)");
        String query = scanner.nextLine().trim();
        
        while(query.equalsIgnoreCase("yes")){
            System.out.println("How much would you like to insert?");
            amount = scanner.nextDouble();
            scanner.nextLine();
            
            if(depositMoney(amount)){
                System.out.println("Current Balance $" + format.format(this.balance));
            }
            else{
                System.out.println("Current Balance $" + format.format(this.balance));
            }
            System.out.println("Would you like to continue to insert money into your account? (yes/no)");
            query = scanner.nextLine().trim();
        }
        return this.balance;
    }
}
