package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.print.attribute.Size2DSyntax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bankapp.BankAccount;
import bankapp.Menu;

class CreateAccountTests {
	private BankAccount testAccount;
	private Menu m;
	
	@BeforeEach
	void setup() {
		m = new Menu();
		testAccount = new BankAccount();
		testAccount.setAccountName("test");
	}
	
	@Test
	void testDuplicateAccountInMap() { //properly reprompts user for valid name, once valid name is given both accounts are in, stuck in loop until valid account name
		BankAccount testAccount2 = new BankAccount();
		testAccount2.setAccountName("test");
		m.addAccountToStorage(testAccount);
		m.addAccountToStorage(testAccount2);
		int size = m.getNumAccounts();
		assertEquals(2, size, .01);	
	}
	
	@Test
	void testNewAccountInMap() { 
		BankAccount testAccount2 = new BankAccount();
		testAccount2.setAccountName("test2");
		m.addAccountToStorage(testAccount);
		m.addAccountToStorage(testAccount2);
		int size = m.getNumAccounts();
		assertEquals(2, size, .01);	
	}
	
	

}
