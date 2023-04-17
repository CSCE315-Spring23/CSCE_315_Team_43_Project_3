package com.team43.project3.smook.service;

import java.sql.Date;
import java.util.List;

import javax.swing.plaf.metal.MetalBorders.MenuItemBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team43.project3.smook.model.Employee;
import com.team43.project3.smook.model.Ingredient_List;
import com.team43.project3.smook.model.Inventory;
import com.team43.project3.smook.model.Menu_Item;
import com.team43.project3.smook.model.Order_Item;
import com.team43.project3.smook.model.Order_List;
import com.team43.project3.smook.model.Transaction;
import com.team43.project3.smook.model.Transaction_Item;
import com.team43.project3.smook.model.keys.Ingredient_List_Key;
import com.team43.project3.smook.repository.EmployeeRepository;
import com.team43.project3.smook.repository.IngredientListRepository;
import com.team43.project3.smook.repository.InventoryRepository;
import com.team43.project3.smook.repository.MenuItemRepository;
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

    public List<List<Integer>> testPairs() {
        Date start = Date.valueOf("2022-01-01");
        Date end = Date.valueOf("2022-05-08");
        List<List<Integer>> pairs = transactionItemRepository.findPairs(start, end);
        System.out.println(pairs);
        return pairs;
    }


    /*
     * Employee
     */
    public Integer login(String username, String password) {
        System.out.println("entered login");
        List<Employee> validLogins = employeeRepository.findByUsernameAndPassword(username, password);
        // return validLogins;
        if(validLogins.size() == 1) {
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

    public Inventory editInventoryItem(long inventoryId, String name, float price, float quantity, String measurement_type) {
        Inventory inv = inventoryRepository.getReferenceById(inventoryId);
        inv.setName(name);
        inv.setPrice(price);
        inv.setQuantity(quantity);
        inv.setMeasurementType(measurement_type);
        inventoryRepository.save(inv);
        return inv;
    }

    public Inventory addInventoryItem(long inventoryId, String name, float price, float quantity, String measurement_type) {
        Inventory inv = new Inventory(inventoryId, name, price, quantity, measurement_type);
        inventoryRepository.save(inv);
        return inv;
    }

    /*
     * Menu Item
     */
    public Menu_Item getMenuItem(long menuItemId) {
        return menuItemRepository.getReferenceById(menuItemId);
    }

    public Menu_Item editMenuItem(long menuItemId, String name, String type, float price, int ingredientAmount, List<Integer> ingredientIds, List<Integer> ingredientQuantity) {
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
                ingList.setInventoryId(ingredientIds.get(i));
                ingList.setQuantity(ingredientQuantity.get(i));
            }
            ingredientListRepository.save(ingList);
        }
        return item;
    }

    public Menu_Item addMenuItem(long menuItemId, String name, String type, float price, int ingredientAmount, List<Integer> ingredientIds, List<Integer> ingredientQuantity) {
        Menu_Item item = new Menu_Item(menuItemId, name, type, price, ingredientAmount);
        for(int i = 0; i < ingredientIds.size(); i++) {
            Ingredient_List ingList = new Ingredient_List(ingredientIds.get(i), menuItemId, ingredientQuantity.get(i));
            ingredientListRepository.save(ingList);
        }
        menuItemRepository.save(item);
        return item;
    }

    /*
     * 
     */
}
