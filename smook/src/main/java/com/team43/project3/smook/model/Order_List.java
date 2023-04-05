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
@Table(name = "order_list")
public class Order_List {
    
    @Id
    @GeneratedValue
    @Column(name = "order_list_id")
    long orderListId;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    long inventoryId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    long orderId;

    @Column(name = "quantity")
    float quantity;


    public long getOrderListId() {
        return this.orderListId;
    }

    public void setOrderListId(long orderListId) {
        this.orderListId = orderListId;
    }

    public long getInventoryId() {
        return this.inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


    public Order_List(long orderListId, long inventoryId, long orderId, float quantity) {
        this.orderListId = orderListId;
        this.inventoryId = inventoryId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Order_List() {
    }

}
