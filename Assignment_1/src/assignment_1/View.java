/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author juliuscanamo
 */
public class View extends JFrame implements ShopListener {
    private Database db = new Database();

    private JPanel userPanel = new JPanel();
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    JTextField unInput = new JTextField(10);
    JTextField pwInput = new JTextField(10);
    private JLabel wrongName = new JLabel("Wrong username or password!");
    
    private JPanel shopPanel = new JPanel();

    JButton quitButton = new JButton("Quit");
    JButton loginButton = new JButton("Log in");
    JButton printAll = new JButton("View Inventory");
    JButton printCategory = new JButton("View Inventory by Category");
    JButton cartButton = new JButton("View Cart");
    JButton balanceButton = new JButton("View Balance");
    JButton insertBalance = new JButton("Insert");

    private JPanel balancePanel = new JPanel();
    private JLabel currentBalanceLabel = new JLabel("Current Balance: $0.00");
    private JLabel balanceLabel = new JLabel("Insert Balance: ");
    JTextField balanceInput = new JTextField(10); 
    
    private JPanel displayPanel = new JPanel();

    private Customer currentUser;
    private Inventory inventory = new Inventory();
    private Cart cart = new Cart();
    
    private JButton checkoutButton = new JButton("Checkout");

    public JLabel message = new JLabel("Welcome", JLabel.CENTER);
    public JTextField calcSolution = new JTextField(10);
    
    private JPanel cartInputPanel = new JPanel();
    private JLabel cartLabel = new JLabel("Enter item number to add to cart: ");
    private JTextField cartInputField = new JTextField(5);
    private JButton addToCartButton = new JButton("Add to Cart");

    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        this.setLocationRelativeTo(null);
        this.db.dbsetup();

        userPanel.add(uName);
        userPanel.add(unInput);
        userPanel.add(pWord);
        userPanel.add(pwInput);
        userPanel.add(loginButton);
        userPanel.add(this.message, BorderLayout.SOUTH);

        this.add(userPanel);
        this.setVisible(true);

