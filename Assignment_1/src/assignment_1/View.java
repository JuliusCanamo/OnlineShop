/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author juliuscanamo
 */
public class View extends JFrame implements ShopListener {
    private JPanel userPanel = new JPanel();
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    JTextField unInput = new JTextField(10);
    JTextField pwInput = new JTextField(10);
    private JLabel wrongName = new JLabel("Wrong username or password!");
    
    JButton quitButton = new JButton("Quit");
    JButton loginButton = new JButton("Log in");
    
    public JLabel message = new JLabel("Welcome", JLabel.CENTER);
    public JTextField calcSolution = new JTextField(10);
    
    public View(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        this.setLocationRelativeTo(null);
        this.userPanel.add(uName);
        this.userPanel.add(unInput);
        this.userPanel.add(pWord);
        this.userPanel.add(pwInput);
        this.userPanel.add(loginButton);
        this.userPanel.add(this.message, BorderLayout.SOUTH);
        this.add(userPanel);
        this.setVisible(true);
    }

    @Override
    public void onLogin(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
