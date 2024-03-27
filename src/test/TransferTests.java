package test;

import bankapp.BankAccount;
import bankapp.Menu;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TransferTests {

    private Menu menu;
    private BankAccount fromAccount;
    private BankAccount toAccount;
    
    @BeforeEach
    void setUp() {
        menu = new Menu();
        fromAccount = new BankAccount();
        fromAccount.setAccountName("fromAccount");
        fromAccount.deposit(1000);
        toAccount = new BankAccount();
        toAccount.setAccountName("toAccount");
        toAccount.deposit(1000);
        menu.addAccountToStorage(fromAccount);
        menu.addAccountToStorage(toAccount);

    }
	
    @Test
    void testSuccessfulTransfer() {
        menu.transfer(fromAccount, toAccount, 100);
        assertEquals(900, fromAccount.getBalance());
        assertEquals(1100, toAccount.getBalance());
    }
    
    @Test
    void testTransferMoreThanBalance() {
        menu.transfer(fromAccount, toAccount, 3000);
        assertEquals(1000, fromAccount.getBalance(), "The balance should not change when attempting to transfer more than available.");
        assertEquals(1000, toAccount.getBalance(), "No amount should be transferred to the destination account.");
    }
    
    @Test
    void testTransferToSameAccount() {
        assertThrows(IllegalArgumentException.class, () -> menu.transfer(fromAccount, fromAccount, 100), "Transferring to the same account should not be allowed.");
    }
	
}
