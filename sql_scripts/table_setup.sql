/* Order */
CREATE TABLE Order (
    Order_ID INTEGER PRIMARY KEY,
    Date_Placed DATE NOT NULL DEFAULT CURRENT_DATE,
    Cost FLOAT,
);

/* Order_List */
CREATE TABLE Order_List (
    -- TODO: Composite and Foreign Key
    Quantity FLOAT,
);

/* Inventory */
CREATE TABLE Inventory (
    Inventory_ID INTEGER PRIMARY KEY,
    Name VARCHAR(255),
    Type VARCHAR(255),
    Weight INTEGER,
    Price FLOAT,
    Quantity FLOAT,
    Measurement_Type VARCHAR(255),
)

/* Ingredient_List */

/* Menu_Item */

/* Transaction_Item */

/* Transaction */

/* Employee */
