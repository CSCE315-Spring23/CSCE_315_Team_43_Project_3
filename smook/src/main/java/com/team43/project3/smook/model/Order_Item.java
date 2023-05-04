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
import java.sql.Timestamp;

@Entity
@Table(name = "order_item")
public class Order_Item {

    @Id
    @Column(name = "order_id")
    long orderId;

    @Column(name = "date_placed", nullable = false)
    Timestamp datePlaced;


    public long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(long order_id) {
        this.orderId = order_id;
    }

    public Timestamp getDatePlaced() {
        return this.datePlaced;
    }

    public void setDatePlaced(Timestamp date_placed) {
        this.datePlaced = date_placed;
    }

    public Order_Item(long orderId, Timestamp datePlaced) {
        this.orderId = orderId;
        this.datePlaced = datePlaced;
    }

    public Order_Item() {
    }

}
