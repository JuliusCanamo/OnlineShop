/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author simpl
 */
public class Money {

    private double balance;
    private double amount;

    public Money() {
        this.balance = 0.0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean depositMoney(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public double insertAmount() {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat format = new DecimalFormat("#0.00");
        String query;
        
        do {
            System.out.println("WOULD YOU LIKE TO INSERT MONEY INTO YOUR ACCOUNT? (YES/NO)");
            query = scanner.nextLine().trim();
            OnlineShop.checkExit(query);

            if (!query.equalsIgnoreCase("yes") && !query.equalsIgnoreCase("no")) {
                System.out.println("INVALID INPUT. PLEASE TYPE 'YES' OR 'NO'.");
            }
        } while (!query.equalsIgnoreCase("yes") && !query.equalsIgnoreCase("no"));

        while (query.equalsIgnoreCase("yes")) {
            System.out.println("HOW MUCH WOULD YOU LIKE TO INSERT?");
            String input = scanner.nextLine().trim();
            OnlineShop.checkExit(input);

            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(" INVALID AMOUNT. PLEASE ENTER A NUMBER.");
                continue;  // Ask again
            }

            if (depositMoney(amount)) {
                System.out.println("Current Balance $" + format.format(this.balance));
            } else {
                System.out.println(" AMOUNT MUST BE POSITIVE.");
            }

            do {
                System.out.println("WOULD YOU LIKE TO CONTINUE TO INSERT MONEY INTO YOUR ACCOUNT? (YES/NO)");
                query = scanner.nextLine().trim();
                OnlineShop.checkExit(query);

                if (!query.equalsIgnoreCase("yes") && !query.equalsIgnoreCase("no")) {
                    System.out.println("INVALID INPUT. PLEASE TYPE 'YES' OR 'NO'.");
                }
            } while (!query.equalsIgnoreCase("yes") && !query.equalsIgnoreCase("no"));

        }
        return this.balance;
    }

    //SAVES USER CURRENT BALANCE FROM SESSION   
    public void saveUserBalance(Customer user) {
        if (user == null) {
            System.out.println("User not provided. Cannot save balance.");
            return;
        }

        String userName = user.getName().trim().toUpperCase();
        File balanceDir = new File("Balance/" + userName.toUpperCase());

        if (!balanceDir.exists()) {
            boolean created = balanceDir.mkdirs();
            if (!created) {
                System.out.println("Failed to create balance directory for user.");
                return;
            }
        }

        File balanceFile = new File(balanceDir, "balance_" + userName.toUpperCase() + ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(balanceFile))) {
            bw.write(String.format("%.2f", this.balance));
            System.out.println("Balance has been saved to: " + balanceFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving balance: " + e.getMessage());
        }
    }

//        /*Code below implements the structure from getting the saved balance from the user
//        and sets the saved balance from the previous session as its current
//        most of the structure of the code was found from - 
//        https://www.w3schools.com/java/java_files_read.asp*/
    
    //LOADS  USERS PREVIOUS BALANCE
    public void UserBalance(Customer user) {
        if (user == null) {
            System.out.println("User not provided. Cannot load balance.");
            return;
        }

        String userName = user.getName().trim().toUpperCase();
        File balanceDir = new File("Balance/" + userName.toUpperCase());
        File balanceFile = new File(balanceDir, "balance_" + userName.toUpperCase() + ".txt");

        if (!balanceFile.exists()) {
            System.out.println("No previous balance found. Starting at $0.00");
            return;
        }

        try (Scanner reader = new Scanner(balanceFile)) {
            if (reader.hasNextLine()) {
                String savedBalance = reader.nextLine().trim();
                double loaded = Double.parseDouble(savedBalance);
                user.getMoney().setBalance(loaded);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading balance file: " + e.getMessage());
        }
    }
}
