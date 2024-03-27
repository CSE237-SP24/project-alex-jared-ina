# base-project
# Iteration 1
  
  ## What user stories were completed this iteration?
  
This iteration we begain with tackling the task of creating accounts.
### Story 1: customer should be able to create and name multiple accounts

#### This story included:
- Modifications to BankAccount.java with an accountName instance var
- createBankAccount(String name) to create an account object in the menu
- displayingOptions() to prompt the user with options
- getValidMainMenuInput() to get valid response from main menu
- addition of a Map<String, BankAccount> accountStorage instance var in Menu.java to store accounts
- addAccountToStorage(BankAccount account) basic storage implementation

Next, we needed to begin implementing user interation with bank accounts.
We decided to use a switch case method with the following options and start with transfer as it was the most difficult:
- Withdraw
- Deposit
- Delete account
- Transfer

### Story 2:customer should be able to transfer money between accounts

#### This story includes:
- addition of withdraw(double amount) to the BankAccount.java file
- futher implementation of addAccountToStorage(BankAccount account)
- displayAccountOptions(BankAccount account) THE INTERACTION SWITCH CASE METHOD
- Contains transfer implementation
- transfer(BankAccount from, BankAccount to, double amount) to transfer funds
- selectAccount(String accountName) for user input account selection
- getValidTransferInput() can only transfer positive values
- Related tests in TransferTests.java

The next 3 stories are grouped together due to their simpler implementation.
Deposit, withdraw, and delete are the remaining cases in our interaction swith case method.

### Stories 3-5:

### 3) customer should be able to deposit money into a selected account

#### This story includes:
- 

### 5) customer should be able to withdraw money from a selected account
   
#### This story includes:
- 
   
### 7) customer should be able to delete an account of their choice
   
#### This story includes:
- edits to the Menu class and switch function imbedded within the displayAccountOptions() method
- creation of the deleteAccount method which deletes a givin account out of the account storage hashmap with the use of remove() command
- (no tests created yet at present moment)

  ## What user stories do you intend to complete next iteration?
  
We intend to finish the following stories and much more but have yet to brainstorm past these points as we worked on these stories:
- Customer should be able to view the balance of any account
- The user should be able to properly interact with every menu screen option prompted (debug Menu.java)

The big story to complete is the debug main Menu as our backend tests work but the actual user interaction is still buggy

## Is there anything that you implemented but doesn't currently work?

Our Menu interaction with users is still buggy in many cases and will be fixed

## What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)

<<<<<<< HEAD
Add description of what happened each iteration

  Iteration 1:
  What user stories were completed this iteration?
The bulk of our work this iteration was determining the flow of the menu and how the users will interact with accounts
The first story we completed allowed users to create and name multiple bank accounts
This led to the creation of multiple methods including displayOpetions() and getValidMainMenuInput() which is the first decision a user makes when loading the program
This method accepts a user input of 1 or 2 based on the print statements on displayOptions
If the user inputs a 1 they are directed to the displayCurrentAccounts() method made in this story
If the user inputs a 2 they are directed to the addAccountToStorage() method made in this story.

We then wanted to start the process of interacting with these accounts and began with the harder task of transfer
We determined that a switch case method would work the best and this method is displayAccountOptions()
This switch case has 4 options
- transfer
- deposit
- withdraw
- delete account
- return to main menu (completed within the transfer story due to simplicity.

We then completed the remaining stories regaurding to this switch case funtion dividing each case into a story
- deposit
- withdraw
- delete account
- transfer (which was what we did first as described above)

Our code is functional from a backend perspective as all of our written tests pass
We need to debug through the menu file to determine how to polish the frontend interaction. 


  

We then completed
  What user stories do you intend to complete next iteration?

Is there anything that you implemented but doesn't currently work?

What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
=======
>>>>>>> 536aaf8253c1233ee188335418c86ca0761b4ed0
