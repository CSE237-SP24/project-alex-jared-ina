package bankapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountMenu {
	private User currentUser;
	private Scanner scanner;

	public AccountMenu(User currentUser, Scanner scanner) {
		this.currentUser = currentUser;
		this.scanner = scanner;
	}

	public void displayCurrentAccounts() {
		if (currentUser.getAccounts().isEmpty()) {
			System.out.println("No current accounts.");
			return;
		}
		System.out.println("Current Accounts:");
		currentUser.getAccounts().forEach(
				(accountName, account) -> System.out.println(accountName + " - Balance: $" + account.getBalance()));
		selectCurrentAccount();
	}

	public void createNewAccount() {
		System.out.print("Enter account name: ");
		String name = scanner.nextLine();
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
		String accountName = scanner.nextLine();
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
				return;
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

	    while (true) {
	        System.out.print("Enter your choice: ");
	        String input = scanner.nextLine(); // Capture the whole line of input as a string
	        try {
	            return Integer.parseInt(input); // Attempt to parse the input as an integer
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a number.");
	        }
	    }
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

	private BankAccount selectAccountForTransfer(Map<Integer, String> accountIndexes) {
		if (accountIndexes.isEmpty()) {
			System.out.println("No other accounts available for transfer.");
			return null;
		}
		System.out.println("Please enter the number of the account you wish to transfer to:");
		int selection;
		try {
			selection = Integer.parseInt(scanner.nextLine());
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

	public double getValidDepositInput() {
		double amount = scanner.nextDouble();
		while (amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = scanner.nextDouble();
		}
		scanner.nextLine();
		return amount;
	}

	public double getValidWithdrawInput(BankAccount account) {
		double withdrawAmount = scanner.nextDouble();
		boolean validInput = false;
		while (validInput == false) {
			if (withdrawAmount > 0 && account.getBalance() >= withdrawAmount) {
				validInput = true;
			} else {
				System.out.println(
						"Input Invalid, please try again and ensure the amount is greater than 0 and less than your current balance.");
			}
		}
		return withdrawAmount;
	}

	public double getValidTransferInput() {
		double amount = scanner.nextDouble();
		while (amount <= 0) {
			System.out.println("Invalid amount! Please enter a positive number for the transfer amount.");
			amount = scanner.nextDouble();
		}
		scanner.nextLine();
		return amount;
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
		scanner.nextLine();
		if (account.withdraw(amount)) {
			System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
		} else {
			System.out.println("Insufficient funds for this withdrawal.");
		}
	}
	
	private void handleLoanPayment(BankAccount account) {
		if(account.getLoanAmount() == 0) {
			System.out.println("No loan to make a payment on");
			return;
		}
		else {
			System.out.println("Your loan payment is $" + account.calculateMonthlyPayment());
	        System.out.println("Are you sure you want to make this payment? (yes/no): ");
	        String confirmation = scanner.nextLine().trim().toLowerCase();
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
	
	private void handleTakeLoan(BankAccount account) {
	    System.out.print("Enter loan amount: ");
	    double amount = scanner.nextDouble();
	    System.out.print("Enter loan period (12, 36, or 60 months): ");
	    int period = scanner.nextInt();
	    if (period == 12 || period == 36 || period == 60) {
	        account.takeLoan(amount, period);
	        System.out.println("Loan taken successfully!");
	    } else {
	        System.out.println("Invalid loan period. Only 12, 36, or 60 months are allowed.");
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
		String confirmation = scanner.nextLine().trim().toLowerCase();
		if ("yes".equals(confirmation)) {
			currentUser.getAccounts().remove(account.getAccountName());
			System.out.println("Account successfully deleted.");
		}

	}

	private void handleMergeAccount(BankAccount fromAccount) {
		if (currentUser.getAccounts().size() > 1) {
			System.out.println("Enter the account name you would like to merge with:");
			String accountName = scanner.nextLine();
			if (currentUser.getAccounts().containsKey(accountName) == false) {
				System.out.println("Cannot merge with nonexistent account.");
				return;
			} else {
				BankAccount toAccount = currentUser.getAccounts().get(accountName);
				try {
					currentUser.merge(fromAccount, toAccount);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}

		} else {
			System.out.println("No other accounts available for transfer.");
		}
	}

}
