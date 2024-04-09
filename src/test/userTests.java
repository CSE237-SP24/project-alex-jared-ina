package test;
import bankapp.BankAccount;
import bankapp.User;
import bankapp.Menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bankapp.BankAccount;
import bankapp.Menu;
import bankapp.User;

class userTests {
	   
    private User user;
    private BankAccount account1;
    private BankAccount account2;



    @BeforeEach
    void setUp() {
        user = new User("username", "password");
        account1 = new BankAccount();
        account1.setAccountName("Checking");
        account1.deposit(100); // Assuming a deposit method that increases the balance
        account2 = new BankAccount();
        account2.setAccountName("Savings");
        account2.deposit(200); // Assuming a deposit method that increases the balance
    }

    @Test
    void authenticateSuccess() {
        assertTrue(user.authenticate("password"));
    }

    @Test
    void authenticateFailure() {
        assertFalse(user.authenticate("wrongPassword"));
    }

    @Test
    void addAndGetAccount() {
        user.addAccount(account1);
        assertEquals(account1, user.getAccount("Checking"), "Failed to get the added account.");
    }

    @Test
    void getAccountsSize() {
        user.addAccount(account1);
        user.addAccount(account2);
        assertEquals(2, user.getAccounts().size(), "Accounts size should be 2.");
    }

    @Test
    void deleteAccount() {
        user.addAccount(account1);
        user.addAccount(account2);
        user.getAccounts().remove("Checking"); // Simulate deleting an account
        assertNull(user.getAccount("Checking"), "The account should be deleted.");
        assertEquals(1, user.getAccounts().size(), "Accounts size should be 1 after deletion.");
    }
    
    

}
