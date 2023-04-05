package com.team43.project3.smook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name = "transaction_item")
public class Transaction_Item {
    
    @Id
    @GeneratedValue
    @Column(name = "transaction_item_id")
    long transactionItemId;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    long menuId;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    long transactionId;


    public long getTransactionItemId() {
        return this.transactionItemId;
    }

    public void setTransactionItemId(long transactionItemId) {
        this.transactionItemId = transactionItemId;
    }

    public long getMenuId() {
        return this.menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Transaction_Item(long transactionItemId, long menuId, long transactionId) {
        this.transactionItemId = transactionItemId;
        this.menuId = menuId;
        this.transactionId = transactionId;
    }

    public Transaction_Item() {
    }

}
