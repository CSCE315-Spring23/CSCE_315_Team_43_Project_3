package com.team43.project3.smook.service;

import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.metal.MetalBorders.MenuItemBorder;

import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team43.project3.smook.model.Employee;
import com.team43.project3.smook.model.Ingredient_List;
import com.team43.project3.smook.model.Inventory;
import com.team43.project3.smook.model.Menu_Item;
import com.team43.project3.smook.model.Menu_Tracker;
import com.team43.project3.smook.model.Order_Item;
import com.team43.project3.smook.model.Order_List;
import com.team43.project3.smook.model.Transaction;
import com.team43.project3.smook.model.Transaction_Item;
import com.team43.project3.smook.model.keys.Ingredient_List_Key;
import com.team43.project3.smook.repository.EmployeeRepository;
import com.team43.project3.smook.repository.IngredientListRepository;
import com.team43.project3.smook.repository.InventoryRepository;
import com.team43.project3.smook.repository.MenuItemRepository;
import com.team43.project3.smook.repository.MenuTrackerRepository;
import com.team43.project3.smook.repository.OrderItemRepository;
import com.team43.project3.smook.repository.OrderListRepository;
import com.team43.project3.smook.repository.TransactionItemRepository;
import com.team43.project3.smook.repository.TransactionRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;

