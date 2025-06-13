/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package assignment_1;

import java.io.FileNotFoundException;

/**
 *
 * @author juliuscanamo
 */
public class App {

    public static void main(String args[]) throws FileNotFoundException {
        //OnlineShop app = new OnlineShop();
        //app.ShopInterface();
        
        //View view = new View();
        //view.addActionListener(view.new ButtonListener());
        
        Database db = new Database();
        db.dbsetup();

        Cart cart = new Cart();
        OrderHistory orderHistory = new OrderHistory();
        View view = new View();
        Controller controller = new Controller(db, cart, null, view, orderHistory);

        view.setController(controller);    }  
}
