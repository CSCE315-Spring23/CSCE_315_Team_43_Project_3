package com.team43.project3.smook.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Menu_Item;

@Repository
public interface MenuItemRepository extends JpaRepository<Menu_Item, Long> {
    @Query(value = "SELECT DISTINCT type FROM menu_item", nativeQuery = true)
    List<String> findCategories();

    @Query(value = "SELECT name FROM menu_item WHERE type=?1", nativeQuery = true)
    List<String> findItemsInCategory(String category);

    @Query(value = "SELECT name FROM menu_item WHERE name NOT IN ('Small Cup', 'Medium Cup', 'Large Cup', 'Small Straw', 'Large Straw', 'Assorted Snacks', 'Dummy Item')", nativeQuery = true)
    List<String> findIngredientsBySmoothie(String name);

    @Query(value = "SELECT price FROM menu_item WHERE name = ?1", nativeQuery = true)
    List<Float> findPriceByMenu(String name);

    @Query(value = "SELECT MAX(menu_item_id) FROM menu_item", nativeQuery = true)
    long findCurrentId();

    List<Menu_Item> findByName(String name);
}
