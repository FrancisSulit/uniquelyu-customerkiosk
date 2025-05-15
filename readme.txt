Classes:

Driver
- Used to run main code

Items
- stores data of attributes stored in table "Items"

LoadEntries
- Interface for LoadItemEntries
- Made it just in case employee needs to access other tables

LoadItemEntries
- Has a method that gets the key (ID) for the TreeMap
- Has a method that gets all data in all attributes, stores it in a new Item object, and then uses said Item as second parameter of the TreeMap
