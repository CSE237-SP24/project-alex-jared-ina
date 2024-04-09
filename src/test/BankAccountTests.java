package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.User;
import bankapp.Menu;

class BankAccountTests {
    private User user;
    private BankAccount mainAccount;
    private BankAccount savingsAccount;
    
    
    @BeforeEach
    void setup() {
        user = new User("username", "password");
                
        mainAccount = new BankAccount();
        mainAccount.setAccountName("Main Account");
        mainAccount.deposit(1000);
        
        savingsAccount = new BankAccount();
        savingsAccount.setAccountName("Savings Account");
        savingsAccount.deposit(500);

        user.addAccount(mainAccount);
        user.addAccount(savingsAccount);
    }
  
    //DEPOSIT TESTS 


    @Test
    void testDeposit() {
        mainAccount.deposit(200);
        assertEquals(1200, mainAccount.getBalance(), "Depositing 200 should increase balance to 1200");
    }

    @Test
    void testDepositZero() {
        mainAccount.deposit(0);
        assertEquals(1000, mainAccount.getBalance(), "Depositing zero should not change the balance");
    }
    
    @Test
    void testMultipleDeposits() {
        mainAccount.deposit(100);
        mainAccount.deposit(200);
        mainAccount.deposit(300);
        assertEquals(1600, mainAccount.getBalance(), "Balance should be the total of all deposits");
    }
    
    void testDepositNegative() {
        double initialBalance = mainAccount.getBalance();
        
        assertThrows(IllegalArgumentException.class, () -> mainAccount.deposit(-1001), "Depositing a negative amount should throw IllegalArgumentException");

        assertEquals(initialBalance, mainAccount.getBalance(), "Balance should remain unchanged when attempting negative deposit.");
    }
    
    
    //WITHDRAW TESTS 
    
    
    @Test
    void testWithdraw() {
        boolean result = mainAccount.withdraw(300);
        assertTrue(result, "Withdrawal of 300 should be successful");
        assertEquals(700, mainAccount.getBalance(), "After withdrawing 300, the balance should be 700");
    }
    @Test
    void testWithdrawMoreThanBalance() {
        boolean result = mainAccount.withdraw(1001);
        assertFalse(result, "Should not be able to withdraw more than the account balance");
        assertEquals(1000, mainAccount.getBalance(), "Balance should remain unchanged when attempting to withdraw too much");
    }
    
    @Test
    void testWithdrawNegative() {
        boolean result = mainAccount.withdraw(-1001);
        assertFalse(result, "Should not be able to withdraw negative");
        assertEquals(1000, mainAccount.getBalance(), "Balance should remain unchanged when attempting engative withdraw.");
    }
    
    
    @Test
    void testWithdrawExactBalance() {
        boolean result = mainAccount.withdraw(1000);
        assertTrue(result, "Should be able to withdraw the exact account balance");
        assertEquals(0, mainAccount.getBalance(), "Withdrawing the exact balance should leave the account empty");
    }

    @Test
    void testMultipleWithdrawals() {
        mainAccount.withdraw(100);
        mainAccount.withdraw(200);
        mainAccount.withdraw(300);
        assertEquals(400, mainAccount.getBalance(), "Balance should be reduced by the total of all withdrawals");
    }

    @Test
    void testMixedDepositsAndWithdrawals() {
        mainAccount.withdraw(100);
        mainAccount.deposit(200);
        mainAccount.withdraw(50);
        assertEquals(1000-100+200-50, mainAccount.getBalance(), "Balance should reflect the net result of deposits and withdrawals");
    }
    
    //TRANSFER TESTS
    
    @Test
    void testTransferBetweenMainAndSavings() {

        double initialMainBalance = mainAccount.getBalance();
        double initialSavingsBalance = savingsAccount.getBalance();
        double transferAmount = 200;

        user.transfer(mainAccount, savingsAccount, transferAmount);

        assertEquals(initialMainBalance - transferAmount, mainAccount.getBalance(), "Main account should be decreased by the transfer amount");
        assertEquals(initialSavingsBalance + transferAmount, savingsAccount.getBalance(), "Savings account should be increased by the transfer amount");
    }
    
    @Test
    void testTransferOverLimit() {
    	
        double MainBalance = mainAccount.getBalance();
        double SavingsBalance = savingsAccount.getBalance();
        double transferAmount = MainBalance + 500; 

        user.transfer(mainAccount, savingsAccount, transferAmount);

        assertEquals(MainBalance, mainAccount.getBalance(), "Failed transfer");
        assertEquals(SavingsBalance, savingsAccount.getBalance(), "Failed transfer");
    }
   
    @Test
    void testTransferNegativeAmount() {

        double initialMainBalance = mainAccount.getBalance();
        double initialSavingsBalance = savingsAccount.getBalance();

        assertThrows(IllegalArgumentException.class, () -> user.transfer(mainAccount, savingsAccount, -100), "Transferring a negative amount should throw an exception");

        assertEquals(initialMainBalance, mainAccount.getBalance(), "Failed transfer");
        assertEquals(initialSavingsBalance, savingsAccount.getBalance(), "Failed transfer");
    }
    
    @Test
    void testTransferToSameAccount() {

        double initialBalance = mainAccount.getBalance();

        assertThrows(IllegalArgumentException.class, () -> user.transfer(mainAccount, mainAccount, 100), "Transferring between the same account should throw an exception");

        assertEquals(initialBalance, mainAccount.getBalance(), "Failed transfer");
    }
    

   
    
}
