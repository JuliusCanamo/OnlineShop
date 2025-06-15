package assignment_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class CartTest {

    private Database db;
    private Cart cart;
    private List<Products> products;

    @Before
    public void setUp() {
        // Use a test database so we don't pollute production DB
        db = new Database();
        db.dbsetup(); // Create tables and insert sample products

        cart = new Cart();
        products = db.getAllProducts();

        // quick sanity check:
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }

    @Test
    public void testAddToCart_Products() {
        Products p = products.get(0);  // first product from DB
        cart.addToCart(p);
        assertEquals(1, cart.getItemCount());
        assertEquals(p, cart.getCartItems().get(0));
    }

    @Test
    public void testAddToCart_int_List() {
        cart.addToCart(2, products);  // item number is 2 (index 1 in list)
        assertEquals(1, cart.getItemCount());
        assertEquals(products.get(1), cart.getCartItems().get(0));
    }

    @Test
    public void testGetCartItems() {
        cart.addToCart(products.get(0));
        cart.addToCart(products.get(1));

        List<Products> items = cart.getCartItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(products.get(0)));
        assertTrue(items.contains(products.get(1)));
    }

    @Test
    public void testGetTotalCost() {
        cart.addToCart(products.get(0));
        cart.addToCart(products.get(1));

        double expectedTotal = products.get(0).getItemPrice() + products.get(1).getItemPrice();
        assertEquals(expectedTotal, cart.getTotalCost(), 0.01);
    }

    @Test
    public void testGetDiscountedTotal_NoDiscount() {
        cart.addToCart(products.get(0));
        cart.addToCart(products.get(1));

        double expectedTotal = products.get(0).getItemPrice() + products.get(1).getItemPrice();
        assertEquals(expectedTotal, cart.getDiscountedTotal(), 0.01);
    }

    @Test
    public void testGetDiscountedTotal_BulkDiscount() {
        cart.addToCart(products.get(0));
        cart.addToCart(products.get(1));
        cart.addToCart(products.get(2));

        double total = cart.getTotalCost();
        double expectedDiscounted = total * 0.8;

        assertEquals(expectedDiscounted, cart.getDiscountedTotal(), 0.01);
    }

    @Test
    public void testClearCart() {
        cart.addToCart(products.get(0));
        cart.addToCart(products.get(1));
        cart.clearCart();
        assertEquals(0, cart.getItemCount());
    }

    @Test
    public void testViewCart() {
        cart.viewCart(); // basic smoke test to verify no exceptions
    }
}
