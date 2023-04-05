package com.team43.project3.smook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Order_Item;

@Repository
public interface OrderItemRepository extends JpaRepository<Order_Item, longeger> {
    
}
