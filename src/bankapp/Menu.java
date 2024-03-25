package bankapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private Map<String, BankAccount> accountStorage = new HashMap<>();

	// not tested
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
//		double amount = mainMenu.getValidMainMenuInput();
//		mainMenu.processingUserSelection(amount);
	}

	// Constructor
	public Menu() {
		this.in = new Scanner(System.in);
	}
	
	public int getNumAccounts() {
		int size = this.accountStorage.size();
		return size;
	}

	public BankAccount addAccountToStorage(BankAccount account) {
		if (accountStorage.containsKey(account.getAccountName())) {
			System.out.println("Account name already exists.");
			System.out.println("Enter valid account name:");
			String newName = in.nextLine();
			BankAccount renamedAccount = new BankAccount();
			renamedAccount.setAccountName(newName);
			addAccountToStorage(renamedAccount);
		}
		accountStorage.put(account.getAccountName(), account);
		System.out.print("Account successfully created!");
		// displayAccountOptions();//Will handle deposit/withdraw/transfer/delete
		return account;
	}

	// Code that just displays stuff - no tests needed
	public void displayingOptions() {
		System.out.println("Menu:");
		System.out.println("Enter 1 to view current accounts");
		System.out.println("Enter 2 to create new account");
		getValidMainMenuInput();
	}

	public void displayCurrentAccounts() {
		if (accountStorage.size() == 0) {
			System.out.println("No current accounts. Returning to main menu");
			displayingOptions();
		} else {
			for (String key : accountStorage.keySet()) {
				System.out.println(key);
			}
			// displayAccountOptions(); //Will handle deposit/withdraw/transfer/delete
		}
	}

	public void displayAccountOptions() {
//implement later
	}

	public int getValidMainMenuInput() {
		int input = in.nextInt();
		in.nextLine(); 
		if (input == 1) {
			displayCurrentAccounts(); //section input from processing of input
		} else if (input == 2) {
			System.out.println("Enter name");
			String name = in.nextLine();
			BankAccount newAccount = new BankAccount();
			newAccount.setAccountName(name);
			addAccountToStorage(newAccount);
		} else {
			System.out.println("Invalid value!");
			displayingOptions();
		}

		return input;
	}

	// Does work - needs tests
	public void deposit(double amount) {
		account.deposit(amount);
		System.out.println("Your balance is now: " + account.getBalance());
	}
	
	public double getValidDepositInput() {
		double amount = in.nextDouble();
		while (amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		return amount;
	}

	public BankAccount getAccount() {
		return account;
	}
}
