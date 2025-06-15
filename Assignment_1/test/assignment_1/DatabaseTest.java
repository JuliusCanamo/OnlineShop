package assignment_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class DatabaseTest {

    private Database db;

    @Before
    public void setUp() {
        db = new Database();
        db.dbsetup();
    }

    @Test
    public void testDbSetupAndInsertProducts() {
        // Just ensure setup works and products inserted
        List<Products> products = db.getAllProducts();
        assertNotNull(products);
        assertTrue(products.size() >= 30);  // Should contain at least your initial products
    }

    @Test
    public void testGetFilteredProducts() {
        List<Products> filtered = db.getFilteredProducts("PANTS");
        assertNotNull(filtered);
        for (Products p : filtered) {
            assertEquals(Category.CategoryType.PANTS, p.getItemType());
        }
    }

    @Test
    public void testRegisterUser() {
        db.deleteUser("testuser_db");

        Customer customer = db.registerUser("testuser_db", "password123");
        assertNotNull(customer);
        assertEquals("testuser_db", customer.getName());

        Customer duplicate = db.registerUser("testuser_db", "anotherpass");
        assertNull(duplicate); // Duplicate should fail
    }

    @Test
    public void testLoadAndSaveUserBalance() {
        db.deleteUser("balanceuser");
        Customer customer = db.registerUser("balanceuser", "balancepass");

        db.loadUserBalance(customer);
        assertEquals(0.0, customer.getMoney().getBalance(), 0.001);

        customer.getMoney().depositMoney(100.0);
        db.saveUserBalance(customer);

        db.loadUserBalance(customer);
        assertEquals(100.0, customer.getMoney().getBalance(), 0.001);
    }

    @Test
    public void testDeleteUser() {
        db.registerUser("deleteuser", "pass");
        db.deleteUser("deleteuser");

        Customer deleted = db.registerUser("deleteuser", "pass");
        assertNotNull(deleted); // Should allow registration again after deletion
    }
}
