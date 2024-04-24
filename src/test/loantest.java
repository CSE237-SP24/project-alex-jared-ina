package test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

class loantest {

    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount();
    }
    
    @Test
    public void testTakeLoan() {
        account.takeLoan(5000, 12);
        assertEquals(5000, account.getLoanAmount(), 0.01);
        assertEquals(5000, account.getBalance(), 0.01);
        assertEquals(12, account.getPayments());
    }
    
    @Test
    public void testCalculateMonthlyPayment() {
        account.takeLoan(1000, 12);
        double expectedPayment = (1000 * (0.05 / 12)) / (1 - Math.pow(1 + (0.05 / 12), -12));
        expectedPayment = Math.round(expectedPayment * 100.0) / 100.0;
        assertEquals(expectedPayment, account.calculateMonthlyPayment(), 0.01);
    }
    
    @Test
    public void testMakePayment() {
        account.takeLoan(1200, 12);
        double initialLoanAmount = account.getLoanAmount();
        double monthlyPayment = account.calculateMonthlyPayment();
        account.makePayment();
        assertEquals(initialLoanAmount - monthlyPayment, account.getLoanAmount(), 0.01);
        assertEquals(11, account.getPeriod());
    }

    @Test
    public void testGetLoanDetails() {
        account.takeLoan(1200, 12);
        String expectedDetails = "Loan Balance: $1200.0\nNext Payment: $" + account.calculateMonthlyPayment() + "\nPayments Remaining: 12";
        assertEquals(expectedDetails, account.getLoanDetails());
    }

    @Test
    public void testViewBalanceWithLoan() {
        account.takeLoan(1000, 12);
        assertTrue(account.getLoanAmount() > 0);
    }

}
