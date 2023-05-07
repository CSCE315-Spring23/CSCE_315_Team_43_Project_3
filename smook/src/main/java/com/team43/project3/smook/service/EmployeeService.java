package com.team43.project3.smook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team43.project3.smook.model.Employee;
import com.team43.project3.smook.repository.EmployeeRepository;
 
@Service
public class EmployeeService {
 
    @Autowired
    private EmployeeRepository repo;
     
    public void processOAuthPostLogin(String username) {
        Employee existUser = repo.getUserByUsername(username);
         
        if (existUser == null) {
            Employee newUser = new Employee(repo.findCurrentId()+1, "employee", username, Employee.Provider.GOOGLE);
             
            repo.save(newUser);        
        }
         
    }
     
}