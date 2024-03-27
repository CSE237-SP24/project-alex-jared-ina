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
	    } 
	    else {
	        System.out.println("Current Accounts:");
	        for (String key : accountStorage.keySet()) {
	            System.out.println(key);
	        }
	        System.out.println("Enter the account name you would like to interact with, or type 'back' to return to the main menu:");
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
	}

	public void displayAccountOptions(BankAccount account) {
		this.account = account;
		boolean exit = false;
		while(!exit) {
	        System.out.println("\nAccount Options for: " + account.getAccountName());
	        System.out.println("1. Deposit");
	        System.out.println("2. Withdraw");
	        System.out.println("3. Transfer");
	        System.out.println("4. Delete Account");
	        System.out.println("5. Return to Main Menu");
	        int choice = in.nextInt();
	        in.nextLine(); 
	        switch (choice) {
	        case 1:
	        	System.out.println("Enter the ammount you wish to deposit:");
                double depositAmount  = in.nextDouble();
                if (depositAmount > 0){
                	account.deposit(depositAmount);
                	System.out.println("Deposit successful. Your current balance is" +  account.getBalance());
                }
                else {
                	System.out.println("Imput Invalid, please try again and enter a positive number.");
                }
            	break;
                
	        case 2:
	        	System.out.println("Enter the amount you wish to withdraw:");
                double withdrawAmount  = in.nextDouble();
                if (withdrawAmount > 0 && account.getBalance() >= withdrawAmount){
                	account.withdraw(withdrawAmount);
                	System.out.println("Withdrawal successful. Your current balance is" +  account.getBalance());
                }
                else {
                	System.out.println("Imput Invalid, please try again and enter a number greater than your balance.");
                }
            	break;
           
			case 3:
			    if (getNumAccounts() > 1) {
			        BankAccount transferToAccount = selectAccountForTransfer(account.getAccountName());
			        if (transferToAccount != null) { 
			            System.out.println("Enter amount to transfer:");
			            double transferAmount = getValidTransferInput();
			            transfer(account, transferToAccount, transferAmount);
			        } else {
			            System.out.println("Transfer cancelled or invalid selection.");
			        }
			    } else {
			        System.out.println("No other accounts to transfer to.");
			    }
			    break;
				
	        case 4:
	            System.out.println("Are you sure you want to delete this account? (yes/no)");
	            String confirmation = in.nextLine().trim().toLowerCase();
	            if ("yes".equals(confirmation)) {
	                deleteAccount(this.account); 
	                exit = true; 
	            } else {
	                System.out.println("Account deletion cancelled.");
	            }
	            break;
	        	
	        case 5:
	        	exit = true;
	        	break;
	        	
	        default:
                System.out.println("Invalid option. Please try again.");
                break;
		}
	        displayingOptions();
		}
	}

	public BankAccount selectAccountForTransfer(String currentAccountName) {
	    System.out.println("Available accounts for transfer:");
	    int accountIndex = 1;
	    Map<Integer, String> accountIndexes = new HashMap<>();
	    for (String accountName : accountStorage.keySet()) {
	        if (!accountName.equals(currentAccountName)) {
	            System.out.println(accountIndex + ". " + accountName);
	            accountIndexes.put(accountIndex, accountName);
	            accountIndex++;
	        }
	    }
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
