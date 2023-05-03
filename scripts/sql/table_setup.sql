/* Inventory */
CREATE TABLE Inventory (
    Inventory_ID INT PRIMARY KEY,
    Name VARCHAR(255),
    Price FLOAT,
    Quantity FLOAT,
    Measurement_Type VARCHAR(255),
    Restock_Amount INT
);

/* Order_Item */
CREATE TABLE Order_Item (
    Order_ID INT PRIMARY KEY,
    Date_Placed TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
    Cost FLOAT
);

/* Order_List */
CREATE TABLE Order_List (
    Order_List_ID INT PRIMARY KEY,
    Inventory_ID INT,
    Order_ID INT,
    Quantity FLOAT,

    CONSTRAINT FK_Inventory_ID
        FOREIGN KEY(Inventory_ID)
        REFERENCES Inventory(Inventory_ID),

    CONSTRAINT FK_Order_ID
        FOREIGN KEY(Order_ID)
        REFERENCES Order_Item(Order_ID)
);

/* Menu_Item */
CREATE TABLE Menu_Item (
    Menu_ID INT PRIMARY KEY,
    Name VARCHAR(255),
    Type VARCHAR(255),
    Price FLOAT,
    Ingredient_Amount INT
);

/* Ingredient_List */
CREATE TABLE Ingredient_List (
    Inventory_ID INT,
    Menu_ID INT,
    PRIMARY KEY(Inventory_ID, Menu_ID),

    Quantity FLOAT,

    CONSTRAINT FK_Inventory_ID
        FOREIGN KEY(Inventory_ID)
        REFERENCES Inventory(Inventory_ID),

    CONSTRAINT FK_Menu_ID
        FOREIGN KEY(Menu_ID)
        REFERENCES Menu_Item(Menu_ID)
);

/* Employee */
CREATE TABLE Employee (
    Employee_ID INT PRIMARY KEY,
    First_Name VARCHAR(255),
    Last_Name VARCHAR(255),
    Role VARCHAR(255),
    Username VARCHAR(255),
    Password VARCHAR(255)
);

/* Transaction */
CREATE TABLE Transaction (
    Transaction_ID INT PRIMARY KEY,
    Employee_ID INT,

    CONSTRAINT FK_Employee_ID
        FOREIGN KEY(Employee_ID)
        REFERENCES Employee(Employee_ID),

    Purchaser_Name VARCHAR(255),
    Price_Of_Transaction FLOAT,
    Time_Of_Purchase TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

/* Transaction_Item */
CREATE TABLE Transaction_Item (
    Transaction_Item_ID INT PRIMARY KEY,
    Inventory_ID INT,
    Transaction_ID INT,
    Quantity INT,

    CONSTRAINT FK_Inventory_ID
        FOREIGN KEY(Inventory_ID)
        REFERENCES Inventory(Inventory_ID),

    CONSTRAINT FK_Transaction_ID
        FOREIGN KEY(Transaction_ID)
        REFERENCES Transaction(Transaction_ID)
);

CREATE TABLE Inventory_Usage (
    Inventory_Usage_ID INT PRIMARY KEY,
    Inventory_ID INT,
    usage FLOAT,
    Date TIMESTAMP,
    CONSTRAINT FK_Inventory_ID
        FOREIGN KEY(Inventory_ID)
        REFERENCES Inventory(Inventory_ID)
);

CREATE TABLE Menu_Tracker (
    Menu_Tracker_ID INT PRIMARY KEY,
    Transaction_ID INT,
    Menu_ID INT,
    Time TIMESTAMP,

    CONSTRAINT FK_Transaction_ID
        FOREIGN KEY(Transaction_ID)
        REFERENCES Transaction(Transaction_ID),

    CONSTRAINT FK_Menu_ID
        FOREIGN KEY(Menu_ID)
        REFERENCES Menu_Item(Menu_ID)
)