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

    public Inventory(long inventoryId, String name, float price, float quantity, String measurementType) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.measurementType = measurementType;
    }

    public Inventory() {
    }

}