        loginButton.addActionListener(new ButtonListener());
        insertBalance.addActionListener(new ButtonListener());
        balanceButton.addActionListener(new ButtonListener());
        printAll.addActionListener(new ButtonListener());
        printCategory.addActionListener(new ButtonListener());
        addToCartButton.addActionListener(new ButtonListener());
        checkoutButton.addActionListener(new ButtonListener());
        cartButton.addActionListener(new ButtonListener());
    }

    public void startShopping() {
        shopPanel.removeAll();
        shopPanel.add(printAll);
        shopPanel.add(printCategory);
        shopPanel.add(cartButton);
        shopPanel.add(balanceButton);

        // Setup an empty balancePanel by default
        balancePanel.removeAll();
        balancePanel.add(balanceLabel);
        balancePanel.add(balanceInput);
        balancePanel.add(insertBalance);
        shopPanel.add(balancePanel);
        
        displayPanel.removeAll();
        shopPanel.add(displayPanel); 
        
        this.getContentPane().removeAll();
        this.add(shopPanel);
        this.revalidate();
        this.repaint();
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                String username = unInput.getText().trim();
                String password = pwInput.getText().trim();
                Customer customer = db.checkName(username, password);
                db.loadUserBalance(customer);
                onLogin(customer);
            }

            else if (e.getSource() == printAll) {
                List<Products> list = db.getAllProducts();

                displayPanel.removeAll(); // Clear previous display
                displayPanel.setLayout(new BorderLayout());

                if (list.isEmpty()) {
                    displayPanel.add(new JLabel("No products found."), BorderLayout.CENTER);
                } else {
                    JPanel productList = new JPanel();
                    productList.setLayout(new java.awt.GridLayout(0, 1)); // vertical list

                    int i = 1;
                    for (Products p : list) {
                        productList.add(new JLabel(i++ + ") " + p.printInfo()));
                    }

                    JScrollPane scrollPane = new JScrollPane(productList);
                    displayPanel.add(scrollPane, BorderLayout.CENTER);
                    
                    cartInputPanel.removeAll();
                    cartInputPanel.add(cartLabel);
                    cartInputPanel.add(cartInputField);
                    cartInputPanel.add(addToCartButton);
                    displayPanel.add(cartInputPanel, BorderLayout.SOUTH);
                }

                shopPanel.revalidate();
                shopPanel.repaint();
            }

            else if (e.getSource() == printCategory) {
                String input = JOptionPane.showInputDialog("Enter category:");
                if (input != null) {
                    List<Products> filtered = db.getFilteredProducts(input.trim().toUpperCase());

                    displayPanel.removeAll();
                    displayPanel.setLayout(new BorderLayout());

                    if (filtered.isEmpty()) {
                        displayPanel.add(new JLabel("No items found for that category."), BorderLayout.CENTER);
                    } else {
                        JPanel productList = new JPanel();
                        productList.setLayout(new java.awt.GridLayout(0, 1));

                        int i = 1;
                        for (Products p : filtered) {
                            productList.add(new JLabel(i++ + ") " + p.printInfo()));
                        }

                        JScrollPane scrollPane = new JScrollPane(productList);
                        displayPanel.add(scrollPane, BorderLayout.CENTER);
                        
                        cartInputPanel.removeAll();
                        cartInputPanel.add(cartLabel);
                        cartInputPanel.add(cartInputField);
                        cartInputPanel.add(addToCartButton);
                        displayPanel.add(cartInputPanel, BorderLayout.SOUTH);
                    }

                    shopPanel.revalidate();
                    shopPanel.repaint();
                }
            }
            
            else if (e.getSource() == addToCartButton) {
                String input = cartInputField.getText().trim();
                try {
                    int itemNumber = Integer.parseInt(input);
                    List<Products> all = db.getAllProducts();

                    if (itemNumber <= 0 || itemNumber > all.size()) {
                        JOptionPane.showMessageDialog(null, "Invalid item number.");
                    } else {
                        Products selected = all.get(itemNumber - 1);
                        cart.addToCart(selected);
                        JOptionPane.showMessageDialog(null, selected.getItemName() + " added to cart.");
                        cartInputField.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }

            else if (e.getSource() == balanceButton) {
                if (currentUser != null) {
                    db.loadUserBalance(currentUser);  // Ensure latest
                    double balance = currentUser.getMoney().getBalance();
                    currentBalanceLabel.setText("Current Balance: $" + String.format("%.2f", balance));

                    balancePanel.removeAll();
                    balancePanel.add(currentBalanceLabel);  // Show actual balance
                    balancePanel.add(balanceLabel);         // Label
                    balancePanel.add(balanceInput);         // Input
                    balancePanel.add(insertBalance);        // Button

                    shopPanel.revalidate();
                    shopPanel.repaint();
                }
            }
            
            else if (e.getSource() == cartButton) {
                List<Products> cartItems = cart.getCartItems();

                displayPanel.removeAll();
                displayPanel.setLayout(new BorderLayout());

                if (cartItems.isEmpty()) {
                    displayPanel.add(new JLabel("Your cart is empty."), BorderLayout.CENTER);
                } else {
                    JPanel cartList = new JPanel();
                    cartList.setLayout(new java.awt.GridLayout(0, 1));

                    int i = 1;
                    double total = 0.0;
                    for (Products p : cartItems) {
                        cartList.add(new JLabel(i++ + ") " + p.printInfo()));
                        total += p.getItemPrice();
                    }

                    JScrollPane scrollPane = new JScrollPane(cartList);
                    scrollPane.setPreferredSize(new java.awt.Dimension(1000, 400));
                    displayPanel.add(scrollPane, BorderLayout.CENTER);

                    // 🛒 Total summary + Checkout Button
                    JPanel checkoutPanel = new JPanel();
                    checkoutPanel.add(new JLabel("Total: $" + String.format("%.2f", total)));
                    checkoutPanel.add(checkoutButton);
                    displayPanel.add(checkoutPanel, BorderLayout.SOUTH);
                }

                shopPanel.revalidate();
                shopPanel.repaint();
            }
            
            else if (e.getSource() == checkoutButton) {
                List<Products> cartItems = cart.getCartItems();
                if (cartItems.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your cart is empty.");
                    return;
                }

                if (currentUser == null) {
                    JOptionPane.showMessageDialog(null, "Please log in first.");
                    return;
                }

                double currentBalance = currentUser.getMoney().getBalance();

                // Apply Discount if 3+ items
                Discounts discount = (cartItems.size() >= 3) ? new BulkDiscount() : new NoDiscount();
                double total = discount.applyDiscount(cart); // Should return discounted total

                if (currentBalance < total) {
                    double shortfall = total - currentBalance;
                    JOptionPane.showMessageDialog(null, "Insufficient balance. You need $" + String.format("%.2f", shortfall));
                } else {
                    // Deduct and persist
                    currentUser.getMoney().setBalance(currentBalance - total);
                    db.saveUserBalance(currentUser);

                    cart.clearCart();
                    JOptionPane.showMessageDialog(null, "Checkout complete! $" + String.format("%.2f", total) + " deducted from your balance.");

                    // Refresh balance and view
                    currentBalanceLabel.setText("Current Balance: $" + String.format("%.2f", currentUser.getMoney().getBalance()));
                    cartButton.doClick(); // Refresh view
                }
            }

            else if (e.getSource() == insertBalance) {
                if (currentUser != null) {
                    try {
                        double amount = Double.parseDouble(balanceInput.getText().trim());

                        if (amount <= 0) {
                            JOptionPane.showMessageDialog(null, "Please enter a positive amount.");
                            return;
                        }

                        currentUser.getMoney().depositMoney(amount);
                        db.saveUserBalance(currentUser);
                        currentBalanceLabel.setText("Current Balance: $" + String.format("%.2f", currentUser.getMoney().getBalance()));
                        balanceInput.setText("");
                        JOptionPane.showMessageDialog(null, "Balance inserted successfully.");

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    }
                }
            }
        }
    }

    @Override
    public void onLogin(Customer customer) {
        if (customer != null) {
            currentUser = customer;
            db.loadUserBalance(currentUser);
            double balance = currentUser.getMoney().getBalance();
            currentBalanceLabel.setText("Current Balance: $" + String.format("%.2f", balance));
            message.setText("Welcome, " + currentUser.getName());
            startShopping();
        } else {
            message.setText("Login failed. Try again.");
        }
    }
}
