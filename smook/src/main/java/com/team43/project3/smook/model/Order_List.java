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
import jakarta.persistence.criteria.Order;

@Entity
@Table(name = "order_list")
public class Order_List {
    
    @Id
    @Column(name = "order_list_id")
    long orderListId;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order_Item order;

    @Column(name = "quantity")
    float quantity;


    public long getOrderListId() {
        return this.orderListId;
    }

    public void setOrderListId(long orderListId) {
        this.orderListId = orderListId;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Order_Item getOrder() {
        return this.order;
    }

    public void setOrder(Order_Item order) {
        this.order = order;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


    public Order_List(long orderListId, Inventory inventory, Order_Item order, float quantity) {
        this.orderListId = orderListId;
        this.setInventory(inventory);
        this.setOrder(order);
        this.quantity = quantity;
    }

    public Order_List() {
    }

}
