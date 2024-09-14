# Use Case Model

**Jin Hui Chen**

## 1 Use Case Diagram

### Actor: User

### Use Cases:

- Create grocery list
- Delete grocery list
- Remove item from grocery list
- Add item to grocery list
- View grocery list

## 2 Use Case Descriptions

### Create grocery list

- Requirements: Allow the user to create a new grocery list.
- Pre-conditions: The user must be logged into the GroceryListApp.
- Post-conditions: A new grocery list is created and displayed to the user.
- Scenarios:

1. The user decides to make a fresh grocery list.
2. 10 premade empty lists can be selected from.

### Delete grocery list

- Requirements: Allow the user to delete an existing grocery list.
- Pre-conditions: The user must have at least one grocery list created.
- Post-conditions: The selected grocery list is permanently deleted from the user's account.
- Scenarios:

1. From the lists that are provided, the user chooses a grocery list.
2. The user selects the option to remove the list they have chosen.
3. The chosen grocery list is permanently removed from the user's account by the system by replacing the deleted list with a blank list.

### Remove item from grocery list

- Requirements: Allow the user to remove items from an existing grocery list.
- Pre-conditions: The user must have a grocery list created with items.
- Post-conditions: The selected item is removed from the grocery list.
- Scenarios:

1. From the lists that are provided, the user chooses a grocery list.
2. The user looks at the items in the chosen list.
3. The option to remove an item is selected by the user.
4. The user chooses what to delete.
5. The chosen item is deleted by the system from the grocery list.

### Add item to grocery list

- Requirements: Allow the user to add items to an existing grocery list.
- Pre-conditions: The user must have a grocery list created.
- Post-conditions: The item is added to the selected grocery list.
- Scenarios:

1. From the lists that are provided, the user chooses a grocery list.
2. The option to add an item to the list of choices is picked by the user.
3. The user is prompted by the system to enter the item's name.
4. The user inputs the quantity and item name.
5. The product is added by the system to the chosen grocery list.

### View grocery list

- Requirements: Allow the user to view the contents of an existing grocery list.
- Pre-conditions: The user must have at least one grocery list created.
- Post-conditions: The user can see the items in the selected grocery list.
- Scenarios:

1. From the lists that are provided, the user chooses a grocery list.
2. The user's selected grocery list items are shown to them by the system.
