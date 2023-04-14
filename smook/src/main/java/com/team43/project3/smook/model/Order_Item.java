package com.team43.project3.smook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "order_item")
public class Order_Item {

    @Id
    @Column(name = "order_id")
    long orderId;

    @Column(name = "date_placed", nullable = false)
    Date datePlaced;

    @Column(name = "cost", nullable = false)
    float cost;


    public long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(long order_id) {
        this.orderId = order_id;
    }

    public Date getDatePlaced() {
        return this.datePlaced;
    }

    public void setDatePlaced(Date date_placed) {
        this.datePlaced = date_placed;
    }

    public float getCost() {
        return this.cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Order_Item(long orderId, Date datePlaced, float cost) {
        this.orderId = orderId;
        this.datePlaced = datePlaced;
        this.cost = cost;
    }

    public Order_Item() {
    }

}