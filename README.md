# base-project

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
