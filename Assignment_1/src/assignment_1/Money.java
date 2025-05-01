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

        System.out.println("Would you like to insert money into your account? (yes/no)");
        String query = scanner.nextLine().trim();

        while (query.equalsIgnoreCase("yes")) {
            System.out.println("How much would you like to insert?");
            amount = scanner.nextDouble();
            scanner.nextLine();

            if (depositMoney(amount)) {
                System.out.println("Current Balance $" + format.format(this.balance));
            } else {
                System.out.println("Current Balance $" + format.format(this.balance));
            }
            System.out.println("Would you like to continue to insert money into your account? (yes/no)");
            query = scanner.nextLine().trim();
        }
        return this.balance;
    }

//    public void saveUserBalance(Customer user) {
//
//        if (user == null) {
//            System.out.println("User not provided. Cannot save balance.");
//            return;
//        }
//
//        String folderPath = "Balance/" + user.getName().toUpperCase();
//        File balanceDir = new File(folderPath);
//
//        // Create the Balance folder if it doesn't exist
//         if (!balanceDir.exists()) {
//            boolean created = balanceDir.mkdirs();
//            if (!created) {
//                System.out.println("Failed to create balance directory for user.");
//                return;
//            }
//        }
//
//        // Full path: Balance/filename.txt
//        String filename = "balance_" + user.getName() + ".txt";
//        File balanceFile = new File(balanceDir, filename);
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(balanceFile))) {
//            bw.write(String.format("%.2f", this.balance));
//            System.out.println("Balance has been saved to: " + balanceFile.getAbsolutePath());
//
//        } catch (IOException e) {
//            System.out.println("Error saving balance: " + e.getMessage());
//        }
//    }
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

//    public void UserBalance(Customer user) {
//
//        if (user == null) {
//            System.out.println("User not provided. Cannot save balance.");
//            return;
//        }
//
//        /*Code below implements the structure from getting the saved balance from the user
//        and sets the saved balance from the previous session as its current
//        most of the structure of the code was found from - 
//        https://www.w3schools.com/java/java_files_read.asp*/
//        String folderPath = "Balance/" + user.getName().toUpperCase();
//        File balanceDir = new File(folderPath);
//
//        if (!balanceDir.exists()) {
//            boolean created = balanceDir.mkdirs();
//            if (!created) {
//                System.out.println("Failed to create balance directory for user.");
//                return;
//            }
//        } else {
//
//            String filename = "balance_" + user.getName() + ".txt";
//            File balanceFile = new File(balanceDir, filename);
//
//            try (Scanner myReader = new Scanner(balanceFile)) {
//                myReader.hasNextLine();
//
//                String savedBalance = myReader.nextLine().trim();
//                double loadTheBalance = Double.parseDouble(savedBalance);
//
//                user.getMoney().setBalance(loadTheBalance);
//
//            } catch (IOException e) {
//                //System.out.println("Error saving balance: " + e.getMessage());
//            }
//        }
//    }
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
