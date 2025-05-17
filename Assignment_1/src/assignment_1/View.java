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
    
    private JLabel balanceLabel = new JLabel("Insert Balance: ");
    JTextField balanceInput = new JTextField(100);
    
    private Customer currentUser;
    private Inventory inventory = new Inventory();
    private Cart cart = new Cart();
    
    public JLabel message = new JLabel("Welcome", JLabel.CENTER);
    public JTextField calcSolution = new JTextField(10);
    
    public View(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        this.setLocationRelativeTo(null);
        this.db.dbsetup();
        this.userPanel.add(uName);
        this.userPanel.add(unInput);
        this.userPanel.add(pWord);
        this.userPanel.add(pwInput);
        this.userPanel.add(loginButton);
        this.userPanel.add(this.message, BorderLayout.SOUTH);
        this.add(userPanel);
        this.setVisible(true);
    }
    
    public void startShopping(){
        shopPanel.removeAll();
        shopPanel.add(printAll);
        shopPanel.add(printCategory);
        shopPanel.add(cartButton);
        shopPanel.add(balanceButton);
        
        shopPanel.add(balanceLabel);
        shopPanel.add(balanceInput);
        shopPanel.add(insertBalance);
        
        this.getContentPane().removeAll();
        shopPanel.setVisible(true);
        this.add(shopPanel);
        this.revalidate();
        this.repaint();
    }
    
    public void addActionListener(ActionListener listener){
        this.loginButton.addActionListener(listener);
        this.quitButton.addActionListener(listener);
        this.printAll.addActionListener(listener);
        this.printCategory.addActionListener(listener);
        this.insertBalance.addActionListener(listener);
        this.printAll.addActionListener(listener);
    }
    
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == printAll) {
                List<Products> list = db.getAllProducts();
                StringBuilder sb = new StringBuilder();
                int i = 1;
                for (Products p : list) {
                    sb.append(i++).append(") ").append(p.printInfo()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);

            } else if (e.getSource() == printCategory) {
                String input = JOptionPane.showInputDialog("Enter category:");
                if (input != null) {
                    List<Products> filtered = db.getFilteredProducts(input.trim().toUpperCase());
                    if (filtered.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No items found for that category.");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        int i = 1;
                        for (Products p : filtered) {
                            sb.append(i++).append(") ").append(p.printInfo()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString(), "Filtered Products", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
    
    @Override
    public void onLogin(Customer customer) {
        if (customer != null) {
            currentUser = customer;
            message.setText("Welcome, " + currentUser.getName());
            startShopping();
        } else {
            message.setText("Login failed. Try again.");
        }
    }
}
