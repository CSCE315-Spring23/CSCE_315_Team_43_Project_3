package com.team43.project3.smook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Order_List;

@Repository
public interface OrderListRepository extends JpaRepository<Order_List, longeger> {
    
}
