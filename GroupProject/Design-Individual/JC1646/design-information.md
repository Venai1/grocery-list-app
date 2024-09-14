. 1. A grocery list consists of items the users want to buy at a grocery store. The application
must allow users to add items to a list, delete items from a list, and change the quantity
of items in the list (e.g., change from one to two pounds of apples).


Added methods:
+ addItem(item: Item): void
* Takes in the item object to add and adds to the list


+ deleteItem(item: Item): void
* Takes in item object to delete and deletes it from the list


+ changeQuantity(item: Item, quantity: int): void
* Takes in item object and quantity and changes the quantity of the given item on the list


2. The application must contain a database (DB) of items and corresponding item types.


Made Item class and ItemType class. Database not considered part of the UML design 


3. Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.


Using the item name string in the item class and type name string in the ItemType class we allow it to be placed accordingly. itemQuantity in the Item class stores desired quantity. 


4. Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB.


itemName stores the item’s name which can be searched from the database. The exact search method is considered part of the database and not implemented in the UML design.


5. Lists must be saved automatically and immediately after they are modified.


saveList method in the Grocery List class would be invoked after modification.


6. Users must be able to check off items in a list (without deleting them).


checkOffItem method in the Grocery List class takes in the item object and flips the isCheckedOff boolean attribute in the Item class.


7. Users must also be able to clear all the check-off marks in a list at once.


clearCheckOffMarks method in the Grocery List class clears all check-off marks for every item in the Grocery List class.


8. Check-off marks for a list are persistent and must also be saved immediately.


saveList method in the Grocery List class would be invoked after modification.


9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).


groupList method in Grocery List class groups items by type.


10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists.


creatingList, renameList, and deleteList are methods in the Main class that provides the user with the ability to create, name/rename, and delete lists. Selection is not considered as it does not affect the design directly.


11. The User Interface (UI) must be intuitive and responsive.


Not considered because it does not affect the design directly.