package bankapp;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password; 
    private Map<String, BankAccount> accounts; 

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new HashMap<>();
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountName(), account);
    }

    public BankAccount getAccount(String accountName) {
        return accounts.get(accountName);
    }

    public String getUsername() {
        return username;
    }

    public Map<String, BankAccount> getAccounts() {
        return accounts;
    }
    

	public void transfer(BankAccount from, BankAccount to, double amount) {
		if(from == to) {
			throw new IllegalArgumentException("Cant transfer to and from same account");
		}
	    if (from.getBalance() >= amount) {
	        from.withdraw(amount); 
	        to.deposit(amount);
	        System.out.println("Transferred $" + amount + " from " + from.getAccountName() + " to " + to.getAccountName());
	        System.out.println("New balance of " + from.getAccountName() + ": $" + from.getBalance());
	        System.out.println("New balance of " + to.getAccountName() + ": $" + to.getBalance());
	    } else {
	        System.out.println("Insufficient balance in account " + from.getAccountName() + " to complete the transfer.");
	    }
	}
	
	
}
