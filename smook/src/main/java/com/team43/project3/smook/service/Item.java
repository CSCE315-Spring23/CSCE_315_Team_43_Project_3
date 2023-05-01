package com.team43.project3.smook.service;

import java.io.Serializable;

import org.springframework.stereotype.Component;

// @Component
public class Item implements Serializable {
    Long id;
    String name;
    String type;
    float price;
    int quantitySold = 0;

    public Item() {}

    public Item(Long id, String name, String type, float price){
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Item(Long id, String name, String type, float price, Integer quantitySold){
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantitySold = quantitySold;
    }

    public void incrementQuantity() {
        quantitySold++;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + type + "," + price + "," + quantitySold;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getQuantitySold() {
        return this.quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

}