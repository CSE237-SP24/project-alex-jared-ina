package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.print.attribute.Size2DSyntax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class DeleteAccountTests {
	
	private BankAccount testAccount;
	private Menu m;
	
	@BeforeEach
	void setup() {
		m = new Menu();
		testAccount = new BankAccount();
		testAccount.setAccountName("test");
	}

	@Test
	void testDeleteExistingAccount() {
		
		m.addAccountToStorage(testAccount);
		m.deleteAccount(testAccount);
		
		int size = m.getNumAccounts();
		assertEquals(0, size, .01);	
	}
	
	@Test
	void testDeleteNonExistingAccount() {
		
		m.deleteAccount(testAccount);
		int size = m.getNumAccounts();
		assertEquals(0, size, .01);	
		
	}

}
