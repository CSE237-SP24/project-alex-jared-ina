package bankapp;

import java.util.Map;
import java.util.Scanner;

public class UserMenu extends Menu{
	private User currentUser;
	//private Menu menu;
	private Map<String, User> users;
	private Scanner scanner;

	public UserMenu(User currentUser, Map<String, User> users, Scanner scanner) {
		this.currentUser = currentUser;
		this.users = users;
		this.scanner = scanner;
		//this.menu = menu;
	}

	public boolean displayUserMenu() {
		AccountMenu accountMenu = new AccountMenu(currentUser, scanner);

		while (currentUser != null) {
			System.out.println("Menu:");
			System.out.println("Enter 1 to view current accounts");
			System.out.println("Enter 2 to create new account");
			System.out.println("Enter 3 to delete current user");
			System.out.println("Enter 4 to logout");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				accountMenu.displayCurrentAccounts();
				break;
			case 2:
				accountMenu.createNewAccount();
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
		return currentUser == null;
	}

	private void deleteUser(User user) {
		System.out.print("Are you sure you want to delete this user? This action cannot be undone! (yes/no): ");
		String confirmation = scanner.nextLine().trim().toLowerCase();
		if ("yes".equals(confirmation)) {
			user.getAccounts().clear();
			users.remove(user.getUsername());
			System.out.println("User successfully deleted. Returning to login screen");
			currentUser = null;
			//menu.displayMainMenu();
		} else {
			System.out.println("User deletion cancelled.");
		}
	}
}
