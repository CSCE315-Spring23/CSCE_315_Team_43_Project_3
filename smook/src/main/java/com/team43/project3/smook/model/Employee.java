package com.team43.project3.smook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
    public enum Provider {
        LOCAL, GOOGLE
    }

    @Id
    @Column(name = "employee_id")
    long employee_id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "role")
    String role;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private Provider provider;


    public long getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(long employeeId) {
        this.employee_id = employeeId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Provider getProvider() {
        return provider;
    }
 
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Employee(long employeeId, String role, String username, Employee.Provider provider) {
        this.employee_id = employeeId;
        this.firstName = null;
        this.lastName = null;
        this.role = role;
        this.username = username;
        this.password = null;
        this.provider = provider;
    }

    public Employee(long employeeId, String firstName, String lastName, String role, String username, String password, Employee.Provider provider) {
        this.employee_id = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.password = password;
        this.provider = provider;
    }

    public Employee() {
    }

}
