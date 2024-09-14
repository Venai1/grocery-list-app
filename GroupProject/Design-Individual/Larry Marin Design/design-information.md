1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).  
Created the groceryList class that contains addItems, deleteItem, and quantityItems and the user class which has editList that gives the user permission to addItems, deleteItem, and setQuantityItems.

2. The application must contain a database (DB) of items and corresponding item types.  
The database is represented by each of the entities in the diagram with the exception of the user interface. Each one of the entities will be a table in the database.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat).  
Added displayItemCategories. This function will show all categories in the item category table. Added displayItemInCategory. This will show all the items with the matching category ID.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.  
Added searchForItem which allows the user to search for a specific item without looking through its category. Will allow the user to add a custom item to the DB if the item they are looking for is not found.

5. Lists must be saved automatically and immediately after they are modified.  
Added saveList which will be invoked by other functions after the user makes changes to the list.

6. Users must be able to check off items in a list (without deleting them).  
Added isCheckedOff to groceryListItem that is of type boolean 0 for not checked, 1 for checked and it is part of every item on the list

7. Users must also be able to clear all the check-off marks in a list at once  
Added clearAllChecks to groceryList.

8. Check-off marks for a list are persistent and must also be saved immediately.  
As mentioned in number 6 is checkedOff is a field in the database so in order to be persistent. As mentioned in number 5 saveList will be invoked by other functions after a user makes any changes to the grocery list.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).  
Created itemCategory and categoryID under item. When loading groceryListItems into the UI, sorting by these properties will allow the items to be grouped.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.  
Gave the user class createNewList, editListProperties, deleteList and selectList to satisfy these requirements. 

11. The User Interface (UI) must be intuitive and responsive.  
Not considered since it's not needed in the design of the UML diagram.