@Service
public class SmookServiceImpl implements SmookService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IngredientListRepository ingredientListRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderListRepository orderListRepository;

    @Autowired
    private TransactionItemRepository transactionItemRepository;

    @Autowired 
    private TransactionRepository transactionRepository;

    @Autowired
    private MenuTrackerRepository menuTrackerRepository;
    
    Timestamp start = setStart();

    public Timestamp setStart() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTime().getTime());
    }

    public void setTime(Timestamp time) {
        start = time;
    }
    /*
     *Test Functions
     */
    public void testDBConnection() {
        Employee testEmployee = employeeRepository.getReferenceById(0L);
        Ingredient_List_Key testKey = new Ingredient_List_Key(7, 0);
        Ingredient_List testIngredientList = ingredientListRepository.getReferenceById(testKey);
        Inventory testInventory = inventoryRepository.getReferenceById(1L);
        Menu_Item testMenuItem = menuItemRepository.getReferenceById(1L);
        Order_Item testOrderItem = orderItemRepository.getReferenceById(1L);
        Order_List testOrderList = orderListRepository.getReferenceById(1L);
        Transaction_Item testTransactionItem = transactionItemRepository.getReferenceById(1L);
        Transaction testTransaction = transactionRepository.getReferenceById(1L);
        System.out.println(testEmployee.getFirstName() + ' ' + testIngredientList.getQuantity() + ' ' + testInventory.getName() + ' ' + testMenuItem.getName() + ' ' + testOrderItem.getDatePlaced() + ' ' + testOrderList.getQuantity() + ' ' + testTransactionItem.getTransactionItemId() + ' ' + testTransaction.getPurchaserName());
        
        Employee newEmployee = new Employee(3L, "Charles", "Barkley", "employee", "cbark", "cbarksalot84");
        employeeRepository.save(newEmployee);
    }

    //Employee
    /**
    This method allows the user to log in with a given username and password.
    @param username The username of the user trying to log in.
    @param password The password of the user trying to log in.
    @return Integer 0 if the login credentials are invalid, 1 if the user is a manager, and 2 if the user is an employee.
    */
    public Integer login(String username, String password) {
        System.out.println("entered login");
        List<Employee> validLogins = employeeRepository.findByUsernameAndPassword(username, password);
        if(validLogins.size() == 1 || validLogins.get(0) == null) {
            System.out.println(validLogins.get(0).getRole().equals("manager"));
            if(validLogins.get(0).getRole().equals("manager")) {
                return 1;
            }
            else if (validLogins.get(0).getRole().equals("employee")) {
                return 2;
            }
            else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }

    //View Tables

    /**
    This method retrieves a list of all inventory items in the database.
    @return List<Inventory> A list of all inventory items.
    */
    public List<Inventory> viewInventory() {
        return inventoryRepository.findAll();
    }
    
    /**
    This method retrieves a list of all menu items in the database.
    @return List<Menu_Item> A list of all menu items.
    */
    public List<Menu_Item> viewMenuItems() {
        return menuItemRepository.findAll();
    }

    /**
    This method retrieves a list of all order items in the database.
    @return List<Order_Item> A list of all order items.
    */
    public List<Order_Item> viewOrderItems() {
        return orderItemRepository.findAll();
    }

    // Inventory

    /**
    This method retrieves an inventory item with a given ID.
    @param inventoryId The ID of the inventory item to retrieve.
    @return Inventory The inventory item with the specified ID.
    */
    public Inventory getInventoryItem(long inventoryId) {
        return inventoryRepository.getReferenceById(inventoryId);
    }

    /**
    This method retrieves an inventory item with a given name.
    @param name The name of the inventory item to retrieve.
    @return Inventory The inventory item with the specified name.
    */
    public Inventory getInventoryItemByName(String name) {
        List<Inventory> inventory = inventoryRepository.findByName(name);
        System.out.println(inventory);
        return inventory.get(0);
    }

    /**
    This method edits an inventory item with a given ID.
    @param inventoryId The ID of the inventory item to edit.
    @param name The new name of the inventory item.
    @param price The new price of the inventory item.
    @param quantity The new quantity of the inventory item.
    @param measurement_type The new measurement type of the inventory item.
    @param restockAmount The new restock amount of the inventory item.
    @return Inventory The edited inventory item.
    */
    public Inventory editInventoryItem(long inventoryId, String name, float price, float quantity, String measurement_type, Integer restockAmount) {
        Inventory inv = inventoryRepository.getReferenceById(inventoryId);
        inv.setName(name);
        inv.setPrice(price);
        inv.setQuantity(quantity);
        inv.setMeasurementType(measurement_type);
        inv.setRestockAmount(restockAmount);
        inventoryRepository.save(inv);
        return inv;
    }

    /**

    Adds a new inventory item to the system with the given name, price, quantity, and measurement type.
    The ID of the new item is generated automatically as the next sequential ID in the inventory repository.
    @param name The name of the new inventory item.
    @param price The price of the new inventory item.
    @param quantity The quantity of the new inventory item.
    @param measurement_type The measurement type of the new inventory item.
    @return The newly created Inventory object.
    */
    public Inventory addInventoryItem(String name, float price, float quantity, String measurement_type) {
        Inventory inv = new Inventory(inventoryRepository.findCurrentId()+1, name, price, quantity, measurement_type);
        inventoryRepository.save(inv);
        return inv;
    }

    // Menu Item

    /**
    Retrieves the Menu_Item object with the given menu item ID from the menu item repository.
    @param menuItemId The ID of the menu item to retrieve.
    @return The Menu_Item object with the specified ID.
    */
    public Menu_Item getMenuItem(long menuItemId) {
        return menuItemRepository.getReferenceById(menuItemId);
    }

    /**
    Updates the properties of the Menu_Item object with the specified menu item ID in the menu item repository,
    including its name, type, price, and ingredient list.
    @param menuItemId The ID of the menu item to update.
    @param name The new name for the menu item.
    @param type The new type for the menu item.
    @param price The new price for the menu item.
    @param ingredientAmount The new number of ingredients in the menu item.
    @param ingredientIds A list of ingredient IDs to add to the menu item's ingredient list.
    @param ingredientQuantity A list of ingredient quantities corresponding to the ingredient IDs to add to the menu item's ingredient list.
    @return The updated Menu_Item object.
    */
    public Menu_Item editMenuItem(long menuItemId, String name, String type, float price, int ingredientAmount, List<Long> ingredientIds, List<Long> ingredientQuantity) {
        Menu_Item item = menuItemRepository.getReferenceById(menuItemId);
        item.setName(name);
        item.setType(type);
        item.setPrice(price);
        item.setIngredientAmount(ingredientAmount);
        menuItemRepository.save(item);
        for(int i = 0; i < ingredientIds.size(); i++) {
            Ingredient_List_Key ingListKey = new Ingredient_List_Key(ingredientIds.get(i), menuItemId);
            Ingredient_List ingList = ingredientListRepository.getReferenceById(ingListKey);
            if(ingList == null) {
                ingList = new Ingredient_List(ingredientIds.get(i), menuItemId, ingredientQuantity.get(i));
            }
            else {
                Inventory tempInv = inventoryRepository.getReferenceById(ingredientIds.get(i));
                ingList.setInventory(tempInv);
                ingList.setQuantity(ingredientQuantity.get(i));
            }
            ingredientListRepository.save(ingList);
        }
        return item;
    }

    /**
    This method adds a new menu item with the given parameters and returns the created object.
    @param name the name of the menu item
    @param type the type of the menu item
    @param price the price of the menu item
    @param ingredientAmount the number of ingredients in the menu item
    @param ingredientIds the list of ingredient IDs used in the menu item
    @param ingredientQuantity the list of ingredient quantities used in the menu item
    @return the created Menu_Item object
    */
    public Menu_Item addMenuItem(String name, String type, float price, int ingredientAmount, List<Integer> ingredientIds, List<Integer> ingredientQuantity) {
        long tempId = menuItemRepository.findCurrentId()+1;
        Menu_Item item = new Menu_Item(tempId, name, type, price, ingredientAmount);
        menuItemRepository.save(item);
        for(int i = 0; i < ingredientIds.size(); i++) {
            Ingredient_List ingList = new Ingredient_List(ingredientIds.get(i), tempId, ingredientQuantity.get(i));
            ingredientListRepository.save(ingList);
        }
        return item;
    }

    // Transaction

    /**
    This method adds a new transaction with the given parameters and returns the created object.
    @param employeeId the ID of the employee who made the transaction
    @param purchaser the name of the purchaser
    @param price the price of the transaction
    @param menuNames the list of menu item names included in the transaction
    @param itemList the list of Inventory objects used in the transaction
    @param quantityList the list of quantities used in the transaction
    @return the created Transaction object
    */
    public Transaction addTransaction(long employeeId, String purchaser, float price, List<String> menuNames, List<Inventory> itemList, List<Integer> quantityList) {
        Employee emp = employeeRepository.getReferenceById(employeeId);
        Date now = new Date();
        Timestamp timeOfPurchase = new Timestamp(now.getTime());
        // System.out.println("got time");
        long transactionId = transactionRepository.findCurrentId() + 1;
        // System.out.println("got transid: " + transactionId);
        System.out.println(transactionId + " " + emp + " " + purchaser + " " + price + " " + timeOfPurchase);
        Transaction trans = new Transaction(transactionId, emp, purchaser, price, timeOfPurchase);
        transactionRepository.save(trans);
        long trackerId = menuTrackerRepository.findCurrentId() + 1;
        for(String item : menuNames) {
            Menu_Item menuItem = menuItemRepository.findByName(item).get(0);
            Menu_Tracker tracker = new Menu_Tracker(trackerId, trans, menuItem, timeOfPurchase);
            trackerId++;
            menuTrackerRepository.save(tracker);
        }
        int i = 0;
        long transItemId = transactionItemRepository.findCurrentId() + 1;
        System.out.println("Size of list: " + itemList.size());
        for(Inventory item : itemList) {
            System.out.println(transItemId + " " + item + " " + trans + " " + quantityList.get(i));
            Transaction_Item transItem = new Transaction_Item(transItemId, item, trans, quantityList.get(i));
            transactionItemRepository.save(transItem);
            i++;    
            transItemId++;
        }
        return trans;
    }

    //Order

    public Order_Item addOrderItem(Float cost, List<String> inventory, List<Integer> quantity) {
        Date now = new Date();
        Timestamp datePlaced = new Timestamp(now.getTime());
        List<Inventory> invList = new ArrayList<Inventory>();
        for(String temp : inventory) {
            invList.add(inventoryRepository.findByName(temp).get(0));
        }
        Order_Item orderItem = new Order_Item(orderItemRepository.findCurrentId()+1, datePlaced, 0);
        orderItemRepository.save(orderItem);
        Long orderListId = orderListRepository.findCurrentId()+1;
        int i = 0;
        for(Inventory inv : invList) {
            Order_List orderList = new Order_List(orderListId, inv, orderItem, quantity.get(i));
            orderListRepository.save(orderList);
            orderListId++;
            i++;
        }
        return orderItem;
    }

    public Order_Item editOrderItem(Long id, Float cost, List<String> inventory, List<Integer> quantity) {
        List<Inventory> invList = new ArrayList<Inventory>();
        for(String temp : inventory) {
            invList.add(inventoryRepository.findByName(temp).get(0));
        }
        Order_Item orderItem = orderItemRepository.getReferenceById(id);
        orderItem.setCost(cost);
        orderItemRepository.save(orderItem);
        Long orderListId = orderListRepository.findCurrentId()+1;
        List<Order_List> orderList = orderListRepository.findByOrderId(orderListId);
        List<Order_List> deleteList = new ArrayList<Order_List>();
        List<Order_List> saveList = new ArrayList<Order_List>();
        for(Order_List order : orderList) {
            if(invList.contains(order.getInventory())) {
                order.setQuantity(quantity.get(invList.indexOf(order.getInventory())));
                saveList.add(order);
            }
            else {
                deleteList.add(order);
            }
        }
        for(int i = 0; i < invList.size(); i++) {
            for(Order_List order : saveList) {
                if(order.getInventory() != invList.get(i)) {
                    Order_List newOrder = new Order_List(orderListRepository.findCurrentId()+1, invList.get(i), orderItem, quantity.get(i));
                    orderListRepository.save(newOrder);
                }
                else {
                    orderListRepository.save(order);
                }
            }
        }
        orderListRepository.deleteAll(deleteList);
        return orderItem;
    }

    /**
    Restocks a specific inventory item with a specified amount.
    @param name A String representing the name of the inventory item.
    @param amount An Integer representing the amount of the inventory item to restock.
    */
    public void restockInventoryItem(String name, Integer amount) {
        Inventory inv = inventoryRepository.findByName(name).get(0);
        inv.setQuantity(amount);
        inventoryRepository.save(inv);
    }

    // Reports
    
    /**
    This method creates a sales report based on the transactions that occurred between the given timestamps and returns the list of Item objects.
    @param start the start time of the report
    @param end the end time of the report
    @return the list of Item objects containing the sales report data
    */
    public List<Item> createSalesReport(Timestamp start, Timestamp end) {
        List<Item> tempReport = new ArrayList<Item>();
        Integer menuItemAmount = menuItemRepository.findMenuCount();
        for(int i = 0; i < menuItemAmount; i++) {
            Menu_Item menu_item = menuItemRepository.getReferenceById((long)i);
            tempReport.add(new Item(menu_item.getMenuId(), menu_item.getName(), menu_item.getType(), menu_item.getPrice()));
        }
        List<Menu_Tracker> trackers = menuTrackerRepository.findBetweenTimes(start, end);
        for(Menu_Tracker tracker : trackers) {
            Menu_Item menu_item = tracker.getMenu();
            tempReport.get((int)menu_item.getMenuId()).incrementQuantity();
        }
        return tempReport;
    }

    /**
    Generates a sales report from the beginning of the day until now.
    @return A List of Items representing the sales report.
    */
    public List<Item> createXReport() {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        return createSalesReport(start, now);
    }

    /**
    Generates an X report from the beginning of the day until now and reset the time to which the reports count.
    @return A List of Items representing the Z report.
    */
    public List<Item> createZReport() {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        List<Item> zReport = createSalesReport(start, now);
        setTime(now);
        return zReport;
    }

    /**
    Generates a report of inventory that is in excess (more than 10% of the inventory capacity) within a given time frame.
    @param start A Timestamp representing the start of the time frame.
    @param end A Timestamp representing the end of the time frame.
    @return A List of Reports representing the excess inventory.
    */
    public List<Report> createExcessReport(Timestamp start, Timestamp end) {
        Integer inventoryCount = inventoryRepository.findInventoryCount();
        Map<Long, Float> tempLists = new HashMap<Long, Float>();
        for(int i = 7; i <= inventoryCount+6; i++) {
            Inventory tempInventory = inventoryRepository.getReferenceById((long)i);
            tempLists.put(tempInventory.getInventoryId(), 0f);
        }
        Long maxId = transactionRepository.findMaxIdInTime(start, end);
        Long minId = transactionRepository.findMinIdInTime(start, end);
        List<Transaction_Item> transItemList = transactionItemRepository.findTransactionsInRange(minId, maxId);
        for(Transaction_Item transItem : transItemList) {
            Inventory inv = transItem.getInventory();
            System.out.println(inv.getInventoryId() + " | " + tempLists.get(inv.getInventoryId()) + " | " + (float)transItem.getQuantity());
            tempLists.put(inv.getInventoryId(), tempLists.get(inv.getInventoryId())+(float)transItem.getQuantity());
        }
        List<Report> excessReport = new ArrayList<Report>();
        Boolean foundExcess = false;
        for(Long invId : tempLists.keySet()) {
            Inventory inv = inventoryRepository.getReferenceById(invId);
            float percentage = 100 * tempLists.get(invId) / (tempLists.get(invId) + inv.getQuantity());
            if(percentage < 10.0) {
                excessReport.add(new Report(inv.getName(), percentage));
                foundExcess = true;
            }
        }
        if(foundExcess == true) {
            return excessReport;
        }
        else {
            return null;
        }
    }

    /**
    Generates a report of inventory that needs to be restocked.
    @return A List of Reports representing the inventory that needs to be restocked.
    */
    public List<Report> createRestockReport() {
        Integer inventoryCount = inventoryRepository.findInventoryCount();
        List<Report> restockReport = new ArrayList<Report>();
        for(int i = 1; i <= inventoryCount; i++) {
            Inventory tempInventory = inventoryRepository.getReferenceById((long)i);
            if(tempInventory.getRestockAmount() > tempInventory.getQuantity())
                restockReport.add(new Report(tempInventory.getName(), tempInventory.getQuantity()));
        }
        return restockReport;
    }

    // Server

    /**
    Returns a list of all categories of menu items in the menu.
    @return List of categories
    */
    public List<String> getCategories() {
        return menuItemRepository.findCategories();
    }

    /**
    Returns a list of all items in a specific category.
    @param category The category name
    @return List of items in the category
    */
    public List<String> getItemsInCategory(String category) {
        return menuItemRepository.findItemsInCategory(category);
    }

    /**
    Returns a list of all ingredients in a specific menu item.
    @param name The menu item name
    @return List of ingredients in the menu item
    */
    public List<String> getIngredientsInItem(String name) {
        List<String> ingList = new ArrayList<String>();
        List<Menu_Item> menuList = menuItemRepository.findByName(name);
        if(menuList.size() == 1) {
            Menu_Item menu = menuList.get(0);
            List<Integer> idList = ingredientListRepository.findInventoryByMenu((int)(menu.getMenuId()));
            for(Integer id : idList) {
                ingList.add(inventoryRepository.findNameByInventoryId(id).get(0));
            }
            return ingList;
        }
        else {
            System.out.println("menuListSize: " + menuList.size());
            return null;
        }
    }

    /**
    Returns a list of all valid ingredients in the inventory.
    @return List of valid ingredients
    */
    public List<String> getAllIngredients() {
        return inventoryRepository.findAllValidIngredients();
    }

    /**
    Returns a list of all valid inventory items.
    @return List of valid inventory items
    */
    public List<Inventory> getAllValidInventory() {
        return inventoryRepository.findAllValidInventory();
    }

    /**
    Returns the price of a specific menu item.
    @param name The menu item name
    @return The price of the menu item
    */
    public float getPriceofMenuItem(String name) {
        return menuItemRepository.findPriceByMenu(name).get(0);
    }    
}
