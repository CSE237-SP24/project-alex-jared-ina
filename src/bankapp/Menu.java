package bankapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private Scanner in = new Scanner(System.in);
	private User currentUser;
	private Map<String, User> users = new HashMap<>();
	
	public Menu() {
		displayLoginMenu();
	}
	
	public static void main(String[] args) {
		new Menu();
	}

	private void displayLoginMenu() {
		while(true) {
        System.out.println("Welcome to the Alex Jared Ina Bank App");
		while (currentUser == null) {
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
            System.out.print("Choose an option: ");
			int choice = in.nextInt();
			in.nextLine();
			switch (choice) {
			case 1:
				loginUser();
				break;
			case 2:
				registerUser();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
		}
	}
	   
	private void loginUser() {
		System.out.println("Username:");
		String username = in.nextLine();
		System.out.println("Password:");
		String password = in.nextLine();

		User user = users.get(username);
		if (user != null && user.authenticate(password)) {
			currentUser = user;
			System.out.println("Login successful.");
			displayingOptions();
		} else {
			System.out.println("Login failed. Please try again.");
		}
	}

	private void registerUser() {
		System.out.println("Choose a username:");
		String username = in.nextLine();
		System.out.println("Choose a password:");
		String password = in.nextLine();

		if (!users.containsKey(username)) {
			User newUser = new User(username, password);
			users.put(username, newUser);
			System.out.println("User registered successfully. Please login.");
		} else {
			System.out.println("Username already taken. Please try again.");
		}
	}
	

	public void displayingOptions() {
		while (currentUser != null) {
        System.out.println("Menu:");
        System.out.println("Enter 1 to view current accounts");
        System.out.println("Enter 2 to create new account");
        System.out.println("Enter 3 to delete current user");
        System.out.println("Enter 4 to logout");
        int choice = in.nextInt();
        in.nextLine(); 
        switch (choice) {
            case 1:
                displayCurrentAccounts();
                break;
            case 2:
                createNewAccount();
                break;
            case 3:
            	deleteUser(currentUser);
            	break;
            case 4:
                currentUser = null;
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
		}
	}

	public void displayCurrentAccounts() {
        if (currentUser.getAccounts().isEmpty()) {
            System.out.println("No current accounts.");
            return;
        }
        System.out.println("Current Accounts:");
        currentUser.getAccounts().forEach((accountName, account) -> System.out.println(accountName + " - Balance: $" + account.getBalance()));
        selectCurrentAccount();
    }
	
    private void createNewAccount() {
        System.out.print("Enter account name: ");
        String name = in.nextLine();
        if (currentUser.getAccounts().containsKey(name)) {
            System.out.println("Account already exists with this name. Please choose a different name.");
        } else {
            BankAccount newAccount = new BankAccount();
            newAccount.setAccountName(name);
            currentUser.addAccount(newAccount);
            System.out.println("Account created successfully.");
        }
    }

    private void selectCurrentAccount() {
        System.out.print("Enter the account name you'd like to interact with, or type 'back' to return: ");
        String accountName = in.nextLine();
        if ("back".equalsIgnoreCase(accountName)) {
            return;
        }
        BankAccount account = currentUser.getAccount(accountName);
        if (account != null) {
            displayAccountOptions(account);
        } else {
            System.out.println("Account not found. Please try again.");
            selectCurrentAccount();
        }
    }

	public void displayAccountOptions(BankAccount account) {
	    while (true) {
	        int choice = printAccountOptions(account);

	        switch (choice) {
	            case 1:
	                handleDeposit(account);
	                break;
	            case 2:
	                handleWithdraw(account);
	                break;
	            case 3:
	                handleDeleteAccount(account);
	                return; // Exit to the main menu after deleting an account
	            case 4:
	            	handleMergeAccount(account);
	            	break;
	            case 5:
	                handleTransfer(account);
	                break;
	            case 6:
	                viewBalance(account);
	                break;
	            case 7:
	            	viewTransactionHistory(account);
	            	break;
	            case 8:
	            	handleTakeLoan(account);
	            	break;
	            case 9:
	            	handleLoanPayment(account);
	            	break;
	            case 10:
	                return; // Exit to the main menu
	            default:
	                System.out.println("Invalid option. Please try again.");
	        }
	    }
	}
	
	public int printAccountOptions(BankAccount account) {
		System.out.println("Account Options for: " + account.getAccountName());
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Delete Account");
		System.out.println("4. Merge Account");
		System.out.println("5. Transfer");
		System.out.println("6. Get Account Balance");
		System.out.println("7. View Transaction History");
		System.out.println("8. Take out a loan");
		System.out.println("9. Make a loan payment");
		System.out.println("10. Return to Main Menu");
		int choice = in.nextInt();
		in.nextLine();
		return choice;
	}
	
	private BankAccount displayAccountsForTransfer(String currentAccountName) {
	    System.out.println("Available accounts for transfer:");
	    Map<Integer, String> accountIndexes = new HashMap<>();
	    int accountIndex = 1;
	    for (String accountName : currentUser.getAccounts().keySet()) {
	        if (!accountName.equals(currentAccountName)) {
	            System.out.println(accountIndex + ". " + accountName);
	            accountIndexes.put(accountIndex, accountName);
	            accountIndex++;
	        }
	    }
	    return selectAccountForTransfer(accountIndexes);
	}
	
	 private void viewTransactionHistory(BankAccount account) {
	        if (account != null) {
	            System.out.println("Transaction history for " + account.getAccountName() + ":");
	            for (String transaction : account.getTransactions()) {
	                System.out.println(transaction);
	            }
	        } else {
	            System.out.println("Account not found.");
	        }
	    }
	 
	public void viewBalance(BankAccount account) {
	    System.out.println("Your Balance is: $" + account.getBalance());
	    if(account.getLoanAmount() > 0) {
	    	System.out.println(account.getLoanDetails());
	    }
	    return;
	}
	
	private void handleLoanPayment(BankAccount account) {
		if(account.getLoanAmount() == 0) {
			System.out.println("No loan to make a payment on");
			return;
		}
		else {
			System.out.println("Your loan payment is $" + account.calculateMonthlyPayment());
	        System.out.println("Are you sure you want to make this payment? (yes/no): ");
	        String confirmation = in.nextLine().trim().toLowerCase();
	        if ("yes".equals(confirmation) && account.getBalance() > account.calculateMonthlyPayment()) {
	        	account.makePayment();
	        	System.out.println("Payment made successfully!");
	        	System.out.println("Current loan details: " + account.getLoanDetails());
	        }
	        else {
	        	System.out.println("insufficient balance or payment cancelled");
	        }
	        return;
		}
	}

	private BankAccount selectAccountForTransfer(Map<Integer, String> accountIndexes) {
	    if (accountIndexes.isEmpty()) {
	        System.out.println("No other accounts available for transfer.");
	        return null;
	    }
	    System.out.println("Please enter the number of the account you wish to transfer to:");
	    int selection;
	    try {
	        selection = Integer.parseInt(in.nextLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid input. Please enter a number.");
	        return null;
	    }
	    if (accountIndexes.containsKey(selection)) {
	        return currentUser.getAccounts().get(accountIndexes.get(selection));
	    } else {
	        System.out.println("Invalid selection.");
	        return null;
	    }
	}
	
	private void handleTakeLoan(BankAccount account) {
	    System.out.print("Enter loan amount: ");
	    double amount = in.nextDouble();
	    System.out.print("Enter loan period (12, 36, or 60 months): ");
	    int period = in.nextInt();
	    if (period == 12 || period == 36 || period == 60) {
	        account.takeLoan(amount, period);
	        System.out.println("Loan taken successfully!");
	    } else {
	        System.out.println("Invalid loan period. Only 12, 36, or 60 months are allowed.");
	    }
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
	
	public double getValidWithdrawInput(BankAccount account) {
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
        System.out.println("Enter the amount you wish to deposit:");
        double amount = getValidDepositInput();
        account.deposit(amount);
        System.out.println("Deposit successful. Your current balance is $" + account.getBalance());
	}
	
	public void handleWithdraw(BankAccount account) {
        System.out.print("Enter the amount you wish to withdraw: $");
        double amount = getValidWithdrawInput(account);
        in.nextLine();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Insufficient funds for this withdrawal.");
        }
	}
	
	private void handleTransfer(BankAccount fromAccount) {
	    if (currentUser.getAccounts().size() > 1) {
	        System.out.println("Select the account to transfer to:");
	        BankAccount toAccount = displayAccountsForTransfer(fromAccount.getAccountName());
	        if (toAccount == null) {
	            System.out.println("Transfer cancelled or invalid account selected.");
	            return;
	        }
	        System.out.println("Enter amount to transfer:");
	        double transferAmount = getValidTransferInput();
	        try {
	        	currentUser.transfer(fromAccount, toAccount, transferAmount);
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	    } else {
	        System.out.println("No other accounts available for transfer.");
	    }
	}
	
	
	private void handleDeleteAccount(BankAccount account) {
        System.out.print("Are you sure you want to delete this account? (yes/no): ");
        String confirmation = in.nextLine().trim().toLowerCase();
        if ("yes".equals(confirmation)) {
            currentUser.getAccounts().remove(account.getAccountName());
            System.out.println("Account successfully deleted.");
        } else {
            System.out.println("Account deletion cancelled.");
        }
	}
	
	private void handleMergeAccount(BankAccount fromAccount) {
		if (currentUser.getAccounts().size() > 1) {
	        System.out.println("Enter the account name you would like to merge with:");
	        String accountName = in.nextLine();
	        if (currentUser.getAccounts().containsKey(accountName)==false){
	        	System.out.println("Cannot merge with nonexistent account.");
	        	return;
	        }
	        else {
	        	BankAccount toAccount = currentUser.getAccounts().get(accountName);
		        try {
		        	currentUser.merge(fromAccount,toAccount);
		        } catch (IllegalArgumentException e) {
		            System.out.println(e.getMessage());
		        }
	        }
	        
	    } else {
	        System.out.println("No other accounts available for transfer.");
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
	
	public void deleteUser(User user) {
		System.out.print("Are you sure you want to delete this user? This action cannot be undone! (yes/no): ");
        String confirmation = in.nextLine().trim().toLowerCase();
        if ("yes".equals(confirmation)) {
        	currentUser.getAccounts().clear();
            users.remove(user.getUsername());
            System.out.println("User successfully deleted. Returning to login screen");
            currentUser = null;
        } else {
            System.out.println("User deletion cancelled.");
        }
		
		
		
	}
	
}
