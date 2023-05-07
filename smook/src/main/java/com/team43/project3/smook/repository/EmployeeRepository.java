package com.team43.project3.smook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT MAX(employee_id) FROM employee", nativeQuery = true)
    long findCurrentId();

    @Query("SELECT u FROM Employee u WHERE u.username = :username")
    Employee getUserByUsername(@Param("username") String username);
}
