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
    @Column(name = "transaction_item_id")
    long transactionItemId;

    @ManyToOne
    @JoinColumn(name = "menu_id", insertable=false, updatable=false)
    Menu_Item menu_item;

    @ManyToOne
    @JoinColumn(name = "transaction_id", insertable=false, updatable=false)
    Transaction transaction;


    public long getTransactionItemId() {
        return this.transactionItemId;
    }

    public void setTransactionItemId(long transactionItemId) {
        this.transactionItemId = transactionItemId;
    }

    public long getMenuId() {
        return this.menu_item.getMenuId();
    }

    public void setMenuId(long menuId) {
        this.menu_item.setMenuId(menuId);
    }

    public long getTransactionId() {
        return this.transaction.getTransaction_id();
    }

    public void setTransactionId(long transactionId) {
        this.transaction.setTransaction_id(transactionId);;
    }

    public Transaction_Item(long transactionItemId, long menuId, long transactionId) {
        this.transactionItemId = transactionItemId;
        this.menu_item.setMenuId(menuId); 
        this.transaction.setTransaction_id(transactionId);
    }

    public Transaction_Item() {
    }

}
