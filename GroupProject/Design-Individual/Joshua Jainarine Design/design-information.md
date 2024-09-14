# Design Information

---

## Classes
User
* represents the person using the GroceryListManager.

List
* represents the user created list that will be manipulated.

Database
* main hub for items that user selects to add to list, can be modified.

Item
* objects that user will add to the list.


### User
- #### Attributes
    * hasMultipleLists will be a boolean value that will state whether the user has created multiple lists.(10)
    ###
    * createdLists will be an integer value that will state the amount of lists the user has created.


- #### Operations
    * createList will allow the user to create a list. (1)
    ###
    * renameList will allow the user to rename the list, since there can be multiple lists in the system, the method needs to have a reference to the old list that is changing.
    ###
    * deleteList will delete a given list from the system.(1)
    ###
    * addToList will add an item from the database into the list, since the items are presented in a hierarchical order, the user has to specify the item and the itemtype to add.
    ###
    * removeFromList removes a specified item and type from a given list.
    ###
    * changeAmount changes the amount of an item in a list.(1)
    ###
    * checkItem check's an item on the list without deleting it, marking it in a way.(6)
    ###
    * clearChecks will clear all the checks from a list.(7)

### List
- #### Attributes
    * numberOfItems is a count of how many items are in a list.
    ###
    * checkedItems is a count of the items that the user has checked off the list but not deleted.

- #### Operations
    * addItem will add an item to the list.
    ###
    * removeItem will remove an item from the list.
    ###
    * checkItem will check an item on the list.

 ### Database
- #### Attributes
    * amountOfItems will keep track of how many items are in the database, general knowledge for a database containing items to keep track of.
    ### 
    * itemExists will be used when the user wants to add an item but it doesn't exist, this boolean will be set to false.

- #### Operations
    * userAddedItem will be called when the itemExists attribute is false in which the database will add the user specified item and type to the database.(4)

### Item
- #### Attributes
    * itemType, all items must have a type, for example if the item is eggs, there are whole eggs, or farm raised. this is what is being added to the list.(3&4)
    ###
    * itemBrand, there are different brands for each item, and if the user wants to buy multiple of one item and one type of item, for example chocolate ice cream, there are multiple brands that the user can choose to buy.(3)
    ###
    * itemExists, if the user is looking in the database and cannot find the specific type or brand of an item, the item does not exist in the database and must be added.(4)

- #### Operations
    * there aren't any operations needed for items themselves since they are being added to the lists by the user, they aren't being manipulated in a traditional sense.

 ### Design
 1) A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list.

 - The user, and list class share addItem, removeItem and changeAmount methods to implement this requirement.

 2) The application must contain a database of items and corresponding item types.
 - The database class is implemented with items and corresponding item types.

 3) Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type and the second level is the name of the actual item. After adding an item, users must be able to specify a quantity for that item.

 - The database will be represented as a hierarchical list. The user will use the addItem method to search the database for said item and the type, then specify the amount with the method itemAmount.

 4) Users must also be to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of the, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

 - implemented through the addItem method, the user will look through the database and will check for a specific item, database will have proximity search in the design that will check for a match, if item doesn't exist, database will add the item.

 5) Lists must be saved automatically and immediately after they are modified. 

 - doesn't affect the design directly so it is inconsidered.

 6) Users must be able to check off items in a list(without deleting them).

 - check will be implemented by the checkItem method where the user can call it to check any item from a given list.

 7) Users must also be able to clear all the check-off marks in a list at once.
 
 - clearChecks will implement the method to clear all the checks at once in a list.

 8) Check-off marks for a list are persistent and must also be saved immediately 

 - doesn't affect the design directly and will automatically be implemented in list class.

 9) The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of product 
 
 - this requirement will be implemented through the UI, when the user looks to add an item through the database, they will be viewed as types first.

 10) The application must support multiple lists at a time. Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

 - The user defined methods createList, renameList, and deleteList implements this requirement.

 11) The User Interface(UI) must be intuitive and responsive.
 
 - doesn't affect the design directly.

### Relationships
- addItem, createList, renameList, removeFromList, changeAmount, checkItem, clearChecks, and removeItem are all methods that will be implemented from the list class to the user class, giving the user manipulation of lists

- The database class is used by the user to add items but can also be manipulated by the user, therefore being both dependency and association.

- The item class is associated with the user class, the user simply adds them to the list or searches for them.

- The list class is dependant on the user to create and manipulate it.

- The list class is also made up of items but there can be a list of no items so aggregation is the relationship.

- The list and item classes are part of the database class but they can be separated, there can be an item that isn't in the database and there can be an item that doesn't have a type and isnt presented as a list in the database, therefore aggregation is used.

