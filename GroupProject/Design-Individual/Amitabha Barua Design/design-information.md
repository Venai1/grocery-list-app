**Classes:**

1\. GroceryList

\- Attributes:

\- items: List&lt;Item&gt;

\- Operations:

\- addItem(item: Item)

\- deleteItem(item: Item)

\- changeQuantity(item: Item, newQuantity: Float)

\- checkOffItem(item: Item)

\- clearCheckOffMarks()

2\. Item

\- Attributes:

\- name: String

\- quantity: Float

\- type: ItemType

\- checked: Boolean

3\. ItemType

\- Attributes:

\- name: String

4\. Database

\- Attributes:

\- items: List&lt;Item&gt;

\- itemTypes: List&lt;ItemType&gt;

\- Operations:

\- searchItemByName(name: String): List&lt;Item&gt;

\- addItem(item: Item)

\- addItemType(itemType: ItemType)

5\. ListManager

\- Attributes:

\- Nonspecific to the requirements

\- Operations:

\- createList(name: String)

\- renameList(list: GroceryList, newName: String)

\- selectList(list: GroceryList)

\- deleteList(list: GroceryList)

Relationships:

1\. GroceryList to Item:

\- One-to-many relationship (one list can contain multiple items).

2\. Item to ItemType:

\- Many-to-one relationship (multiple items can belong to the same item type).

3\. Database to Item and ItemType:

\- One-to-many relationship (database contains multiple items and item types).

4\. ListManager to GroceryList :

\- One-to-many relationship (list manager manages multiple grocery lists).

class ItemType:

def \__init_\_(self, name):

self.name = name

class Item:

def \__init_\_(self, name, quantity, item_type):

self.name = name

self.quantity = quantity

self.type = item_type

self.checked = False

class GroceryList:

def \__init_\_(self):

self.items = \[\]

def add_item(self, item):

self.items.append(item)

def delete_item(self, item):

self.items.remove(item)

def change_quantity(self, item, new_quantity):

item.quantity = new_quantity

def check_off_item(self, item):

item.checked = True

def clear_check_off_marks(self):

for item in self.items:

item.checked = False

class Database:

def \__init_\_(self):

self.items = \[\]

self.item_types = \[\]

def search_item_by_name(self, name):

return \[item for item in self.items if item.name.lower() == name.lower()\]

def add_item(self, item):

self.items.append(item)

def add_item_type(self, item_type):

self.item_types.append(item_type)

class ListManager:

def \__init_\_(self):

self.lists = \[\]

def create_list(self, name):

new_list = GroceryList()

self.lists.append((name, new_list))

def rename_list(self, list_name, new_name):

for idx, (name, \_) in enumerate(self.lists):

if name == list_name:

self.lists\[idx\] = (new_name, self.lists\[idx\]\[1\])

def select_list(self, list_name):

for name, grocery_list in self.lists:

if name == list_name:

return grocery_list

return None

def delete_list(self, list_name):

for idx, (name, \_) in enumerate(self.lists):

if name == list_name:

del self.lists\[idx\]

break

Initialize Database

db = Database()

db.add_item_type(ItemType("Fruit"))

db.add_item_type(ItemType("Vegetable"))

Create List Manager

list_manager = ListManager()

Create Grocery List

list_manager.create_list("Weekly Grocery List")

Select Grocery List

grocery_list = list_manager.select_list("Weekly Grocery List")

Add Items

item_type = db.item_types\[0\] Example: Fruit

item = Item("Apple", 2, item_type)

grocery_list.add_item(item)

Delete Item

grocery_list.delete_item(item)

Change Quantity

item = Item("Banana", 3, item_type)

grocery_list.add_item(item)

grocery_list.change_quantity(item, 5)

Check Off Item

grocery_list.check_off_item(item)

Clear Check-off Marks

grocery_list.clear_check_off_marks()

Rename List

list_manager.rename_list("Weekly Grocery List", "Monthly Grocery List")

Delete List

list_manager.delete_list("Monthly Grocery List")
