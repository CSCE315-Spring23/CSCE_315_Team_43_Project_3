package com.team43.app.backend.server;

import java.util.ArrayList;

public class MenuItem {
  private int menu_id;
  private String name;
  private ArrayList<InventoryItem> ingredients;
  private float price;
  private int item_count;
  private String category;

  public MenuItem(int id, String n, ArrayList<InventoryItem> ing, float p,
                  int ic, String c) {
    menu_id = id;
    name = n;
    ingredients = ing;
    price = p;
    item_count = ic;
    category = c;
  }

  // Accessors and Mutators
  public int getID() { return menu_id; }

  public String getName() { return name; }

  public ArrayList<InventoryItem> getIngredients() { return ingredients; }

  public float getPrice() { return price; }

  public void setPrice(float p) { price = p; }

  public int getItemCount() { return item_count; }

  public void setItemCount(int ic) { item_count = ic; }

  public String getCategory() { return category; }

  // returns number of dummy items in the menu item
  private int countDummyItems() { return quantityInventoryItem("Dummy Item"); }

  // removes up to num_remove dummy items from the menu item
  // returns actual number of dummy items removed
  private int removeDummyItems(int num_remove) {
    return removeItem("Dummy Item", num_remove);
  }

  // removes up to num_remove items of given id from the menu item
  // returns actual number of items removed
  public int removeItem(String name, int num_remove) {
    for (int i = 0; i < ingredients.size(); ++i) {
      if (ingredients.get(i).getName().equals(name)) {
        int curr_quantity = (int)ingredients.get(i).getQuantity();

        if (curr_quantity <= num_remove) {
          num_remove = curr_quantity;
          ingredients.remove(i);
        } else {
          ingredients.get(i).setQuantity(curr_quantity - num_remove);
        }

        item_count -= num_remove;
        return num_remove;
      }
    }

    return 0;
  }

  // adds given number of desired item to the menu item
  public boolean addItem(InventoryItem new_item, int num_add) {
    item_count += num_add;

    if (countDummyItems() > 0) {
      removeDummyItems(num_add);
    }

    for (InventoryItem item : ingredients) {
      if (item.getID() == new_item.getID()) {
        int curr_quantity = (int)item.getQuantity();
        item.setQuantity(curr_quantity + num_add);
        return true;
      }
    }

    return false;
  }

  // returns number of given item in the menu item
  public int quantityInventoryItem(String name) {
    for (InventoryItem item : ingredients) {
      if (item.getName().equals(name)) {
        return (int)item.getQuantity();
      }
    }

    return 0;
  }
}