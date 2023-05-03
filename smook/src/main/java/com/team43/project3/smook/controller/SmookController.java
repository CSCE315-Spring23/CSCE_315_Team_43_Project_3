package com.team43.project3.smook.controller;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.team43.project3.smook.model.Employee;
import com.team43.project3.smook.model.Inventory;
import com.team43.project3.smook.model.Menu_Item;
import com.team43.project3.smook.model.Order_Item;
import com.team43.project3.smook.model.Transaction;
import com.team43.project3.smook.service.Report;
import com.team43.project3.smook.service.Item;
import com.team43.project3.smook.service.SmookServiceImpl;

@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class SmookController {


    @Autowired
    private SmookServiceImpl loveGameService;

    /*
     * Test Functions
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public void testDB()
    {
        loveGameService.testDBConnection();
    }

    @RequestMapping(value = "/salesReport", method = RequestMethod.GET)
    @ResponseBody
    public List<?> createSalesReport(@RequestParam String startTime, @RequestParam String endTime)
    {
        Timestamp start = Timestamp.valueOf(startTime); 
        Timestamp end = Timestamp.valueOf(endTime);
        return loveGameService.createSalesReport(start, end);
    }

    @RequestMapping(value = "/XReport", method = RequestMethod.GET)
    @ResponseBody
    public List<?> createXReport()
    {
        List<?> tempList = loveGameService.createXReport();
        return tempList;
    }

    @RequestMapping(value = "/ZReport", method = RequestMethod.GET)
    @ResponseBody
    public List<?> createZReport()
    {
        List<?> tempList = loveGameService.createZReport();
        return tempList;
    }

    @RequestMapping(value = "/ExcessReport", method = RequestMethod.GET)
    @ResponseBody
    public List<Report> createExcessReport(@RequestParam String startTime, @RequestParam String endTime)
    {
        Timestamp start = Timestamp.valueOf(startTime); 
        Timestamp end = Timestamp.valueOf(endTime);
        List<Report> tempList = loveGameService.createExcessReport(start, end);
        return tempList;
    }

    @RequestMapping(value = "/RestockReport", method = RequestMethod.GET)
    @ResponseBody
    public List<Report> createRestockReport()
    {
        List<Report> tempList = loveGameService.createRestockReport();
        return tempList;
    }

    /*
     * Employee
     */

     //returns 0 if not found, 1 if manager, 2 if employee
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Integer loginVerify(@RequestParam String username, @RequestParam String password){
        System.out.println("entered controller");
        return loveGameService.login(username, password);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public List<String> sendCategories()
    {
        return loveGameService.getCategories();
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<String> sendItemsInCategory(@RequestParam String category)
    {
        return loveGameService.getItemsInCategory(category);
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    @ResponseBody
    public List<String> sendIngredientsInItem(@RequestParam String name)
    {
        return loveGameService.getIngredientsInItem(name);
    }

    @RequestMapping(value = "/allingredients", method = RequestMethod.GET)
    @ResponseBody
    public List<String> sendAllIngredients()
    {
        return loveGameService.getAllIngredients();
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET)
    @ResponseBody
    public float sendPriceOfItem(@RequestParam String name)
    {
        return loveGameService.getPriceofMenuItem(name);
    }

    /*
     * manager
     */
    @RequestMapping(value = "/validInventory", method = RequestMethod.GET)
    @ResponseBody
    public List<Inventory> sendValidInventory()
    {
        return loveGameService.getAllValidInventory();
    }

    @RequestMapping(value = "/menu_items", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu_Item> sendMenu_Items()
    {
        return loveGameService.viewMenuItems();
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    @ResponseBody
    public Menu_Item addMenu_Item(@RequestParam String name, @RequestParam String type, @RequestParam float price, @RequestParam int ingredientAmount, @RequestParam List<Integer> ingredientIds, @RequestParam List<Integer> ingredientQuantity)
    {
        return loveGameService.addMenuItem(name, type, price, ingredientAmount, ingredientIds, ingredientQuantity);
    }

    @RequestMapping(value = "/addInventory", method = RequestMethod.POST)
    @ResponseBody
    public Inventory addInventory(@RequestParam String name, @RequestParam float price, @RequestParam float quantity, @RequestParam String measurement_type)
    {
        return loveGameService.addInventoryItem(name, price, quantity, measurement_type);
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public Order_Item addOrder(@RequestParam Float cost, @RequestParam List<String> invList, @RequestParam List<Integer> quantity)
    {
        return loveGameService.addOrderItem(cost, invList, quantity);
    }

    @PostMapping(value = "/addTransaction")
    @ResponseBody
    public void addTransaction(@RequestParam Integer smoothieQuantity, @RequestParam long employeeId, @RequestParam String name, @RequestParam List<String> size, @RequestParam float price, @RequestParam List<String> smoothieName, @RequestParam List<Integer> numIngredients, @RequestParam List<String> ingredientName, @RequestParam List<Integer> itemQuantity) {
        List<Inventory> itemList = new ArrayList<Inventory>();
        List<Integer> sizeList = new ArrayList<Integer>();
        Integer start = 0;
        Integer end = numIngredients.get(0);
        for(int j = 0; j < smoothieQuantity; j++) {
            for(int i = start; i < end; i++) {
                Inventory item = loveGameService.getInventoryItemByName(ingredientName.get(i));
                itemList.add(item);
                if(size.get(j).equals("small")){
                    sizeList.add(itemQuantity.get(i));
                }
                else if(size.get(j).equals("medium")){
                    sizeList.add(2*itemQuantity.get(i));
                }
                else {
                    sizeList.add(3*itemQuantity.get(i));
                }
            }
            if(j+1 < smoothieQuantity) {
                start = end;
                end += numIngredients.get(j+1);
            }
        }
    
        Transaction temp = loveGameService.addTransaction(employeeId, name, price, smoothieName, itemList, sizeList);
        System.out.println(temp);
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.POST)
    @ResponseBody
    public Menu_Item editMenu_Item(@RequestParam long menuItemId, @RequestParam String name, @RequestParam String type, @RequestParam float price, @RequestParam int ingredientAmount, @RequestParam List<Long> ingredientIds, @RequestParam List<Long> ingredientQuantity)
    {
        return loveGameService.editMenuItem(menuItemId, name, type, price, ingredientAmount, ingredientIds, ingredientQuantity);
    }

    @RequestMapping(value = "/editInventory", method = RequestMethod.POST)
    @ResponseBody
    public Inventory editInventory(@RequestParam long inventoryId, @RequestParam String name, @RequestParam float price, @RequestParam float quantity, @RequestParam String measurement_type, @RequestParam Integer restockAmount)
    {
        return loveGameService.editInventoryItem(inventoryId, name, price, quantity, measurement_type, restockAmount);
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    @ResponseBody
    public Order_Item editOrder(@RequestParam Long id, @RequestParam Float cost, @RequestParam List<String> invList, @RequestParam List<Integer> quantity)
    {
        return loveGameService.editOrderItem(id, cost, invList, quantity);
    }
}
