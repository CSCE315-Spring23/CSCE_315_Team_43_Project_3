package com.team43.project3.smook.service;

import java.util.List;

import com.team43.project3.smook.model.Employee;
import com.team43.project3.smook.model.Inventory;
import com.team43.project3.smook.model.Menu_Item;
import com.team43.project3.smook.model.Order_Item;

public interface SmookService {
    //Test Functionalities
    public void testDBConnection();

    //Employee
    public Integer login(String username, String password);
    
    //Generic View Tables
    public List<Inventory> viewInventory();
    public List<Menu_Item> viewMenuItems();
    public List<Order_Item> viewOrderItems();

    //Inventory
    public Inventory getInventoryItem(long inventoryId);
    public Inventory editInventoryItem(long inventoryId, String name, float price, float quantity, String measurement_type);
    public Inventory addInventoryItem(String name, float price, float quantity, String measurement_type);
    public List<String> getAllIngredients();

    //Menu Item
    public Menu_Item getMenuItem(long menuItemId);
    public Menu_Item editMenuItem(long menuItemId, String name, String type, float price, int ingredientAmount, List<Long> ingredientIds, List<Long> ingredientQuantity);
    public Menu_Item addMenuItem(String name, String type, float price, int ingredientAmount, List<Integer> ingredientIds, List<Integer> ingredientQuantity);
    public List<String> getCategories();
    public List<String> getItemsInCategory(String category);
    public float getPriceofMenuItem(String name);
    
    //Multiple Tables
    public List<String> getIngredientsInItem(String name);

}
