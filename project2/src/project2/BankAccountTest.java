package project2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount acc = new BankAccount(1000.0);
        acc.deposit(500.0);
        assertEquals(1500.0, acc.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        BankAccount acc = new BankAccount(1000.0);
        acc.withdraw(300.0);
        assertEquals(700.0, acc.getBalance(), 0.001);
    }

    @Test(expected = RuntimeException.class)
    public void testInsufficientBalance() {
        BankAccount acc = new BankAccount(1000.0);
        acc.withdraw(2000.0);
    }
}