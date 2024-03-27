package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
class WithdrawDepositTests {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
        account.setAccountName("Test Account");
    }

    @Test
    void testDepositPositiveAmount() {
        account.deposit(100);
        assertEquals(100, account.getBalance(), "Depositing 100 should increase balance to 100");
    }

    @Test
    void testDepositNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(-100),
                "Depositing a negative amount should throw IllegalArgumentException");
        assertEquals("Amount must be positive", exception.getMessage());
    }

    @Test
    void testWithdrawSufficientBalance() {
        account.setBalance(200);
        account.withdraw(100);
        assertEquals(100, account.getBalance(), "Withdrawing 100 from 200 should leave a balance of 100");
    }

    @Test
    void testWithdrawInsufficientBalance() {
        account.setBalance(50);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(100),
                "Withdrawing more than the account balance should throw IllegalArgumentException");
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void testWithdrawNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100),
                "Withdrawing a negative amount should throw IllegalArgumentException");
        assertEquals("Amount must be positive", exception.getMessage());
    }

}
