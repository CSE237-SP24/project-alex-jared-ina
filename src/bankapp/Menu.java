package bankapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
	private Scanner scanner = new Scanner(System.in);
	private Map<String, User> users = new HashMap<>();
	private User currentUser;
	private int incorrectPassword;

	public void displayMainMenu() {
		while (true) {
				System.out.println("Welcome to the Alex Jared Ina Bank App");
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. Exit");
				System.out.print("Choose an option: ");
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
			

			UserMenu userMenu = new UserMenu(currentUser, users, scanner);
			userMenu.displayUserMenu();
			
		}
	}
	
	private void loginUser() {
	    System.out.println("Username:");
	    String username = scanner.nextLine();
	    User user = users.get(username);
	    System.out.println("Password:");
	    String password = scanner.nextLine();
	    if (user != null && user.authenticate(password)) {
	        currentUser = user;
	        incorrectPassword = 0;  // Reset the counter after successful login
	        System.out.println("Login successful.");
	    } else {
	        incorrectPassword++;
	        if (incorrectPassword < 3) {
	            System.out.println("Login failed. Please try again.");
	        } else {
	            getValidPasswordInput(user);
	        }
	    }
	}
	


	private void registerUser() {
		System.out.println("Choose a username:");
		String username = scanner.nextLine();
		System.out.println("Choose a password:");
		String password = scanner.nextLine();

		if (!users.containsKey(username)) {
			addUser(username,password);
			System.out.println("User registered successfully. Please login.");
		} else {
			System.out.println("Username already taken. Please try again.");
		}
	}
	
	public void addUser(String username, String password) {
		User newUser = new User(username, password);
		users.put(username, newUser);
	}
	
	private void getValidPasswordInput(User user) {
	    System.out.println("Too many incorrect attempts, would you like to change your password? Enter Yes or No:");
	    String confirmation = scanner.nextLine().trim().toLowerCase();
	    if ("yes".equals(confirmation)) {
	        handleChangePassword(user);
	    } else {
	        incorrectPassword = 0; 
	        System.out.println("Ok, returning to the main menu.");
	    }
	}

	private void handleChangePassword(User user) {
	    if (!users.containsKey(user.getUsername())) {
	    	System.out.println("User not found. Please try again or register.");
	    	loginUser();
	    }
	    System.out.println("What would you like your new password to be?");
	    String newPassword = scanner.nextLine();
	    if (!newPassword.equals(user.getPassword())) {
	        user.setPassword(newPassword);
	        System.out.println("Password changed successfully!");
	        incorrectPassword = 0; 
	    } else {
	        System.out.println("New password cannot be the same 1as the old password. Please try again.");
	        handleChangePassword(user);
	    }
	}
    
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.displayMainMenu();
	}
	
}

