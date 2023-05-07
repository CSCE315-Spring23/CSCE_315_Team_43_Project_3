package com.team43.project3.smook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    

    @Query(value = "SELECT name FROM inventory WHERE inventory_id = ?1 ORDER BY name ASC", nativeQuery = true)
    List<String> findNameByInventoryId(Integer id);

    @Query(value = "SELECT name FROM inventory WHERE name NOT IN ('Small Cup', 'Medium Cup', 'Large Cup', 'Small Straw', 'Large Straw', 'Assorted Snacks', 'Dummy Item') ORDER BY name ASC", nativeQuery = true)
    List<String> findAllValidIngredients();

    @Query(value = "SELECT * FROM inventory WHERE name NOT IN ('Small Cup', 'Medium Cup', 'Large Cup', 'Small Straw', 'Large Straw', 'Assorted Snacks', 'Dummy Item') ORDER BY inventory_id ASC", nativeQuery = true)
    List<Inventory> findAllValidInventory();

    @Query(value = "SELECT * FROM inventory WHERE name = ?1 ORDER BY inventory_id ASC", nativeQuery = true)
    List<Inventory> findByName(String name);

    @Query(value = "SELECT MAX(inventory_id) FROM inventory", nativeQuery = true)
    long findCurrentId();

    @Query(value = "SELECT COUNT(*) FROM inventory WHERE name NOT IN ('Small Cup', 'Medium Cup', 'Large Cup', 'Small Straw', 'Large Straw', 'Dummy Item')", nativeQuery = true)
    Integer findInventoryCount();
}
