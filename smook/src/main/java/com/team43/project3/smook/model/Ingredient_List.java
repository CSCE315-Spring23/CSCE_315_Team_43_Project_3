package com.team43.project3.smook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredient_list")
public class Ingredient_List {
    
    @Id
    @GeneratedValue
    @Column(name = "ingredient_list_id")
    long ingredientListId;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    long inventoryId;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    long menuId;

    @Column(name = "quantity")
    float quantity;


    public long getIngredientListId() {
        return this.ingredientListId;
    }

    public void setIngredientListId(long ingredientListId) {
        this.ingredientListId = ingredientListId;
    }

    public long getInventoryId() {
        return this.inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public long getMenuId() {
        return this.menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Ingredient_List(long ingredientListId, long inventoryId, long menuId, float quantity) {
        this.ingredientListId = ingredientListId;
        this.inventoryId = inventoryId;
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public Ingredient_List() {
    }

}
