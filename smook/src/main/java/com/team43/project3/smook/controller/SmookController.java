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
import com.team43.project3.smook.model.Transaction;
import com.team43.project3.smook.service.Report;
import com.team43.project3.smook.service.Item;
import com.team43.project3.smook.service.SmookServiceImpl;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping(value = "/transaction")
    @ResponseBody
    public void testTransaction(@RequestParam Integer smoothieQuantity, @RequestParam long employeeId, @RequestParam String name, @RequestParam List<String> size, @RequestParam float price, @RequestParam List<String> smoothieName, @RequestParam List<Integer> numIngredients, @RequestParam List<String> ingredientName, @RequestParam List<Integer> itemQuantity) {
        System.out.println("starting test transaction");
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
    
        System.out.println("start adding transaction");
        Transaction temp = loveGameService.addTransaction(employeeId, name, price, smoothieName, itemList, sizeList);
        System.out.println(temp);
    }

    @RequestMapping(value = "/salesReport", method = RequestMethod.GET)
    @ResponseBody
    public List<?> testSalesReport()
    {
        Date now = new Date();
        Date then = new Date(0);
        Timestamp start = new Timestamp(then.getTime()); //this is 1 hour ago
        Timestamp end = new Timestamp(now.getTime());
        return loveGameService.createSalesReport(start, end);
    }

    @RequestMapping(value = "/XReport", method = RequestMethod.GET)
    @ResponseBody
    public List<?> testXReport()
    {
        List<?> tempList = loveGameService.createXReport();
        return tempList;
    }

    @RequestMapping(value = "/ZReport", method = RequestMethod.GET)
    @ResponseBody
    public List<?> testZReport()
    {
        List<?> tempList = loveGameService.createZReport();
        return tempList;
    }

    @RequestMapping(value = "/ExcessReport", method = RequestMethod.GET)
    @ResponseBody
    public List<Report> testExcessReport()
    {
        Date now = new Date();
        Timestamp start = new Timestamp(now.getTime()-999999999); //this is 1 hour ago
        Timestamp end = new Timestamp(now.getTime());
        List<Report> tempList = loveGameService.createExcessReport(start, end);
        return tempList;
    }

    @RequestMapping(value = "/RestockReport", method = RequestMethod.GET)
    @ResponseBody
    public List<Report> testRestockReport()
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

    // @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    // @ResponseBody
    // public void receiveTransaction(@RequestBody Map<String, Object> payload)
    // {
        
    // }

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
    public Menu_Item addMenu_Item(String name, String type, float price, int ingredientAmount, List<Integer> ingredientIds, List<Integer> ingredientQuantity)
    {
        return loveGameService.addMenuItem(name, type, price, ingredientAmount, ingredientIds, ingredientQuantity);
    }

    @RequestMapping(value = "/addInventory", method = RequestMethod.POST)
    @ResponseBody
    public Inventory addInventory(String name, float price, float quantity, String measurement_type)
    {
        return loveGameService.addInventoryItem(name, price, quantity, measurement_type);
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.POST)
    @ResponseBody
    public Menu_Item editMenu_Item(long menuItemId, String name, String type, float price, int ingredientAmount, List<Long> ingredientIds, List<Long> ingredientQuantity)
    {
        return loveGameService.editMenuItem(menuItemId, name, type, price, ingredientAmount, ingredientIds, ingredientQuantity);
    }

    @RequestMapping(value = "/editInventory", method = RequestMethod.POST)
    @ResponseBody
    public Inventory editInventory(long inventoryId, String name, float price, float quantity, String measurement_type, Integer restockAmount)
    {
        return loveGameService.editInventoryItem(inventoryId, name, price, quantity, measurement_type, restockAmount);
    }
}
