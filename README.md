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
- 

  ## What user stories do you intend to complete next iteration?
  
We intend to finish the following stories and much more but have yet to brainstorm past these points as we worked on these stories:
- Customer should be able to view the balance of any account
- The user should be able to properly interact with every menu screen option prompted (debug Menu.java)

The big story to complete is the debug main Menu as our backend tests work but the actual user interaction is still buggy

## Is there anything that you implemented but doesn't currently work?

Our Menu interaction with users is still buggy in many cases and will be fixed

## What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)

