package com.team43.project3.smook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.team43.project3.smook.service.SmookServiceImpl;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class SmookController {
    @Autowired
    private SmookServiceImpl loveGameService;

    @RequestMapping(value = "/smook/test", method = RequestMethod.POST)
    @ResponseBody
    public void testDB()
    {
        loveGameService.testDBConnection();
    }

    @RequestMapping(value = "/smook/pairs", method = RequestMethod.GET)
    @ResponseBody
    public void testPairFunction()
    {
        loveGameService.testPairs();
    }
}
