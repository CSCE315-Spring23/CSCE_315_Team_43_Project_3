package com.team43.app.backend.manager;

public class OrderList {
    int inventory_id;
    int order_id;
    double quantity;

    public OrderList(int inventory_id, int order_id, double quantity){
        this.inventory_id = inventory_id;
        this.order_id = order_id;
        this.quantity = quantity;
    }
}
