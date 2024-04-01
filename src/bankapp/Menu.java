package bankapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private Map<String, BankAccount> accountStorage = new HashMap<>();

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
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
		System.out.print("Account successfully created! Returning to main menu");
		displayingOptions();
		return account;
	}

	public void displayingOptions() {
		System.out.println("Menu:");
		System.out.println("Enter 1 to view current accounts");
		System.out.println("Enter 2 to create new account");
		getValidMainMenuInput();
	}

	public void displayCurrentAccounts() {
		if (accountStorage.size() == 0) {
			System.out.println("No current accounts. Returning to main menu.");
			displayingOptions();
		} else {
			System.out.println("Current Accounts:");
			for (String key : accountStorage.keySet()) {
				System.out.println(key);
			}
		}
		selectCurrentAccount();
	}

	public void selectCurrentAccount() {
		System.out.println(
				"Enter the account name you would like to interact with, or type 'back' to return to the main menu:");
		String accountOfInterestName = in.nextLine();
		if ("back".equalsIgnoreCase(accountOfInterestName)) {
			displayingOptions();
		}
		BankAccount accountOfInterest = accountStorage.get(accountOfInterestName);
		if (accountOfInterest != null) {
			displayAccountOptions(accountOfInterest);
		} else {
			System.out.println("Account not found. Please try again.");
			displayCurrentAccounts();
		}
	}

	public void displayAccountOptions(BankAccount account) {
		this.account = account;
		int choice = printAccountOptions();
		switch (choice) {
		case 1:
			handleDeposit(this.account);
			break;
		case 2:
			handleWithdraw(this.account);
			break;
		case 3:
			handleTransfer(this.account);
			break;
		case 4:
			handleDeleteAccount(this.account);
			break;
		case 5:
			break;
		default:
			System.out.println("Invalid option. Please try again.");
			break;
		}
		displayingOptions();
	}
	
	public int printAccountOptions() {
		System.out.println("\nAccount Options for: " + account.getAccountName());
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Transfer");
		System.out.println("4. Delete Account");
		System.out.println("5. Return to Main Menu");
		int choice = in.nextInt();
		in.nextLine();
		return choice;
	}
	
	private BankAccount displayAccountsForTransfer(String currentAccountName) {
	    System.out.println("Available accounts for transfer:");
	    Map<Integer, String> accountIndexes = new HashMap<>();
	    int accountIndex = 1;
	    for (String accountName : accountStorage.keySet()) {
	        if (!accountName.equals(currentAccountName)) {
	            System.out.println(accountIndex + ". " + accountName);
	            accountIndexes.put(accountIndex, accountName);
	            accountIndex++;
	        }
	    }
	    return selectAccountForTransfer(accountIndexes);
	}
	
	private BankAccount selectAccountForTransfer(Map<Integer, String> accountIndexes) {
	    if (accountIndexes.isEmpty()) {
	        System.out.println("No other accounts available for transfer.");
	        return null;
	    }
	    System.out.println("Please enter the number of the account you wish to transfer to:");
	    int selection = in.nextInt();
	    in.nextLine();
	    if (accountIndexes.containsKey(selection)) {
	        return accountStorage.get(accountIndexes.get(selection));
	    } else {
	        System.out.println("Invalid selection.");
	        return null;
	    }
	}
	
	public int getValidMainMenuInput() {
		int input = in.nextInt();
		in.nextLine(); 
		if (input == 1) {
			displayCurrentAccounts(); 
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
	
	public void deleteAccount(BankAccount account) {
	    accountStorage.remove(account.getAccountName());
	    System.out.println("Account successfully deleted. Returning to main menu.");
	    displayingOptions();
	}
	
	public void deposit(double amount) {
		account.deposit(amount);
		System.out.println("Your balance is now: " + account.getBalance());
	}
	
	public BankAccount selectAccount(String accountName) {
	    BankAccount selectedAccount = accountStorage.get(accountName);
	    while (selectedAccount == null) {
	        System.out.println("Account '" + accountName + "' does not exist. Please enter a valid account name:");
	        accountName = in.nextLine();
	        selectedAccount = accountStorage.get(accountName);
	    }

	    return selectedAccount;
	}
	public double getValidDepositInput() {
		double amount = in.nextDouble();
		while (amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		in.nextLine();
		return amount;
	}
	
	public double getValidWithdrawInput() {
		double withdrawAmount = in.nextDouble();
		boolean validInput = false;
		while (validInput == false) {
	    if (withdrawAmount > 0 && account.getBalance() >= withdrawAmount) {
	    	validInput = true;
	    } 
	    else {
	        System.out.println("Input Invalid, please try again and ensure the amount is greater than 0 and less than your current balance.");
	    }
	}
		return withdrawAmount;
	}
	
	public void handleDeposit(BankAccount account) {
    	System.out.println("Enter the ammount you wish to deposit:");
        double depositAmount  = getValidDepositInput();
        account.deposit(depositAmount);
        System.out.println("Deposit successful. Your current balance is $" +  account.getBalance());
	}
	
	public void handleWithdraw(BankAccount account) {
	    System.out.println("Enter the amount you wish to withdraw:");
	    double validWithdrawAmount = getValidWithdrawInput();
        account.withdraw(validWithdrawAmount);
        System.out.println("Withdrawal successful. Your current balance is $" + account.getBalance());
	}
	
	private void handleTransfer(BankAccount fromAccount) {
	    if (getNumAccounts() > 1) {
	    	BankAccount transferToAccount = displayAccountsForTransfer(fromAccount.getAccountName());
	        if (transferToAccount != null) {
	            System.out.println("Enter amount to transfer:");
	            double transferAmount = getValidTransferInput();
	            transfer(fromAccount, transferToAccount, transferAmount);
	        } else {
	            System.out.println("Transfer cancelled or invalid selection.");
	        }
	    } else {
	        System.out.println("No other accounts to transfer to.");
	    }
	}
	
	private void handleDeleteAccount(BankAccount account) {
		System.out.println("Are you sure you want to delete this account? (yes/no)");
		String confirmation = in.nextLine().trim().toLowerCase();
		if ("yes".equals(confirmation)) {
			deleteAccount(account);
		} else {
			System.out.println("Account deletion cancelled.");
		}
	}
	
	public double getValidTransferInput() {
	    double amount = in.nextDouble();
	    while (amount <= 0) {
	        System.out.println("Invalid amount! Please enter a positive number for the transfer amount.");
	        amount = in.nextDouble();
	    }
	    in.nextLine(); 
	    return amount;
	}
	
	public Menu() {
		this.in = new Scanner(System.in);
	}
	
	public int getNumAccounts() {
		int size = this.accountStorage.size();
		return size;
	}
	
	public BankAccount getAccount() {
		return account;
	}
}
