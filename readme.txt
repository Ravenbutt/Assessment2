CSC1029 Assessment 2 ReadMe

This file covers finer details such as how to access the hidden menu, reasoning behind choices etc.

GENERAL POINTS

- Regarding code format
I have tried to maintain Java formatting conventions for code e.g. one line before comments,
max of 100 chars for columns etc.

- Regarding user input:
Places where the user should enter input will be marked by '>'

- Regarding the insertCoins() method: 
I decided that the user would insert coins like so: 1 for £1, 2 for £2, rather than 100 and 200 for £1 and £2 respectively. 
My reasoning behind this is because part of the task was to think about how a real vending machine would work, 
and a user would not insert 100 or 200 pennies into a vending machine.

- Regarding itemId as an identifier:
I chose to distinguish items by their ItemID as it provides a definite unique identifier for each stock item
and I think it makes most sense to purchase items based on their ID. The item ID is displayed in listItems(),
and when selecting an item, the user selects the item by it's ID rather than an arbitrary number determined by the system.


SOME POINTS ON PART 2 FUNCTIONALITY
- Regarding accessing the hidden/engineer menu:
The engineer/hidden menu can be accessed by inputting 5 on the main system menu, and typing in the password of 'password'.

- Regarding saving the machine's state:
The state csv file should be saved in the top level directory of the project.