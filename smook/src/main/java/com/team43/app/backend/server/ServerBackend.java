package com.team43.app.backend.server;

import com.team43.app.backend.server.jdbcpostgreSQL;
import com.team43.app.backend.shared.InventoryTracker;
import com.team43.app.backend.server.Transaction;
import java.util.ArrayList;

public class ServerBackend {
    private int employee_id;
    private int currItemCount;
    private Transaction curr_trans;
    private jdbcpostgreSQL db;

    public ServerBackend() {
        this(0);
    }

    public ServerBackend(int emp_id) {
        employee_id = emp_id;

        db = new jdbcpostgreSQL();

        currItemCount = 0;

        startTransaction();

        if (!InventoryTracker.is_initialized) {
            InventoryTracker.initializeTracker(db.getNumInventoryItems());
        }
    }

    // Starts new transaction
    public void startTransaction() {
        curr_trans = new Transaction(db.getLastTransactionID() + currItemCount);
        currItemCount = 0;
    }

    // Returns all categories in database
    public ArrayList<String> getCategories() {
        return db.getCategories();
    }

    // Returns all items in the given category
    public ArrayList<String> getItemsInCategory(String category) {
        return db.getItemsInCategory(category);
    }

    // Returns all valid ingredients in the database
    public ArrayList<String> getAllIngredients() {
        return db.getAllIngredients();
    }

    // Returns list of names of ingredients for current item
    public ArrayList<String> getItemIngredients() {
        return curr_trans.getItemIngredients();
    }

    // Returns quantity of given ingredient in current item
    public int getIngredientQuantity(String name) {
        return curr_trans.getIngredientQuantity(name);
    }

    // Sets size of current item, updating the cup and straw
    public void setSize(String size) {
        String cup_name = size + " Cup";

        String straw_name;
        if (size.equals("Small")) {
            straw_name = "Small Straw";
        } else {
            straw_name = "Large Straw";
        }

        curr_trans.setSize(size, db.getMenuItem(cup_name),
                db.getMenuItem(straw_name));
    }

    // Sets quanity of current item
    public void setQuantity(int quantity) {
        curr_trans.setQuantity(quantity);
    }

    // Completes adding current item to transaction
    public void completeItem() {
        curr_trans.completeItem();
    }

    // Adds new item to transaction, returning its index
    public int addItem(String name) {
        currItemCount += 1;
        return curr_trans.addItem(db.getMenuItem(name));
    }

    // Removes item at given index, updating current item index if necessary
    public void removeItem(int index) {
        currItemCount -= 1;
        curr_trans.removeItem(index);
    }

    // Completes current transaction and updates database
    public void completeTransaction(String purchaser_name) {
        curr_trans.completeTransaction();
        curr_trans.setPurchaserName(purchaser_name);
        db.writeTransactionData(curr_trans, employee_id);
        startTransaction();
    }

    // Adds or removes given ingredient to current item
    public void adjustItem(String ingredient_name, int quantity) {
        if (quantity < 0) {
            curr_trans.removeIngredient(ingredient_name, quantity);
        } else {
            curr_trans.addIngredient(db.getMenuItem(ingredient_name), quantity);
        }
    }

    // Returns total price of current transaction
    public float getTransactionPrice() {
        return curr_trans.getPrice();
    }

    // Returns price of specified item
    public float getItemPrice(int index) {
        return curr_trans.getItemPrice(index);
    }

    // Switches current item to given index
    public void switchItems(int index) {
        curr_trans.setCurrentItem(index);
    }

    // Updates quantity of specified item in transaction
    public void adjustItemQuantity(int index, int new_quantity) {
        if (new_quantity <= 0) {
            removeItem(index);
        } else {
            curr_trans.getItems().get(index).setQuantity(new_quantity);
        }
    }

    // Closes connection to database after all transactions are completed
    // and updates the inventory based on used items
    public void finishTransactions() {
        // TODO: update inventory
        db.updateInventory(InventoryTracker.inventoryUsage);
        InventoryTracker.resetTracker();
        db.closeConnection();
    }
}
