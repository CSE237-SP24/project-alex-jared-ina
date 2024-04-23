package bankapp;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
	
	private double balance;
	private String accountName;
    private List<String> transactions;  
    private double loanAmount;
    private int loanPeriod;
    private double interestRate;
    private int paymentsMade;
    private int TOTAL_PAYMENTS;

	public BankAccount() {
		this.balance = 0;
		this.accountName = " ";
		this.transactions = new ArrayList<>();
		this.loanAmount = 0;
		this.loanPeriod = 0;
		this.interestRate = .05;
        this.paymentsMade = 0;
        this.TOTAL_PAYMENTS = loanPeriod;
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
    
    public void takeLoan(double amount, int months) {
        this.loanAmount = amount;
        this.loanPeriod = months;
        this.TOTAL_PAYMENTS = this.loanPeriod;
        this.balance += amount;
    }
    
    public double calculateMonthlyPayment() {
        double monthlyRate = interestRate / 12;
        double monthlyPayment = (getLoanAmount() * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -loanPeriod));
        monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;
        return monthlyPayment;
    }
    
    public String getLoanDetails() {
        return "Loan Balance: $" + this.getLoanAmount() + "\nNext Payment: $" + calculateMonthlyPayment() + 
               "\nPayments Remaining: " + (TOTAL_PAYMENTS - paymentsMade);
    }
    
    public void makePayment() {
        double payment = calculateMonthlyPayment();
        withdraw(payment, true);  
        this.loanAmount -= payment;
        paymentsMade++;
        loanPeriod--;
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

	public double getLoanAmount() {
		return this.loanAmount;
	}
	
}
