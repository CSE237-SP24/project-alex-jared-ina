package test;

import bankapp.BankAccount;
import bankapp.Menu;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TransferTests {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }
	
    @Test
    void testSuccessfulTransfer() {
        BankAccount fromAccount = new BankAccount();
        fromAccount.setAccountName("FromAccount");
        fromAccount.deposit(500);
        BankAccount toAccount = new BankAccount();
        toAccount.setAccountName("ToAccount");
        menu.addAccountToStorage(fromAccount);
        menu.addAccountToStorage(toAccount);

        menu.transfer(fromAccount, toAccount, 100);

        assertEquals(400, fromAccount.getBalance());
        assertEquals(100, toAccount.getBalance());
    }
    
    @Test
    void testTransferMoreThanBalance() {
        BankAccount fromAccount = new BankAccount();
        fromAccount.setAccountName("FromAccount");
        fromAccount.deposit(200);
        BankAccount toAccount = new BankAccount();
        toAccount.setAccountName("ToAccount");
        menu.addAccountToStorage(fromAccount);
        menu.addAccountToStorage(toAccount);

        menu.transfer(fromAccount, toAccount, 300);

        assertEquals(200, fromAccount.getBalance(), "The balance should not change when attempting to transfer more than available.");
        assertEquals(0, toAccount.getBalance(), "No amount should be transferred to the destination account.");
    }
    
    @Test
    void testTransferToSameAccount() {
        BankAccount account = new BankAccount();
        account.setAccountName("SingleAccount");
        account.deposit(300);
        menu.addAccountToStorage(account);

        assertThrows(IllegalArgumentException.class, () -> menu.transfer(account, account, 100), "Transferring to the same account should not be allowed.");
    }
	
}
