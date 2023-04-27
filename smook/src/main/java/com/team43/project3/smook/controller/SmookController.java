package com.team43.project3.smook.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.team43.project3.smook.model.Employee;
import com.team43.project3.smook.service.SmookServiceImpl;

@RequestMapping("/")
@RestController
public class SmookController {
    @Autowired
    private SmookServiceImpl loveGameService;

    /*
     * Test Functions
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public void testDB()
    {
        loveGameService.testDBConnection();
    }

    @RequestMapping(value = "/pairs", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public List<List<Integer>> testPairFunction()
    {
        return loveGameService.testPairs();
    }

    /*
     * Employee
     */

     //returns 0 if not found, 1 if manager, 2 if employee
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public Integer loginVerify(@RequestParam String username, @RequestParam String password){
        System.out.println("entered controller");
        return loveGameService.login(username, password);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public List<String> sendCategories()
    {
        return loveGameService.getCategories();
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public List<String> sendItemsInCategory(@RequestParam String category)
    {
        return loveGameService.getItemsInCategory(category);
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public List<String> sendIngredientsInItem(@RequestParam String name)
    {
        return loveGameService.getIngredientsInItem(name);
    }

    @RequestMapping(value = "/allingredients", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public List<String> sendAllIngredients()
    {
        return loveGameService.getAllIngredients();
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public float sendPriceOfItem(@RequestParam String name)
    {
        return loveGameService.getPriceofMenuItem(name);
    }
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:5173/")
    @ResponseBody
    public boolean receiveTransaction(@RequestParam String customerName, @RequestParam int smoothieCount, @RequestParam List<String> smootheNames, @RequestParam List<String> ingredientLists){
        System.out.println(customerName);
        return true;
    }
    // @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    // @ResponseBody
    // public void receiveTransaction(@RequestBody Map<String, Object> payload)
    // {
        
    // }
}
