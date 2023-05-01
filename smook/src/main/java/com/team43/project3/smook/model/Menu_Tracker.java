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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "menu_tracker")
public class Menu_Tracker {
    @Id
    @Column(name = "menu_tracker_id")
    long menu_tracker_id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    Menu_Item menu;

    @Column(name = "time")
    Timestamp time;



    public Menu_Tracker() {
    }

    public Menu_Tracker(long menu_tracker_id, Transaction transaction, Menu_Item menu, Timestamp time) {
        this.menu_tracker_id = menu_tracker_id;
        this.transaction = transaction;
        this.menu = menu;
        this.time = time;
    }

    public long getMenu_tracker_id() {
        return this.menu_tracker_id;
    }

    public void setMenu_tracker_id(long menu_tracker_id) {
        this.menu_tracker_id = menu_tracker_id;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Menu_Item getMenu() {
        return this.menu;
    }

    public void setMenu(Menu_Item menu) {
        this.menu = menu;
    }

    public Timestamp getTimestamp() {
        return this.time;
    }

    public void setTimestamp(Timestamp time) {
        this.time = time;
    }
}
