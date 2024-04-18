package bankapp;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
	
	private double balance;
	private String accountName;
    private List<String> transactions;  

	
	public BankAccount() {
		this.balance = 0;
		this.accountName = " ";
		this.transactions = new ArrayList<>();
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
		transactions.add("Deposited $" + amount);
	}
	
    public boolean withdraw(double amount) {
        if (amount <= 0 || this.balance < amount) {
            return false; 
        }
        this.balance -= amount;
        transactions.add("Withdrew $" + amount);
        return true; 
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
	
    public List<String> getTransactions() {
        return transactions;
    }
}
