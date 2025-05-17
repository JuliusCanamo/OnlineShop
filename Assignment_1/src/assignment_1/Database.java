/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author juliuscanamo
 */
public class Database {
    Connection userconn = null;
    Connection inventoryconn = null;
    String url = "jdbc:derby:Users;create=true";
    String inventoryURL = "jdbc:derby:Inventory;create=true";

    public void dbsetup() {
        try {
            userconn = DriverManager.getConnection(url);
            Statement statement = userconn.createStatement();

            String tableName = "UserDB";

            if (!checkTableExisting(userconn, tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(50), password VARCHAR(50))");
            }
            statement.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Database setup error: " + e.getMessage());
        }
    }
    
    private void productDB(){
        try {
            inventoryconn = DriverManager.getConnection(inventoryURL);
            Statement statement = inventoryconn.createStatement();

            String tableName = "Products";

            if (!checkTableExisting(inventoryconn,tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (itemName VARCHAR(100), type VARCHAR(100), size VARCHAR(100), price DOUBLE");
            }
            statement.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Database setup error: " + e.getMessage());
        }
    }

    private boolean checkTableExisting(Connection conn, String tableName) {
        boolean flag = false;
        try {
            System.out.println("Checking existing tables for: " + tableName);
            String[] types = {"TABLE"};
            ResultSet rs = conn.getMetaData().getTables(null, null, null, types);
            while (rs.next()) {
                String foundTable = rs.getString("TABLE_NAME");
                if (foundTable.equalsIgnoreCase(tableName)) {
                    flag = true;
                }
            }
            if (rs != null) rs.close();
        } catch (Exception e) {
            System.out.println("Table checking error: " + e.getMessage());
        }
        return flag;
    }
    
    private void insertProducts(){
        try{
            inventoryconn = DriverManager.getConnection(inventoryURL);
            Statement statement = inventoryconn.createStatement();

            String tableName = "Products";
            
            if (checkTableExisting(inventoryconn,tableName)) {
                statement.executeUpdate("INSERT INTO " + tableName + " VALUES ('SlateForm Jeans', 'PANTS', 'Medium', 49.99), ('SlateForm Jeans', 'PANTS', 'Medium', 49.99), "
                        + "('MonoEdge Denim Shorts', 'SHORTS', 'Large', 39.99), "
                        + "('LayerCore Graphic Hoodie', 'HOODIES', 'Extra Large', 59.99),"
                        + "('CoreTone T-Shirt', 'SHIRTS', 'Medium', 19.99), "
                        + "('LuxeFade Crewneck Sweatshirt', 'SWEATSHIRTS', 'Large', 45.50), "
                        + "('AxisCut Jeans', 'PANTS', 'Medium', 49.99),"
                        + "('FluxDrape Shorts, 'SHORTS', 'Large', 39.99),"
                        + "('EchoWeave Graphic Hoodie', 'HOODIES', 'Extra Large', 59.99),"
                        + "('CleanStitch T-Shirt', 'SHIRTS', 'Medium', 19.99),"
                        + "('ShiftGrid Crewneck Sweatshirt', 'SWEATSHIRTS', 'Large', 45.50),"
                        + "('Strukt Jacket', 'OUTERWEAR', 'Medium', 99.99),"
                        + "('FrameEdge Blazer', 'OUTERWEAR', 'Large', 149.99),"
                        + "('Cloudshell Coat', 'OUTERWEAR', 'Extra Large', 199.99),"
                        + "('Gravite Trench', 'OUTERWEAR', 'Medium', 179.99),"
                        + "('ZeroWind Parka', 'OUTERWEAR', 'Large', 129.99),"
                        + "('KnitShell Windbreaker', 'OUTERWEAR', 'Medium', 89.99),"
                        + "('FlexCircuit Leggings', 'ACTIVEWEAR', 'Medium', 39.99),"
                        + "('AirPulse Gym Shorts', 'ACTIVEWEAR', 'Large', 29.99),"
                        + "('DryTone Tech Tee', 'ACTIVEWEAR', 'Medium', 24.99),"
                        + "('PulseGear Jacket', 'ACTIVEWEAR', 'Large', 79.99),"
                        + "('Minimaluxe Slip Dress,', 'DRESSES', 'Medium', 69.99),"
                        + "('NoirThread Midi Dress', 'DRESSES', 'Large', 79.99),"
                        + "('EclipseFit Romper', 'DRESSES', 'Medium', 54.99),"
                        + "('OneForm Jumpsuit', 'DRESSES', 'Large', 89.99),"
                        + "('StrideForm Sneakers', 'FOOTWEAR', '8', 89.99),"
                        + "('GrainLeather Boots', 'FOOTWEAR', '9', 149.99),"
                        + "('Solace Sandals', 'FOOTWEAR', '7', 59.99),"
                        + "('ModLoaf Loafers', 'FOOTWEAR', '10', 59.99), "
                        + "('Louis Vuitton Imagination White', 'PERFUME', '100ML', 610.99),"
                        + "('Louis Vuitton Ombre Nomade Brown', 'PERFUME', '100ML', 755.00), "
                        + "('Yves Saint Laurent - MYSLF Eau de Parfum', 'PERFUME', '60ML', 193.00)");
            }
            statement.close();
        }
        catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Unable to store products: " + e.getMessage());
        }
    }

    public Customer checkName(String username, String password) {
        Customer cust = null;

        try {
            Statement statement = userconn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid, password FROM UserDB WHERE userid = '" + username + "'");

            if (rs.next()) {
                String pass = rs.getString("password");
                System.out.println("** Found user: " + username);

                if (password.equals(pass)) {
                    cust = new Customer(username, password);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Incorrect password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Register new user
                statement.executeUpdate("INSERT INTO UserDB (userid, password) VALUES('" + username + "', '" + password + "')");

                JOptionPane.showMessageDialog(null,
                    "New user registered successfully!",
                    "Registration",
                    JOptionPane.INFORMATION_MESSAGE);

                cust = new Customer(username, password);
            }

            statement.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Login or insert error: " + e.getMessage());
        }
        return cust;
    }
}
