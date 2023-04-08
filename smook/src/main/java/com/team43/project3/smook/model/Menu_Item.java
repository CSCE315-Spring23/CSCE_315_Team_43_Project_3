package com.team43.project3.smook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_item")
public class Menu_Item {
    
    @Id
    @Column(name = "menu_id")
    long menuId;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    String type;

    @Column(name = "price")
    float price;

    @Column(name = "ingredient_amount")
    long ingredientAmount;


    public long getMenuId() {
        return this.menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getIngredientAmount() {
        return this.ingredientAmount;
    }

    public void setIngredientAmount(long ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public Menu_Item(long menuId, String name, String type, float price, long ingredientAmount) {
        this.menuId = menuId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.ingredientAmount = ingredientAmount;
    }

    public Menu_Item() {
    }

}
