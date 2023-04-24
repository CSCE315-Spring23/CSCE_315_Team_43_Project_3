package com.team43.project3.smook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    

    @Query(value = "SELECT name FROM inventory WHERE inventory_id = ?1", nativeQuery = true)
    List<String> findNameByInventoryId(Integer id);

    @Query(value = "SELECT name FROM inventory WHERE name NOT IN ('Small Cup', 'Medium Cup', 'Large Cup', 'Small Straw', 'Large Straw', 'Assorted Snacks', 'Dummy Item')", nativeQuery = true)
    List<String> findAllValidIngredients();

    @Query(value = "SELECT * FROM inventory WHERE name = ?1", nativeQuery = true)
    List<Inventory> findByName(String name);
}
