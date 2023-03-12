package com.team43.app.backend.server;

import com.team43.app.backend.shared.InventoryTracker;
import java.util.ArrayList;

public class Transaction {
    private int transaction_id;
    private String purchaser_name;
    private ArrayList<TransactionItem> items;
    private int curr_item;
    private float price;

    public Transaction() {
        transaction_id = -1;
        purchaser_name = "mystery man";
        items = new ArrayList<TransactionItem>();
        curr_item = -1;
        price = 0;
    }

    public Transaction(int id) {
        transaction_id = id;
        purchaser_name = "mystery man";
        items = new ArrayList<TransactionItem>();
        curr_item = -1;
        price = 0;
    }

    // Accessors and Mutators
    public int getID() {
        return transaction_id;
    }

    public String getPurchaserName() {
        return purchaser_name;
    }

    public void setPurchaserName(String name) {
        purchaser_name = name;
    }

    public ArrayList<TransactionItem> getItems() {
        return items;
    }

    public int getCurrentItem() {
        return curr_item;
    }

    public void setCurrentItem(int index) {
        curr_item = index;
    }

    public float getPrice() {
        return price;
    }

    // Returns price of item at given index
    public float getItemPrice(int index) {
        return items.get(index).getPrice();
    }

    // Returns list of names of all ingredients in current item
    public ArrayList<String> getItemIngredients() {
        return items.get(curr_item).getAllIngredients();
    }

    // Returns quantity of given ingredient in current item
    public int getIngredientQuantity(String name) {
        return items.get(curr_item).getIngredientQuantity(name);
    }

    // Sets size, cup, and straw of current item
    public void setSize(String size, MenuItem cup, MenuItem straw) {
        TransactionItem trans = items.get(curr_item);
        trans.setSize(size);
        trans.setCupAndStraw(cup, straw);
    }

    // Sets quantity of current item
    public void setQuantity(int quantity) {
        items.get(curr_item).setQuantity(quantity);
    }

    // Adds new item to transaction, updating and returning the current item index
    public int addItem(MenuItem item) {
        items.add(new TransactionItem(item));
        curr_item = items.size() - 1;
        return curr_item;
    }

    // Removes item at given index from transaction
    public void removeItem(int index) {
        items.remove(index);

        if (index < curr_item) {
            --curr_item;
        } else if (index == curr_item) {
            curr_item = -1;
        }
    }

    // Removes given amount of given ingredient from current item
    public void removeIngredient(String ingredient_name, int quantity) {
        items.get(curr_item).removeItem(ingredient_name, quantity);
    }

    // Adds given amount of given ingredient to current item
    public void addIngredient(MenuItem item, int quantity) {
        items.get(curr_item).addItem(item, quantity);
    }

    // Computes the price of completed item and updates current item to unselected
    // and updates total price of transaction
    public void completeItem() {
        items.get(curr_item).computePrice();
        curr_item = -1;

        price = 0;
        for (TransactionItem item : items) {
            price += item.getPrice();
        }
    }

    // Completes current transaction, calculating price and updating inventory
    //  tracker
    public void completeTransaction() {
        price = 0;
        for (TransactionItem item : items) {
            item.computePrice();
            price += item.getPrice();
            item.updateInventoryUsed();
        }
    }

    public ArrayList<Integer> getMenuItemIDs() {
        ArrayList<Integer> menu_ids = new ArrayList<Integer>();
        for (TransactionItem item : items) {
            menu_ids.addAll(item.getMenuItemIDs());
        }

        return menu_ids;
    }
}
