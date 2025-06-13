package assignment_1;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author juliuscanamo
 */

public class View extends JFrame implements ShopListener {

    private final Database db = new Database();
    private final Color backgroundColor = Color.decode("#011826");
    private final Color accentColor = Color.decode("#24A3BF");
    private final Color secondaryColor = Color.decode("#266573");
    private final Color lightColor = Color.decode("#3E818A");
    private final Color extraLightColor = Color.decode("#4F99A1");

    private JPanel mainPanel = new JPanel(new CardLayout());
    private JPanel loginPanel = new JPanel();
    private JPanel shopPanel = new JPanel();
    private JPanel displayPanel = new JPanel();
    private JPanel cartInputPanel = new JPanel();

    private JButton startButton = new JButton("Start");
    private JButton printAll = new JButton("View Inventory");
    private JButton printCategory = new JButton("View By Category");
    private JButton cartButton = new JButton("View Cart");
    private JButton balanceButton = new JButton("View Balance");
    private JButton checkoutButton = new JButton("Checkout");
    private JButton addToCartButton = new JButton("Add to Cart");
    private JButton backToStartButton = new JButton("Back to Start");
    private JButton discountButton = new JButton("View Discounts");
    private JButton orderHistoryButton = new JButton("Order History");

    private final OrderHistory orderHistory = new OrderHistory();
    private final Cart cart = new Cart();

    private JLabel currentBalanceLabel = new JLabel("Current Balance: $0.00");
    private JLabel userLabel = new JLabel("Logged in: Guest");

    private JTextField cartInputField = new JTextField(5);
    private Customer currentUser;
    private List<Products> displayedProducts = null;

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public View() {
        db.dbsetup();
        setTitle("Collection World Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        setLocationRelativeTo(null);
        initLoginPanel();
        initShopPanel();
        add(mainPanel);
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
    }

    private void showShop() {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "shop");
        showWelcomeMessage();
    }

    private void showLogin() {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "login");
    }
    
        private void showLoginForm() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(extraLightColor);

        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(15);
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            Customer customer = db.checkName(username, password);

            if (customer != null) {
                db.loadUserBalance(customer);
                onLogin(customer);
            } else {
                int regOption = JOptionPane.showConfirmDialog(this, "User not found. Register?", "Register", JOptionPane.YES_NO_OPTION);
                if (regOption == JOptionPane.YES_OPTION) {
                    Customer newUser = db.registerUser(username, password);
                    if (newUser != null) {
                        db.loadUserBalance(newUser);
                        currentUser = newUser;
                        showShop();
                        updateBalanceDisplay();
                        updateUserLabel();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed.");
                }
            }
        }
    }

    private void initLoginPanel() {
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Welcome to Collection World!");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(lblTitle, gbc);

        gbc.gridy = 1;
        styleButton(startButton);
        loginPanel.add(startButton, gbc);

        startButton.addActionListener(e -> handleStartupChoice());

        mainPanel.add(loginPanel, "login");
        showLogin();
    }

    private void handleStartupChoice() {
        JDialog dialog = new JDialog(this, "Select Option", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 200);
        dialog.getContentPane().setBackground(extraLightColor);
        dialog.setLocationRelativeTo(this);

        JLabel label = new JLabel("Please select how you want to proceed:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setBackground(extraLightColor);

        JButton loginBtn = new JButton("Login");
        JButton guestBtn = new JButton("Guest");
        styleButton(loginBtn);
        styleButton(guestBtn);

        buttonPanel.add(loginBtn);
        buttonPanel.add(guestBtn);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> {
            dialog.dispose();
            showLoginForm();
        });

        guestBtn.addActionListener(e -> {
            dialog.dispose();
            currentUser = new Customer("Guest", "Guest");
            controller.setCurrentUser(currentUser);
            showShop();
            updateBalanceDisplay();
            updateUserLabel();
        });

        dialog.setVisible(true);
    }

    private void initShopPanel() {
        shopPanel.setLayout(new BorderLayout(10, 10));
        shopPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        shopPanel.setBackground(backgroundColor);

        JPanel topMenu = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        topMenu.setBackground(secondaryColor);

        styleButton(printAll);
        styleButton(printCategory);
        styleButton(cartButton);
        styleButton(balanceButton);
        styleButton(discountButton);
        styleButton(orderHistoryButton);
        styleButton(backToStartButton);

        topMenu.add(printAll);
        topMenu.add(printCategory);
        topMenu.add(cartButton);
        topMenu.add(balanceButton);
        topMenu.add(discountButton);
        topMenu.add(orderHistoryButton);
        topMenu.add(backToStartButton);

        JScrollPane topMenuScroll = new JScrollPane(topMenu);
        topMenuScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        topMenuScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        topMenuScroll.setBorder(BorderFactory.createTitledBorder("Menu"));
        topMenuScroll.setPreferredSize(new Dimension(800, 90));
        topMenuScroll.getViewport().setBackground(secondaryColor);
        topMenuScroll.getHorizontalScrollBar().setUnitIncrement(16);
        styleScrollBar(topMenuScroll);

        shopPanel.add(topMenuScroll, BorderLayout.NORTH);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        displayPanel.setBackground(lightColor);
        shopPanel.add(displayPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(backgroundColor);
        currentBalanceLabel.setForeground(Color.WHITE);
        currentBalanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        bottomPanel.add(currentBalanceLabel, BorderLayout.WEST);
        bottomPanel.add(userLabel, BorderLayout.EAST);

        shopPanel.add(bottomPanel, BorderLayout.SOUTH);

        styleButton(addToCartButton);
        styleButton(checkoutButton);

        printAll.addActionListener(e -> showAllProducts());
        printCategory.addActionListener(e -> showByCategory());
        cartButton.addActionListener(e -> showCart());
        balanceButton.addActionListener(e -> controller.addBalance());
        discountButton.addActionListener(e -> showDiscounts());
        orderHistoryButton.addActionListener(e -> showOrderHistory());
        addToCartButton.addActionListener(e -> {
            try {
                int itemNumber = Integer.parseInt(cartInputField.getText().trim());
                controller.addToCart(itemNumber, displayedProducts);
                cartInputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid item number.");
            }
        });

        checkoutButton.addActionListener(e -> controller.checkout());
        backToStartButton.addActionListener(e -> returnToStart());

        mainPanel.add(shopPanel, "shop");
    }

    private void returnToStart() {
        cart.clearCart();
        currentUser = null;
        showLogin();
    }

    private void refresh() {
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private void styleButton(JButton button) {
        button.setBackground(accentColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
    }

    private void updateUserLabel() {
        String username = (currentUser != null) ? currentUser.getName() : "Guest";
        userLabel.setText("Logged in: " + username);
    }

    private void styleScrollBar(JScrollPane scrollPane) {
        JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
        horizontalBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = accentColor;
                this.trackColor = secondaryColor;
            }
        });
    }

    private void showWelcomeMessage() {
        displayPanel.removeAll();
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(lightColor);

        JLabel welcomeTitle = new JLabel("Welcome to Collection World!");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeTitle.setForeground(Color.WHITE);
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel("View store items by selecting 'View Inventory' or 'View By Category'.");
        instructions.setFont(new Font("Arial", Font.PLAIN, 16));
        instructions.setForeground(Color.LIGHT_GRAY);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeTitle);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        welcomePanel.add(instructions);
        welcomePanel.add(Box.createVerticalGlue());

        displayPanel.add(welcomePanel, BorderLayout.CENTER);
        refresh();
    }

    @Override
    public void onLogin(Customer customer) {
        if (controller != null && customer != null) {
            currentUser = customer;
            controller.setCurrentUser(customer);
            updateBalanceDisplay();
            updateUserLabel();
            showShop();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Controller not initialized.");
        }
}

    void updateBalanceDisplay() {
        db.loadUserBalance(currentUser);
        double balance = currentUser.getMoney().getBalance();
        currentBalanceLabel.setText("Current Balance: $" + String.format("%.2f", balance));
        refresh();
    }

    // Show all products
    private void showAllProducts() {
        List<Products> products = db.getAllProducts();
        displayedProducts = products;
        displayProducts(products);
    }

