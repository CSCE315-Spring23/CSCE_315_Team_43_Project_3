package com.team43.project3.smook.model;

import com.team43.project3.smook.model.keys.Ingredient_List_Key;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredient_list")
public class Ingredient_List {

    @EmbeddedId
    Ingredient_List_Key ingredient_list_key;

    @ManyToOne
    @JoinColumn(name = "inventory_id", insertable=false, updatable=false)
    Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "menu_id", insertable=false, updatable=false)
    Menu_Item menu;

    @Column(name = "quantity")
    float quantity;


    public long getInventoryId() {
        return this.inventory.getInventoryId();
    }

    public void setInventoryId(long inventoryId) {
        this.inventory.setInventoryId(inventoryId);
    }

    public long getMenuId() {
        return this.menu.getMenuId();
    }

    public void setMenuId(long menuId) {
        this.menu.setMenuId(menuId);
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Ingredient_List(long inventoryId, long menuId, float quantity) {
        this.inventory.setInventoryId(inventoryId);
        this.menu.setMenuId(menuId);
        this.quantity = quantity;
    }

    public Ingredient_List() {
    }

}
