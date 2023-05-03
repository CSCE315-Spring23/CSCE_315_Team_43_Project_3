package com.team43.project3.smook.service;

import java.sql.Timestamp;
import java.util.List;

import com.team43.project3.smook.model.Employee;
import com.team43.project3.smook.model.Inventory;
import com.team43.project3.smook.model.Menu_Item;
import com.team43.project3.smook.model.Order_Item;
import com.team43.project3.smook.model.Transaction;

public interface SmookService {
    //Time Trackers
    public Timestamp setStart();
    public void setTime(Timestamp time);

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
    public Inventory getInventoryItemByName(String name);
    public Inventory editInventoryItem(long inventoryId, String name, float price, float quantity, String measurement_type, Integer restockAmount);
    public Inventory addInventoryItem(String name, float price, float quantity, String measurement_type);
    public List<String> getAllIngredients();
    public List<String> getIngredientsInItem(String name);
    public List<Inventory> getAllValidInventory();

    //Menu Item
    public Menu_Item getMenuItem(long menuItemId);
    public Menu_Item editMenuItem(long menuItemId, String name, String type, float price, int ingredientAmount, List<Long> ingredientIds, List<Long> ingredientQuantity);
    public Menu_Item addMenuItem(String name, String type, float price, int ingredientAmount, List<Integer> ingredientIds, List<Integer> ingredientQuantity);
    public List<String> getCategories();
    public List<String> getItemsInCategory(String category);
    public float getPriceofMenuItem(String name);
    
    //Transaction
    public Transaction addTransaction(long employeeId, String purchaser, float price, List<String> menuNames, List<Inventory> itemList, List<Integer> quantityList);

    //Restock Orders
    public void restockInventoryItem(String name, Integer amount);

    //Reports
    public List<Item> createSalesReport(Timestamp start, Timestamp end);
    public List<Item> createXReport();
    public List<Item> createZReport();
    public List<Report> createExcessReport(Timestamp start, Timestamp end);
    public List<Report> createRestockReport();
}