// Show by category selection
    private void showByCategory() {
        Category.CategoryType[] categoryTypes = Category.CategoryType.values();
        JComboBox<Category.CategoryType> categoryDropdown = new JComboBox<>(categoryTypes);

        int option = JOptionPane.showConfirmDialog(this, categoryDropdown, "Select Category", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Category.CategoryType selectedCategory = (Category.CategoryType) categoryDropdown.getSelectedItem();
            List<Products> filtered = db.getFilteredProducts(selectedCategory.toString());
            displayedProducts = filtered;
            displayProducts(filtered);
        }
    }

// Display product cards nicely in grid
    private void displayProducts(List<Products> products) {
        displayPanel.removeAll();

        int columns = 3;
        int rows = (int) Math.ceil(products.size() / (double) columns);
        JPanel gridPanel = new JPanel(new GridLayout(rows, columns, 15, 15));
        gridPanel.setBackground(lightColor);

        int i = 1;
        for (Products p : products) {
            JPanel productCard = new JPanel();
            productCard.setLayout(new BoxLayout(productCard, BoxLayout.Y_AXIS));
            productCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            productCard.setBackground(extraLightColor);
            productCard.setPreferredSize(new Dimension(220, 180));

            String productText = "<html><b>Item #" + i + "</b><br>Product: " + p.getItemName() + "<br>Category: "
                    + p.getItemType() + "<br>Size: " + p.getItemSize() + "<br>Price: $"
                    + String.format("%.2f", p.getItemPrice()) + "</html>";

            JLabel productLabel = new JLabel(productText);
            productLabel.setPreferredSize(new Dimension(200, 150));
            productLabel.setHorizontalAlignment(SwingConstants.CENTER);
            productCard.add(productLabel);
            gridPanel.add(productCard);
            i++;
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        cartInputPanel.removeAll();
        cartInputPanel.add(new JLabel("Enter item number to add:"));
        cartInputPanel.add(cartInputField);
        cartInputPanel.add(addToCartButton);
        displayPanel.add(cartInputPanel, BorderLayout.SOUTH);
        refresh();
    }


// View current balance + add funds
    private void showBalance() {
        db.loadUserBalance(currentUser);
        double balance = currentUser.getMoney().getBalance();
        currentBalanceLabel.setText("Current Balance: $" + String.format("%.2f", balance));
        refresh();

        JPanel balancePanel = new JPanel(new GridLayout(3, 1, 10, 10));
        balancePanel.add(new JLabel("Current Balance: $" + String.format("%.2f", balance)));

        JTextField addBalanceField = new JTextField();
        balancePanel.add(new JLabel("Insert Additional Balance:"));
        balancePanel.add(addBalanceField);

        int option = JOptionPane.showConfirmDialog(this, balancePanel, "Balance", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double amount = Double.parseDouble(addBalanceField.getText().trim());
                if (amount > 0) {
                    currentUser.getMoney().depositMoney(amount);
                    db.saveUserBalance(currentUser);
                    JOptionPane.showMessageDialog(this, "Balance updated!");
                    showBalance();
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid positive amount.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        }
    }

// View current discounts info
    private void showDiscounts() {
        displayPanel.removeAll();
        JPanel discountPanel = new JPanel();
        discountPanel.setLayout(new BoxLayout(discountPanel, BoxLayout.Y_AXIS));
        discountPanel.setBackground(lightColor);

        JLabel title = new JLabel("Current Active Discounts");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bulkDiscount = new JLabel("• Buy 3 or more items: 20% OFF (Bulk Discount)");
        bulkDiscount.setFont(new Font("Arial", Font.PLAIN, 18));
        bulkDiscount.setForeground(Color.LIGHT_GRAY);
        bulkDiscount.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel noDiscount = new JLabel("• Less than 3 items: No Discount Applied");
        noDiscount.setFont(new Font("Arial", Font.PLAIN, 18));
        noDiscount.setForeground(Color.LIGHT_GRAY);
        noDiscount.setAlignmentX(Component.CENTER_ALIGNMENT);

        discountPanel.add(Box.createVerticalGlue());
        discountPanel.add(title);
        discountPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        discountPanel.add(bulkDiscount);
        discountPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        discountPanel.add(noDiscount);
        discountPanel.add(Box.createVerticalGlue());

        displayPanel.add(discountPanel, BorderLayout.CENTER);
        refresh();
    }

    // Display the logged-in user's order history
    private void showOrderHistory() {
        displayPanel.removeAll();

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setBackground(lightColor);

        JLabel title = new JLabel("Order History");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderPanel.add(title);
        orderPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Only available for logged-in users, not guest
        if (currentUser == null || currentUser.getName().equalsIgnoreCase("Guest")) {
            JLabel guestLabel = new JLabel("Order history not available for guests.");
            guestLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            guestLabel.setForeground(Color.LIGHT_GRAY);
            guestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderPanel.add(guestLabel);
        } else {
            String userFolderPath = "OrderHistory/" + currentUser.getName();
            File userFolder = new File(userFolderPath);
            File[] orderFiles = userFolder.listFiles((dir, name) -> name.endsWith(".txt"));

            if (orderFiles == null || orderFiles.length == 0) {
                JLabel noOrdersLabel = new JLabel("No orders found.");
                noOrdersLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                noOrdersLabel.setForeground(Color.LIGHT_GRAY);
                noOrdersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                orderPanel.add(noOrdersLabel);
            } else {
                int orderNumber = 1;
                for (File file : orderFiles) {
                    JPanel orderBox = new JPanel();
                    orderBox.setLayout(new BoxLayout(orderBox, BoxLayout.Y_AXIS));
                    orderBox.setBackground(extraLightColor);
                    orderBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                    orderBox.setMaximumSize(new Dimension(600, 150));
                    orderBox.add(new JLabel("Order #" + orderNumber));
                    orderBox.add(Box.createRigidArea(new Dimension(0, 10)));

                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            JLabel productLabel = new JLabel(line);
                            productLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                            orderBox.add(productLabel);
                        }
                    } catch (IOException e) {
                        JLabel errorLabel = new JLabel("Failed to load order.");
                        orderBox.add(errorLabel);
                    }

                    orderBox.add(Box.createRigidArea(new Dimension(0, 10)));
                    orderPanel.add(orderBox);
                    orderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                    orderNumber++;
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(orderPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        refresh();
    }

    // Displays the current cart contents
    void showCart() {
        List<Products> items = cart.getCartItems();
        displayPanel.removeAll();

        if (items.isEmpty()) {
            displayPanel.add(new JLabel("Your cart is empty."), BorderLayout.CENTER);
        } else {
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            double total = 0;

            for (Products p : items) {
                listPanel.add(new JLabel(p.printInfo()));
                listPanel.add(Box.createVerticalStrut(10));
                total += p.getItemPrice();
            }

            JScrollPane scrollPane = new JScrollPane(listPanel);
            displayPanel.add(scrollPane, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(new JLabel("Total: $" + String.format("%.2f", total)));
            bottomPanel.add(checkoutButton);
            displayPanel.add(bottomPanel, BorderLayout.SOUTH);
        }
        refresh();
    }
    
}
