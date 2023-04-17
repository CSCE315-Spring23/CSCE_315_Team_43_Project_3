package com.team43.project3.smook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @ResponseBody
    public void testDB()
    {
        loveGameService.testDBConnection();
    }

    @RequestMapping(value = "/pairs", method = RequestMethod.GET)
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
    @ResponseBody
    public Integer loginVerify(@RequestParam String username, @RequestParam String password){
        System.out.println("entered controller");
        return loveGameService.login(username, password);
    }
}
