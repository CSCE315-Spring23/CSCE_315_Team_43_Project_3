package com.team43.project3.smook.service;

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
}