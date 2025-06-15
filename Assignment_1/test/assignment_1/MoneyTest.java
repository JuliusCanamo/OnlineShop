package assignment_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoneyTest {

    private Database db;
    private Customer customer;

    @Before
    public void setUp() {
        db = new Database();
        db.dbsetup();

        db.deleteUser("testuser_money");  // Clean old test data

        customer = db.registerUser("testuser_money", "password123");
        assertNotNull(customer);
    }

    @Test
    public void testInitialBalance() {
        db.loadUserBalance(customer);
        assertEquals(0.0, customer.getMoney().getBalance(), 0.001);
    }

    @Test
    public void testDepositMoneyAndSave() {
        db.loadUserBalance(customer);
        boolean result = customer.getMoney().depositMoney(100.0);
        assertTrue(result);
        db.saveUserBalance(customer);

        // Reload to verify
        db.loadUserBalance(customer);
        assertEquals(100.0, customer.getMoney().getBalance(), 0.001);
    }

    @Test
    public void testMultipleDeposits() {
        db.loadUserBalance(customer);
        customer.getMoney().depositMoney(50.0);
        customer.getMoney().depositMoney(30.0);
        db.saveUserBalance(customer);

        db.loadUserBalance(customer);
        assertEquals(80.0, customer.getMoney().getBalance(), 0.001);
    }

    @Test
    public void testDepositNegative() {
        db.loadUserBalance(customer);
        boolean result = customer.getMoney().depositMoney(-10.0);
        assertFalse(result);
        assertEquals(0.0, customer.getMoney().getBalance(), 0.001);
    }
}
