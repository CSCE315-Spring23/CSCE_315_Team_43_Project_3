package com.team43.project3.smook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Menu_Item;

@Repository
public interface MenuItemRepository extends JpaRepository<Menu_Item, Long> {
    
}
