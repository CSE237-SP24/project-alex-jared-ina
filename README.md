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
- Deposit implementation in the switch case method

### 5) customer should be able to withdraw money from a selected account
   
#### This story includes:
- withdraw implementation in the switch ase method
   
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

# Iteration 2

## What user stories were completed this iteration?
### The bulk of our work this iteration was focused around the implementation of User logins
#### This included the following stories:
- System should be able to store many users
- Users should have a username and password
- Users should access all their accounts after logging in

All of this work was done in a singular branch due to the intertwined nature of these individual stories
This work included a major refactoring of our entire code and the creation of a new User class
Instead of all accounts being stored in one storage map structure they are now stored in an individual map for each user
This change in storage method lead to changes (ranging from one line to nearly remaking the method) throughout our menu class

### We also tackled changes from our Iteraiton 1 feedback mainly focused around refactoring and testing
These changes included new tests due to the new implementation of our user storage and refactoring our main switch case method that deals with account interation

### We also added balance viewing as an option of account interation
Self explanatory 

## What user stories do you intend to complete next iteration?
We currently have all of our user stories completed and will work to brainstorm new functionality for the next iteration. 

## Is there anything that you implemented but doesn't currently work?
Everything implemented currently is functional however we need to finish polishing up the tests.
