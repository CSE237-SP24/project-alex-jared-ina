package bankapp;

public class BankAccount {
	
	private double balance;
	private String accountName;
	
	//Constructors - not tested
	public BankAccount() {
		this.balance = 0;
		this.accountName = " ";
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
	
	
	
	//getters and setters - not tested
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
		// TODO Auto-generated method stub
		this.accountName = name;
	}
}
