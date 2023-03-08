package com.team43.app.backend.server;

import com.team43.app.backend.shared.InventoryTracker;
import java.util.ArrayList;

public class TransactionItem {
  private MenuItem main_item;
  private MenuItem cup;
  private MenuItem straw;
  private ArrayList<MenuItem> add_ons;
  private String size;
  private int init_item_count;
  private float price;
  private int quantity;
  private boolean is_smoothie;

  public TransactionItem(MenuItem main) {
    main_item = main;
    cup = null;
    straw = null;
    add_ons = new ArrayList<MenuItem>();
    size = "";
    init_item_count = main_item.getItemCount();
    price = main_item.getPrice();
    quantity = 1;
    is_smoothie = !(main_item.getCategory().equals("Enhance") ||
                    main_item.getCategory().equals("Other"));
  }

  // Accessors and Mutators
  public MenuItem getMainItem() { return main_item; }

  public MenuItem getCup() { return cup; }

  public MenuItem getStraw() { return straw; }

  public void setCupAndStraw(MenuItem c, MenuItem s) {
    cup = c;
    straw = s;
  }

  public ArrayList<MenuItem> getAddOns() { return add_ons; }

  public String getSize() { return size; }

  public void setSize(String s) { size = s; }

  public float getPrice() { return price; }

  public int getQuantity() { return quantity; }

  public void setQuantity(int q) { quantity = q; }

  // returns list of full item with main item, cup, straw, and add-ons
  public ArrayList<MenuItem> getFullItem() {
    ArrayList<MenuItem> full_item = new ArrayList<MenuItem>();
    full_item.add(main_item);
    full_item.add(cup);
    full_item.add(straw);
    full_item.addAll(add_ons);
    return full_item;
  }

  // returns list of the names of all ingredients in the item
  public ArrayList<String> getAllIngredients() {
    ArrayList<String> ing = new ArrayList<String>();

    for (InventoryItem item : main_item.getIngredients()) {
      ing.add(item.getName());
    }

    for (MenuItem add_on : add_ons) {
      for (InventoryItem item : add_on.getIngredients()) {
        ing.add(item.getName());
      }
    }

    return ing;
  }

  // returns the quantity of the given ingredient in the item
  public int getIngredientQuantity(String name) {
    int quantity = main_item.quantityInventoryItem(name);

    for (MenuItem item : add_ons) {
      quantity += item.quantityInventoryItem(name);
    }

    return quantity;
  }

  // adds given amount of given item to transaction item
  public void addItem(MenuItem item, int quantity) {
    if (main_item.addItem(item.getIngredients().get(0), quantity)) {
      return;
    }

    for (MenuItem add_on : add_ons) {
      if (add_on.addItem(item.getIngredients().get(0), quantity)) {
        return;
      }
    }

    item.getIngredients().get(0).setQuantity(quantity);
    add_ons.add(item);
  }

  // removes given amount of given item from transaction item
  public void removeItem(String name, int num_remove) {
    num_remove -= main_item.removeItem(name, num_remove);
    if (num_remove == 0) {
      return;
    }

    for (int i = 0; i < add_ons.size(); ++i) {
      MenuItem item = add_ons.get(i);
      int removed = item.removeItem(name, num_remove);
      main_item.setItemCount(main_item.getItemCount() - removed);

      if (item.getIngredients().isEmpty()) {
        add_ons.remove(i--);
      }

      num_remove -= removed;
      if (num_remove == 0) {
        return;
      }
    }
  }

  // scales ingredients by appropriate factor based on size
  // should only be called at end of transaction for smoothies
  private void scaleIngredients() {
    float scale = 1;
    if (size.equals("Small")) {
      return;
    } else if (size.equals("Medium")) {
      scale = (float)1.6;
    } else { // Large
      scale = 2;
    }

    for (InventoryItem ingredient : main_item.getIngredients()) {
      ingredient.setQuantity(ingredient.getQuantity() * scale);
    }

    for (MenuItem item : add_ons) {
      for (InventoryItem ingredient : item.getIngredients()) {
        ingredient.setQuantity(ingredient.getQuantity() * scale);
      }
    }
  }

  // returns all inventory used by the current item
  // should only be called at end of transaction
  public void updateInventoryUsed() {
    if (!is_smoothie) {
      InventoryItem item = main_item.getIngredients().get(0);
      InventoryTracker.updateItemUsage(item.getID(),
                                       quantity * item.getQuantity());
      return;
    }

    scaleIngredients();

    for (InventoryItem ingredient : main_item.getIngredients()) {
      InventoryTracker.updateItemUsage(ingredient.getID(),
                                       quantity * ingredient.getQuantity());
    }

    InventoryItem cup_ingr = cup.getIngredients().get(0);
    InventoryTracker.updateItemUsage(cup_ingr.getID(),
                                     quantity * cup_ingr.getQuantity());
    InventoryItem straw_ingr = cup.getIngredients().get(0);
    InventoryTracker.updateItemUsage(straw_ingr.getID(),
                                     quantity * straw_ingr.getQuantity());

    for (MenuItem item : add_ons) {
      for (InventoryItem ingredient : item.getIngredients()) {
        InventoryTracker.updateItemUsage(ingredient.getID(),
                                         quantity * ingredient.getQuantity());
      }
    }
  }

  // returns list of menu item IDs for transaction_item table
  public ArrayList<Integer> getMenuItemIDs() {
    ArrayList<Integer> menu_ids = new ArrayList<Integer>();
    menu_ids.add(main_item.getID());

    for (MenuItem item : add_ons) {
      menu_ids.add(item.getID());
    }

    if (quantity > 1) {
      ArrayList<Integer> single_item = menu_ids;
      menu_ids = new ArrayList<Integer>();

      for (int i = 0; i < quantity; ++i) {
        menu_ids.addAll(single_item);
      }
    }

    return menu_ids;
  }

  // computes price of item
  // should only be called at end of transaction
  public void computePrice() {
    int curr_item_count = main_item.getItemCount();
    for (MenuItem item : add_ons) {
      curr_item_count += item.getItemCount();
    }

    int count_diff = 0;
    if (curr_item_count > init_item_count) {
      count_diff = curr_item_count - init_item_count;
    }

    float add_on_price = (float)0.99;
    price = main_item.getPrice() + add_on_price * count_diff + cup.getPrice();
  }
}
