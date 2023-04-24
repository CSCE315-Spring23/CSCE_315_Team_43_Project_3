package com.team43.project3.smook.service;

import java.sql.Date;
import java.sql.Timestamp;


public class InventoryUsage {
    public Timestamp date;
    public Integer inventory_id;
    public Integer usage;

    public InventoryUsage(Timestamp date, Integer inventory_id, Integer usage){
        this.date = date;
        this.inventory_id = inventory_id;
        this.usage = usage;
    }
}
