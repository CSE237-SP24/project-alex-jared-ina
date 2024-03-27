package bankapp;

public class BankAccount {
	
	private double balance;
	private String accountName;
	
	public BankAccount() {
		this.balance = 0;
		this.accountName = " ";
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
	
	public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("Amount must be positive");
	    }
	    if (this.balance < amount) {
	        throw new IllegalArgumentException("Insufficient funds");
	    }
	    this.balance -= amount;
	}
	
	public double getBalance() {
		return this.balance;
	}

	public String getAccountName() {
		return accountName;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setAccountName(String name) {
		this.accountName = name;
	}
}
