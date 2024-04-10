package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;
import bankapp.User;

class MenuTests {
	private User user1;
	private User user2;
	private BankAccount account1;
	private BankAccount account2;
	private Menu menu;
	private Map<String, User> users;
	
	 @BeforeEach
	    void setUp() {
		 	Map<String, User> users = new HashMap<>();
	        User user1 = new User("testUser", "password");
	        users.put("testUser", user1);
	 }
	 
	 
	
	 @Test
	    void deleteUserOneAccount() {
		 	user1.addAccount(account1);
		 	
		 	menu.deleteUser(user1);
		 	
		 	assertEquals(0, users.size());
		 	assertEquals(0, user1.getAccounts().size());
	 }
	 
	 @Test
	    void deleteUserManyAccounts() {
		 user1.addAccount(account1);
		 user1.addAccount(account2);
		 
		 menu.deleteUser(user1);
		 
		 assertEquals(0, users.size());
		 assertEquals(0, user1.getAccounts().size());
	 }
		 
		 
		 	
	 
	 @Test
	    void deleteUserNoAccounts() {
		 
		 	menu.deleteUser(user1);
		 
		 	assertEquals(0, users.size());
		 	assertEquals(0, user1.getAccounts().size());
		 
		 
		 
	 }
	 
	 @Test
	    void deleteUserWithOtherExistingUsersPresent() {
		 	User user2 = new User("testUser2", "password");
	        users.put("testUser2", user2);
	        
	        menu.deleteUser(user1);
	        
	        assertEquals(1, users.size());
		 	assertEquals(0, user1.getAccounts().size());
 
	 }
	 

}
