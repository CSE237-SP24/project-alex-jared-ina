package bankapp;

import java.util.Map;
import java.util.Scanner;

public class UserMenu{
	private User currentUser;
	private Map<String, User> users;
	private Scanner scanner;

	public UserMenu(User currentUser, Map<String, User> users, Scanner scanner) {
		this.currentUser = currentUser;
		this.users = users;
		this.scanner = scanner;
	}

	public void displayUserMenu() {
		AccountMenu accountMenu = new AccountMenu(currentUser, scanner);

		while (currentUser != null) {
			System.out.println("Menu:");
			System.out.println("Enter 1 to view current accounts");
			System.out.println("Enter 2 to create new account");
			System.out.println("Enter 3 to delete current user");
			System.out.println("Enter 4 to logout");
	        String input = scanner.nextLine();  // Read the entire line of input
	        int choice = 0;
	        try {
	            choice = Integer.parseInt(input);  // Attempt to parse the input as an integer
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a number.");
	            continue;  // Skip the rest of the loop iteration and prompt again
	        }
			switch (choice) {
			case 1:
				accountMenu.displayCurrentAccounts();
				break;
			case 2:
				accountMenu.createNewAccount();
				break;
			case 3:
				handleDeleteUser(currentUser);
				return;
			case 4:
				currentUser = null;
				System.out.println("Logged out.");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	


	private void handleDeleteUser(User user) {
		System.out.print("Are you sure you want to delete this user? This action cannot be undone! (yes/no): ");
		String confirmation = scanner.nextLine().trim().toLowerCase();
		if ("yes".equals(confirmation)) {
			deleteUser(user);
			System.out.println("User successfully deleted. Returning to login screen");
			currentUser = null;
		} else {
			System.out.println("User deletion cancelled.");
		}
	}
	
	public void deleteUser(User user) {
		user.getAccounts().clear();
		users.remove(user.getUsername());
	}

	
}
