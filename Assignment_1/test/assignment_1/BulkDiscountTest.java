package assignment_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BulkDiscountTest {

    private BulkDiscount bulkDiscount;
    private Cart cart;
    private Products p1, p2, p3;

    @Before
    public void setUp() {
        bulkDiscount = new BulkDiscount();
        cart = new Cart();

        // Create dummy products
        p1 = new Products("Product1", Category.CategoryType.SHIRTS, "M", 10.0);
        p2 = new Products("Product2", Category.CategoryType.HOODIES, "L", 20.0);
        p3 = new Products("Product3", Category.CategoryType.FOOTWEAR, "9", 30.0);
    }

    @Test
    public void testNoDiscountWhenLessThanThree() {
        cart.addToCart(p1);
        cart.addToCart(p2);

        double expected = cart.getTotalCost();
        double discounted = bulkDiscount.applyDiscount(cart);

        assertEquals(expected, discounted, 0.001);
    }

    @Test
    public void testApplyBulkDiscount() {
        cart.addToCart(p1);
        cart.addToCart(p2);
        cart.addToCart(p3); // Now 3 items — bulk discount should apply

        double total = cart.getTotalCost();
        double expectedDiscounted = total * 0.8;  // 20% off

        double actualDiscounted = bulkDiscount.applyDiscount(cart);
        assertEquals(expectedDiscounted, actualDiscounted, 0.001);
    }
}
