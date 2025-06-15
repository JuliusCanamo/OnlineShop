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
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juliuscanamo
 */
public class Database {

    Connection userConn = null;
    Connection inventoryConn = null;
    String userURL = "jdbc:derby:Users;create=true";
    String inventoryURL = "jdbc:derby:Inventory;create=true";

    public void dbsetup() {
        setupUserDB();
        setupProductDB();
        insertProducts();
    }

    private void setupUserDB() {
        try {
            userConn = DriverManager.getConnection(userURL);
            Statement statement = userConn.createStatement();

            String tableName = "UserDB";

            if (!checkTableExisting(userConn, tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(50), password VARCHAR(50), balance DOUBLE)");
            }
            statement.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("User DB setup error: " + e.getMessage());
        }
    }

    private void setupProductDB() {
        try {
            inventoryConn = DriverManager.getConnection(inventoryURL);
            Statement statement = inventoryConn.createStatement();

            String tableName = "Products";

            if (!checkTableExisting(inventoryConn, tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName
                        + " (itemName VARCHAR(100), type VARCHAR(100), size VARCHAR(100), price DOUBLE)");
            }
            statement.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Inventory DB setup error: " + e.getMessage());
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
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Table checking error: " + e.getMessage());
        }
        return flag;
    }

    private void insertProducts() {
        //this code only inserts the products if its empty
        try {
            inventoryConn = DriverManager.getConnection(inventoryURL);

            // Check if the Products table already has data
            Statement checkStmt = inventoryConn.createStatement();
            ResultSet checkRs = checkStmt.executeQuery("SELECT COUNT(*) FROM Products");
            checkRs.next();
            int rowCount = checkRs.getInt(1);
            checkRs.close();
            checkStmt.close();

            if (rowCount > 0) {
                System.out.println("Products already exist. Skipping insert.");
                return;
            }

            String sql = "INSERT INTO Products (itemName, type, size, price) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = inventoryConn.prepareStatement(sql);

            Object[][] products = {
                {"SlateForm Jeans", "PANTS", "Medium", 49.99},
                {"MonoEdge Denim Shorts", "SHORTS", "Large", 39.99},
                {"LayerCore Graphic Hoodie", "HOODIES", "Extra Large", 59.99},
                {"CoreTone T-Shirt", "SHIRTS", "Medium", 19.99},
                {"LuxeFade Crewneck Sweatshirt", "SWEATSHIRTS", "Large", 45.50},
                {"AxisCut Jeans", "PANTS", "Medium", 49.99},
                {"FluxDrape Shorts", "SHORTS", "Large", 39.99},
                {"EchoWeave Graphic Hoodie", "HOODIES", "Extra Large", 59.99},
                {"CleanStitch T-Shirt", "SHIRTS", "Medium", 19.99},
                {"ShiftGrid Crewneck Sweatshirt", "SWEATSHIRTS", "Large", 45.50},
                {"Strukt Jacket", "OUTERWEAR", "Medium", 99.99},
                {"FrameEdge Blazer", "OUTERWEAR", "Large", 149.99},
                {"Cloudshell Coat", "OUTERWEAR", "Extra Large", 199.99},
                {"Gravite Trench", "OUTERWEAR", "Medium", 179.99},
                {"ZeroWind Parka", "OUTERWEAR", "Large", 129.99},
                {"KnitShell Windbreaker", "OUTERWEAR", "Medium", 89.99},
                {"FlexCircuit Leggings", "ACTIVEWEAR", "Medium", 39.99},
                {"AirPulse Gym Shorts", "ACTIVEWEAR", "Large", 29.99},
                {"DryTone Tech Tee", "ACTIVEWEAR", "Medium", 24.99},
                {"PulseGear Jacket", "ACTIVEWEAR", "Large", 79.99},
                {"Minimaluxe Slip Dress", "DRESSES", "Medium", 69.99},
                {"NoirThread Midi Dress", "DRESSES", "Large", 79.99},
                {"EclipseFit Romper", "DRESSES", "Medium", 54.99},
                {"OneForm Jumpsuit", "DRESSES", "Large", 89.99},
                {"StrideForm Sneakers", "FOOTWEAR", "8", 89.99},
                {"GrainLeather Boots", "FOOTWEAR", "9", 149.99},
                {"Solace Sandals", "FOOTWEAR", "7", 59.99},
                {"ModLoaf Loafers", "FOOTWEAR", "10", 59.99},
                {"Louis Vuitton Imagination White", "PERFUME", "100ML", 610.99},
                {"Louis Vuitton Ombre Nomade Brown", "PERFUME", "100ML", 755.00},
                {"Yves Saint Laurent - MYSLF Eau de Parfum", "PERFUME", "60ML", 193.00}
            };

            for (Object[] p : products) {
                ps.setString(1, (String) p[0]);
                ps.setString(2, (String) p[1]);
                ps.setString(3, (String) p[2]);
                ps.setDouble(4, (double) p[3]);
                ps.executeUpdate();
            }

            ps.close();
            System.out.println("Products inserted successfully.");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Unable to store products: " + e.getMessage());
        }

//        try {
//            inventoryConn = DriverManager.getConnection(inventoryURL);
//            String sql = "INSERT INTO Products (itemName, type, size, price) VALUES (?, ?, ?, ?)";
//            PreparedStatement ps = inventoryConn.prepareStatement(sql);
//
//            Object[][] products = {
//                {"SlateForm Jeans", "PANTS", "Medium", 49.99},
//                {"MonoEdge Denim Shorts", "SHORTS", "Large", 39.99},
//                {"LayerCore Graphic Hoodie", "HOODIES", "Extra Large", 59.99},
//                {"CoreTone T-Shirt", "SHIRTS", "Medium", 19.99},
//                {"LuxeFade Crewneck Sweatshirt", "SWEATSHIRTS", "Large", 45.50},
//                {"AxisCut Jeans", "PANTS", "Medium", 49.99},
//                {"FluxDrape Shorts", "SHORTS", "Large", 39.99},
//                {"EchoWeave Graphic Hoodie", "HOODIES", "Extra Large", 59.99},
//                {"CleanStitch T-Shirt", "SHIRTS", "Medium", 19.99},
//                {"ShiftGrid Crewneck Sweatshirt", "SWEATSHIRTS", "Large", 45.50},
//                {"Strukt Jacket", "OUTERWEAR", "Medium", 99.99},
//                {"FrameEdge Blazer", "OUTERWEAR", "Large", 149.99},
//                {"Cloudshell Coat", "OUTERWEAR", "Extra Large", 199.99},
//                {"Gravite Trench", "OUTERWEAR", "Medium", 179.99},
//                {"ZeroWind Parka", "OUTERWEAR", "Large", 129.99},
//                {"KnitShell Windbreaker", "OUTERWEAR", "Medium", 89.99},
//                {"FlexCircuit Leggings", "ACTIVEWEAR", "Medium", 39.99},
//                {"AirPulse Gym Shorts", "ACTIVEWEAR", "Large", 29.99},
//                {"DryTone Tech Tee", "ACTIVEWEAR", "Medium", 24.99},
//                {"PulseGear Jacket", "ACTIVEWEAR", "Large", 79.99},
//                {"Minimaluxe Slip Dress", "DRESSES", "Medium", 69.99},
//                {"NoirThread Midi Dress", "DRESSES", "Large", 79.99},
//                {"EclipseFit Romper", "DRESSES", "Medium", 54.99},
//                {"OneForm Jumpsuit", "DRESSES", "Large", 89.99},
//                {"StrideForm Sneakers", "FOOTWEAR", "8", 89.99},
//                {"GrainLeather Boots", "FOOTWEAR", "9", 149.99},
//                {"Solace Sandals", "FOOTWEAR", "7", 59.99},
//                {"ModLoaf Loafers", "FOOTWEAR", "10", 59.99},
//                {"Louis Vuitton Imagination White", "PERFUME", "100ML", 610.99},
//                {"Louis Vuitton Ombre Nomade Brown", "PERFUME", "100ML", 755.00},
//                {"Yves Saint Laurent - MYSLF Eau de Parfum", "PERFUME", "60ML", 193.00}
//            };
//
//            for (Object[] p : products) {
//                ps.setString(1, (String) p[0]);
//                ps.setString(2, (String) p[1]);
//                ps.setString(3, (String) p[2]);
//                ps.setDouble(4, (double) p[3]);
//                ps.executeUpdate();
//            }
//
//            ps.close();
//            System.out.println("Products inserted successfully.");
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.println("Unable to store products: " + e.getMessage());
//        }
    }

    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<>();
        try {
            inventoryConn = DriverManager.getConnection(inventoryURL);
            String sql = "SELECT * FROM Products";
            PreparedStatement ps = inventoryConn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("itemName");
                String typeStr = rs.getString("type").toUpperCase();
                String size = rs.getString("size");
                double price = rs.getDouble("price");

                try {
                    Category.CategoryType type = Category.CategoryType.valueOf(typeStr);
                    products.add(new Products(name, type, size, price));
                } catch (IllegalArgumentException ex) {
                    System.out.println("Unknown product type: " + typeStr);
                }
            }

