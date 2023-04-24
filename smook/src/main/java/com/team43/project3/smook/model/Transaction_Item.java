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
    @JoinColumn(name = "inventory_id")
    Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    Transaction transaction;

    @Column(name = "quantity")
    int quantity;


    public long getTransactionItemId() {
        return this.transactionItemId;
    }

    public void setTransactionItemId(long transactionItemId) {
        this.transactionItemId = transactionItemId;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Transaction_Item(long transactionItemId, Inventory inventory, Transaction transaction, int quantity) {
        this.transactionItemId = transactionItemId;
        this.setInventory(inventory);
        this.setTransaction(transaction);
        this.quantity = quantity;
    }

    public Transaction_Item() {
    }

}
