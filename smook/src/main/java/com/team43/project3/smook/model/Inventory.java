package com.team43.project3.smook.model;

import org.hibernate.annotations.Mutability;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "inventory")
public class Inventory {
    
    @Id
    @Column(name = "inventory_id")
    long inventoryId;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    float price;

    @Column(name = "quantity")
    float quantity;

    @Column(name = "measurement_type")
    String measurementType;

    @Column(name = "restock_amount")
    Integer restockAmount;

    public Inventory() {
    }

    public Inventory(long inventoryId, String name, float price, float quantity, String measurementType) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.measurementType = measurementType;
        this.restockAmount = 100;
    }

    public long getInventoryId() {
        return this.inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasurementType() {
        return this.measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public Integer getRestockAmount() {
        return this.restockAmount;
    }

    public void setRestockAmount(Integer restockAmount) {
        this.restockAmount = restockAmount;
    }

    @Override
    public String toString() {
        return "{" +
            " inventoryId='" + getInventoryId() + "'" +
            ", name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", measurementType='" + getMeasurementType() + "'" +
            ", restockAmount='" + getRestockAmount() + "'" +
            "}";
    }


}
