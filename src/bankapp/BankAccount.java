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
	
    public void deposit(double amount, boolean logTransaction) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance += amount;
        if (logTransaction) {
            transactions.add("Deposited $" + amount);  // Log the transaction
        }
    }

    // Overload the deposit method to maintain backward compatibility
    public void deposit(double amount) {
        deposit(amount, true);
    }
	
    public boolean withdraw(double amount, boolean logTransaction) {
        if (amount <= 0 || this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        if (logTransaction) {
            transactions.add("Withdrew $" + amount);  // Log the transaction
        }
        return true;
    }

    // Overload the withdraw method to maintain backward compatibility
    public boolean withdraw(double amount) {
        return withdraw(amount, true);
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
