/* Sum total sales (transactions) starting from 1 year ago */
SELECT SUM(Price_Of_Transaction)
FROM Transaction;

/* Get transaction times and prices for today */
SELECT
    Time_Of_Purchase,
    Price_Of_Transaction
FROM Transaction
WHERE Time_Of_Purchase = CURRENT_DATE - INTERVAL '200 day';

/* Get all transaction items for a transaction */
SELECT *
FROM Transaction_Item
WHERE Transaction_ID = 1;

/* Get the quantity of an inventory item */
SELECT Quantity
FROM Inventory
WHERE Inventory_ID = 1;

/* Get all orders placed in the last week */
SELECT *
FROM order_item
WHERE date_placed >= CURRENT_DATE - INTERVAL '7 day';

/* Get all transactions from the last week */
SELECT *
FROM Transaction
WHERE Time_Of_Purchase >= CURRENT_DATE - INTERVAL '7 day';

/* Get all menu items names */
SELECT
    Name
FROM Menu_Item;

/* Get all ingredients for a given menu item */
SELECT *
FROM Ingredient_List
WHERE Menu_ID = 1;

/* Get the price of a menu item */
SELECT
    Price
FROM Menu_Item
WHERE Menu_ID = 1;

/* Get the employee name from a transaction */
SELECT
    First_Name,
    Last_Name
FROM Employee
WHERE Employee_ID = 1;

/* Confirm that the employee's username and password match */
SELECT COUNT(*)
FROM Employee
WHERE
    Username = 'davesmith' AND
    Password = 'password';

/* Get the role of a given employee */
SELECT
    Role
FROM Employee
WHERE Employee_ID = 2;  -- George Tamlin is the manager

/* Get the quantity remaining of all inventory items */
SELECT
    Name,
    Quantity
FROM Inventory;

/* Get all the order list items from an order */
SELECT *
FROM Order_List
WHERE Order_ID = 1;

/* Sum the costs of all orders in the past month */
SELECT SUM(Cost)
FROM Order_Item
WHERE Date_Placed >= CURRENT_DATE - INTERVAL '120 day';
