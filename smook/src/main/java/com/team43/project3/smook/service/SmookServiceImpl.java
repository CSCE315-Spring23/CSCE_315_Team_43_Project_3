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

    // public List<List<Integer>> testPairs() {
    //     Date start = Date.valueOf("2022-01-01");
    //     Date end = Date.valueOf("2022-05-08");
    //     List<List<Integer>> pairs = transactionItemRepository.findPairs(start, end);
    //     System.out.println(pairs);
    //     return pairs;
    // }
    /*
     * Employee
     */
    public Integer login(String username, String password) {
        System.out.println("entered login");
        List<Employee> validLogins = employeeRepository.findByUsernameAndPassword(username, password);
        // return validLogins;
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

    /*
     * View Tables
     */
    public List<Inventory> viewInventory() {
        return inventoryRepository.findAll();
    } 

    public List<Menu_Item> viewMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<Order_Item> viewOrderItems() {
        return orderItemRepository.findAll();
    }

    /*
     * Inventory
     */
    public Inventory getInventoryItem(long inventoryId) {
        return inventoryRepository.getReferenceById(inventoryId);
    }

    public Inventory getInventoryItemByName(String name) {
        List<Inventory> inventory = inventoryRepository.findByName(name);
        System.out.println(inventory);
        return inventory.get(0);
    }

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

    public Inventory addInventoryItem(String name, float price, float quantity, String measurement_type) {
        Inventory inv = new Inventory(inventoryRepository.findCurrentId()+1, name, price, quantity, measurement_type);
        inventoryRepository.save(inv);
        return inv;
    }

    /*
     * Menu Item
     */
    public Menu_Item getMenuItem(long menuItemId) {
        return menuItemRepository.getReferenceById(menuItemId);
    }

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

    public Menu_Item addMenuItem(String name, String type, float price, int ingredientAmount, List<Integer> ingredientIds, List<Integer> ingredientQuantity) {
        long tempId = menuItemRepository.findCurrentId()+1;
        Menu_Item item = new Menu_Item(tempId, name, type, price, ingredientAmount);
        for(int i = 0; i < ingredientIds.size(); i++) {
            Ingredient_List ingList = new Ingredient_List(ingredientIds.get(i), tempId, ingredientQuantity.get(i));
            ingredientListRepository.save(ingList);
        }
        menuItemRepository.save(item);
        return item;
    }

    /*
     * Transaction
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

    /*
     * Reports
     */
    //name, type, price, quantitySold
    public List<Item> createSalesReport(Timestamp start, Timestamp end) {
        List<Item> tempReport = new ArrayList<Item>();
        Integer menuItemAmount = menuItemRepository.findMenuCount();
        for(int i = 0; i < menuItemAmount; i++) {
            Menu_Item menu_item = menuItemRepository.getReferenceById((long)i);
            tempReport.add(new Item(menu_item.getMenuId(), menu_item.getName(), menu_item.getType(), menu_item.getPrice()));
        }
        // long startId = transactionRepository.findMinIdInTime(start, end);
        // long endId = transactionRepository.findMaxIdInTime(start, end);
        // List<Menu_Tracker> trackers = menuTrackerRepository.findBetweenTransactionIds(startId, endId);
        List<Menu_Tracker> trackers = menuTrackerRepository.findBetweenTimes(start, end);
        for(Menu_Tracker tracker : trackers) {
            Menu_Item menu_item = tracker.getMenu();
            tempReport.get((int)menu_item.getMenuId()).incrementQuantity();
        }
        // List<String> salesReport = new ArrayList<String>();
        // List<Object> salesReport = new ArrayList<Object>();
        // for(int i = 0; i < menuItemAmount; i++) {
            // salesReport.add(tempReport.get(i).toString());
            // salesReport.add((Object)tempReport.get(i));
        // }
        return tempReport;
        //fill list with transaction_items or menu_id + amount pairs
        //increment inventory amounts in upstream table for tracking
    }

    public List<Item> createXReport() {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        return createSalesReport(start, now);
    }

    public List<Item> createZReport() {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        List<Item> zReport = createSalesReport(start, now);
        setTime(now);
        return zReport;
    }

    public List<Excess> createExcessReport(Timestamp start, Timestamp end) {
        Integer inventoryCount = inventoryRepository.findInventoryCount();
        Map<Inventory, Float> tempLists = new HashMap<Inventory, Float>(inventoryCount);
        for(int i = 1; i <= inventoryCount; i++) {
            Inventory tempInventory = inventoryRepository.getReferenceById((long)i);
            tempLists.put(tempInventory, 0f);
        }
        Long maxId = transactionRepository.findMaxIdInTime(start, end);
        Long minId = transactionRepository.findMinIdInTime(start, end);
        List<Transaction_Item> transItemList = transactionItemRepository.findTransactionsInRange(minId, maxId);
        for(Transaction_Item transItem : transItemList) {
            Inventory inv = transItem.getInventory();
            tempLists.put(inv, tempLists.get(inv)+transItem.getQuantity());
        }
        List<Excess> excessReport = new ArrayList<Excess>();
        Boolean foundExcess = false;
        for(Inventory inv : tempLists.keySet()) {
            float percentage = 100 * tempLists.get(inv) / (tempLists.get(inv) + inv.getQuantity());
            if(percentage < 10.0) {
                excessReport.add(new Excess(inv.getName(), percentage));
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

    public Map<String, Float> createRestockReport() {
        Integer inventoryCount = inventoryRepository.findInventoryCount();
        Map<String, Float> restockReport = new HashMap<String, Float>();
        for(int i = 1; i <= inventoryCount; i++) {
            Inventory tempInventory = inventoryRepository.getReferenceById((long)i);
            if(tempInventory.getRestockAmount() > tempInventory.getQuantity())
                restockReport.put(tempInventory.getName(), tempInventory.getQuantity());
        }
        return restockReport;
    }

    public void restockInventoryItem(String name, Integer amount) {
        Inventory inv = inventoryRepository.findByName(name).get(0);
        inv.setQuantity(amount);
        inventoryRepository.save(inv);
    }

    /*
     * Server
     */
    public List<String> getCategories() {
        return menuItemRepository.findCategories();
    }

    public List<String> getItemsInCategory(String category) {
        return menuItemRepository.findItemsInCategory(category);
    }

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

    public List<String> getAllIngredients() {
        return inventoryRepository.findAllValidIngredients();
    }

    public List<Inventory> getAllValidInventory() {
        return inventoryRepository.findAllValidInventory();
    }

    public float getPriceofMenuItem(String name) {
        return menuItemRepository.findPriceByMenu(name).get(0);
    }

    // public void pushTransaction()

    /*
     * manager
     */

    
}
