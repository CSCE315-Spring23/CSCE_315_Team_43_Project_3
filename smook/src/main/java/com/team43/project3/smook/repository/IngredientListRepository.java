package com.team43.project3.smook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Ingredient_List;
import com.team43.project3.smook.model.Inventory;
import com.team43.project3.smook.model.Menu_Item;
import com.team43.project3.smook.model.keys.Ingredient_List_Key;

@Repository
public interface IngredientListRepository extends JpaRepository<Ingredient_List, Ingredient_List_Key> {
    

    //returns ingredientlist entires for some reason???????
    @Query(value = "SELECT inventory_id FROM ingredient_list WHERE menu_id = ?1", nativeQuery = true)
    List<Integer> findInventoryByMenu(Integer menuId);
}
