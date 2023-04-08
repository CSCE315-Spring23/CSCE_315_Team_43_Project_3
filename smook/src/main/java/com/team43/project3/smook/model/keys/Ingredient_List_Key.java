package com.team43.project3.smook.model.keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ingredient_List_Key implements Serializable {
    long inventory_id;
    long menu_id;


    public long getInventory_id() {
        return this.inventory_id;
    }

    public void setInventory_id(long inventory_id) {
        this.inventory_id = inventory_id;
    }

    public long getMenu_id() {
        return this.menu_id;
    }

    public void setMenu_id(long menu_id) {
        this.menu_id = menu_id;
    }

    public Ingredient_List_Key() {
    }

    public Ingredient_List_Key(long inventoryId, long menuId) {
        this.inventory_id = inventoryId;
        this.menu_id = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ingredient_List_Key)) {
            return false;
        }
        Ingredient_List_Key ingredient_List_Key = (Ingredient_List_Key) o;
        return inventory_id == ingredient_List_Key.inventory_id && menu_id == ingredient_List_Key.menu_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory_id, menu_id);
    }

    @Override
    public String toString() {
        return "{" +
            " inventory_id='" + getInventory_id() + "'" +
            ", menu_id='" + getMenu_id() + "'" +
            "}";
    }
    
}