            rs.close();
            ps.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Unable to fetch all products: " + e.getMessage());
        }
        return products;
    }

    public List<Products> getFilteredProducts(String tp) {
        List<Products> allProducts = getAllProducts();
        List<Products> filtered = new ArrayList<>();
        try {
            Category.CategoryType select = Category.CategoryType.valueOf(tp.toUpperCase());
            for (Products p : allProducts) {
                if (p != null && p.getItemType() == select) {
                    filtered.add(p);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category.");
        }
        return filtered;
    }

    public Customer checkName(String username, String password) {
        Customer cust = null;

        try {
            Statement statement = userConn.createStatement();
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

                statement.executeUpdate("INSERT INTO UserDB (userid, password, balance) VALUES('" + username + "', '" + password + "', 0.0)");

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

    public void loadUserBalance(Customer user) {
        if (user == null) {
            System.out.println("User not provided. Cannot save balance.");
            return;
        }
        try {
            PreparedStatement ps = userConn.prepareStatement("SELECT balance FROM UserDB WHERE userid = ?");
            ps.setString(1, user.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.getMoney().setBalance(rs.getDouble("balance"));
            }
            rs.close();
            ps.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Error loading user balance: " + e.getMessage());
        }
    }

    public void saveUserBalance(Customer user) {
        if (user == null) {
            System.out.println("User not provided. Cannot save balance.");
            return;
        }
        try {
            PreparedStatement ps = userConn.prepareStatement("UPDATE UserDB SET balance = ? WHERE userid = ?");
            ps.setDouble(1, user.getMoney().getBalance());
            ps.setString(2, user.getName());
            ps.executeUpdate();
            System.out.println("Balance: " + ps);
            ps.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Error loading user balance: " + e.getMessage());
        }

    }
    // USER REGISTRATION

    public Customer registerUser(String username, String password) {
        try {
            PreparedStatement checkStmt = userConn.prepareStatement("SELECT * FROM UserDB WHERE userid = ?");
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "USERNAME ALREADY EXISTS.");
                return null;
            }

            PreparedStatement insertStmt = userConn.prepareStatement("INSERT INTO UserDB (userid, password, balance) VALUES (?, ?, ?)");
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            insertStmt.setDouble(3, 0.0);  // Initial balance is zero
            insertStmt.executeUpdate();

            System.out.println("USER REGISTERED: " + username);
            return new Customer(username, password);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("REGISTER USER ERROR: " + e.getMessage());
            return null;
        }
    }
    //DELETE USER

    public void deleteUser(String username) {
        try {
            PreparedStatement ps = userConn.prepareStatement("DELETE FROM UserDB WHERE userid = ?");
            ps.setString(1, username);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
