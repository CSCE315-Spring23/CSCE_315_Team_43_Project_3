package com.team43.project3.smook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Ingredient_List;
import com.team43.project3.smook.model.keys.Ingredient_List_Key;

@Repository
public interface IngredientListRepository extends JpaRepository<Ingredient_List, Ingredient_List_Key> {
    
}
