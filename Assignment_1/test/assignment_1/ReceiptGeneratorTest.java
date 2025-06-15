package assignment_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class ReceiptGeneratorTest {

    private Cart cart;
    private Products p1, p2, p3;

    @Before
    public void setUp() {
        cart = new Cart();

        // Create sample products manually (without needing database here)
        p1 = new Products("T-Shirt", Category.CategoryType.SHIRTS, "M", 20.00);
        p2 = new Products("Hoodie", Category.CategoryType.HOODIES, "L", 50.00);
        p3 = new Products("Sneakers", Category.CategoryType.FOOTWEAR, "9", 80.00);
    }

    @Test
    public void testReceiptWithoutDiscount() {
        cart.addToCart(p1);
        cart.addToCart(p2);

        String receipt = ReceiptGenerator.generateReceipt(cart);

        assertTrue(receipt.contains("T-Shirt"));
        assertTrue(receipt.contains("Hoodie"));
        assertTrue(receipt.contains("Total: $70.00"));
        assertTrue(receipt.contains("Discount: $0.00"));
        assertTrue(receipt.contains("Final Total: $70.00"));
    }

    @Test
    public void testReceiptWithBulkDiscount() {
        cart.addToCart(p1);
        cart.addToCart(p2);
        cart.addToCart(p3);  // now triggers bulk discount

        String receipt = ReceiptGenerator.generateReceipt(cart);

        assertTrue(receipt.contains("T-Shirt"));
        assertTrue(receipt.contains("Hoodie"));
        assertTrue(receipt.contains("Sneakers"));

        double total = 20 + 50 + 80;
        double discount = total * 0.20;
        double finalTotal = total - discount;

        assertTrue(receipt.contains("Total: $150.00"));
        assertTrue(receipt.contains("Discount: $30.00"));
        assertTrue(receipt.contains("Final Total: $120.00"));
    }
}
