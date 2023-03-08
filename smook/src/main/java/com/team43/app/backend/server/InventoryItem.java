package com.team43.app.backend.server;

public class InventoryItem {
  private int inventory_id;
  private String name;
  private float price;
  private float quantity; // in menu item, not inventory

  public InventoryItem(int id, String n, float p, float q) {
    inventory_id = id;
    name = n;
    price = p;
    quantity = q;
  }

  // Accessors and Mutators
  public int getID() { return inventory_id; }

  public String getName() { return name; }

  public void setName(String n) { name = n; }

  public float getPrice() { return price; }

  public void setPrice(float p) { price = p; }

  public float getQuantity() { return quantity; }

  public void setQuantity(float q) { quantity = q; }
}