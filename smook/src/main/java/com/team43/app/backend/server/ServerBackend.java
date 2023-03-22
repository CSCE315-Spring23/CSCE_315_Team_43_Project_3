package com.team43.app.backend.server;

import com.team43.app.backend.server.Transaction;
import com.team43.app.backend.server.jdbcpostgreSQL;
import com.team43.app.backend.shared.InventoryTracker;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerBackend {
  private int employee_id;
  private int currItemCount;
  private Transaction curr_trans;
  private jdbcpostgreSQL db;

  public ServerBackend() { this(0); }

  public ServerBackend(int emp_id) {
    employee_id = emp_id;

    db = new jdbcpostgreSQL();

    currItemCount = 0;

    startTransaction();

    if (!InventoryTracker.is_initialized) {
      InventoryTracker.initializeTracker(db.getNumInventoryItems());
    }
  }

  // starts new transaction
  public void startTransaction() {
    curr_trans = new Transaction(db.getLastTransactionID() + currItemCount);
    currItemCount = 0;
  }

  // returns all categories in database
  public ArrayList<String> getCategories() { return db.getCategories(); }

  // returns all items in the given category
  public ArrayList<String> getItemsInCategory(String category) {
    return db.getItemsInCategory(category);
  }

  // returns all valid ingredients in the database
  public ArrayList<String> getAllIngredients() {
    return db.getAllIngredients();
  }

  // returns list of names of ingredients for current item
  public ArrayList<String> getItemIngredients() {
    return curr_trans.getItemIngredients();
  }

  // returns quantity of given ingredient in current item
  public int getIngredientQuantity(String name) {
    return curr_trans.getIngredientQuantity(name);
  }

  // sets size of current item, updating the cup and straw
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

  // sets quanity of current item
  public void setQuantity(int quantity) { curr_trans.setQuantity(quantity); }

  // completes adding current item to transaction
  public void completeItem() { curr_trans.completeItem(); }

  // adds new item to transaction, returning its index
  public int addItem(String name) {
    currItemCount += 1;
    return curr_trans.addItem(db.getMenuItem(name));
  }

  // removes item at given index, updating current item index if necessary
  public void removeItem(int index) {
    currItemCount -= 1;
    curr_trans.removeItem(index);
  }

  // completes current transaction and updates database
  public void completeTransaction(String purchaser_name) {
    curr_trans.completeTransaction();
    curr_trans.setPurchaserName(purchaser_name);
    db.writeTransactionData(curr_trans, employee_id);
    startTransaction();
  }

  // adds or removes given ingredient to current item
  public void adjustItem(String ingredient_name, int quantity) {
    if (quantity < getIngredientQuantity(ingredient_name)) {
      curr_trans.removeIngredient(ingredient_name, quantity);
    } else {
      curr_trans.addIngredient(db.getMenuItem(ingredient_name), quantity);
    }
  }

  // returns total price of current transaction
  public float getTransactionPrice() { return curr_trans.getPrice(); }

  // returns price of specified item
  public float getItemPrice(int index) {
    return curr_trans.getItemPrice(index);
  }

  // switches current item to given index
  public void switchItems(int index) { curr_trans.setCurrentItem(index); }

  // updates quantity of specified item in transaction
  public void adjustItemQuantity(int index, int new_quantity) {
    if (new_quantity <= 0) {
      removeItem(index);
    } else {
      curr_trans.getItems().get(index).setQuantity(new_quantity);
    }
  }

  // updates the inventory usage based on the current transaction period
  private void updateInventoryTracking() {
    // get old and new inventory usage
    HashMap<Integer, Float> old_usage = db.getCurrentUsage();
    HashMap<Integer, Float> new_usage = InventoryTracker.inventoryUsage;

    // update inventory usage locally
    for (Integer inv_id : new_usage.keySet()) {
      if (old_usage.containsKey(inv_id)) {
        old_usage.replace(inv_id,
                          old_usage.get(inv_id) + new_usage.get(inv_id));
      } else {
        old_usage.put(inv_id, new_usage.get(inv_id));
      }
    }

    // update inventory usage in database
    db.updateInventoryUsage(old_usage);
  }

  // closes connection to database after all transactions are completed
  // updates the inventory based on used items
  public void finishTransactions() {
    db.updateInventory(InventoryTracker.inventoryUsage);
    updateInventoryTracking();
    InventoryTracker.resetTracker();
    InventoryTracker.is_initialized = false;
    db.close_connection();
  }

  // returns map of excess inventory names and the percentage used since the
  // given date
  public HashMap<String, Float> getExcess(int month, int day, int year) {
    // get necessary information from database
    HashMap<Integer, Float> usage =
        db.getUsageSinceDate("" + year + "-" + month + "-" + day);
    HashMap<Integer, Float> curr_inv = db.getCurrentInventory();
    HashMap<Integer, String> names = db.getInventoryNames();

    // build map of excess inventory
    HashMap<String, Float> excess = new HashMap<String, Float>();
    for (Integer id : usage.keySet()) {
      float percentage =
          100 * (usage.get(id) / (usage.get(id) + curr_inv.get(id)));
      if (percentage < 10.0) {
        excess.put(names.get(id), percentage);
      }
    }

    return excess;
  }

  // returns map of inventory items that are currently under their average daily
  // usage. Maps item name to list with current inventory and average usage
  public HashMap<String, ArrayList<Float>> getRestock() {
    // get necessary information from database
    HashMap<Integer, Float> curr_inventory = db.getCurrentInventory();
    HashMap<Integer, Float> avg_usage = db.getAverageUsage();
    HashMap<Integer, String> names = db.getInventoryNames();

    // build map of understocked items
    HashMap<String, ArrayList<Float>> restock =
        new HashMap<String, ArrayList<Float>>();
    for (Integer id : curr_inventory.keySet()) {
      if (curr_inventory.get(id) < avg_usage.get(id)) {
        ArrayList<Float> value = new ArrayList<Float>();
        value.add(curr_inventory.get(id));
        value.add(avg_usage.get(id));
        restock.put(names.get(id), value);
      }
    }

    return restock;
  }
}