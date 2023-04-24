package com.team43.project3.smook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class Transaction {
    
    @Id
    @Column(name = "transaction_id")
    long transaction_id;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    Employee employee;

    @Column(name = "purchaser_name")
    String purchaserName;

    @Column(name = "price_of_transaction")
    float priceOfTransaction;

    @Column(name = "time_of_purchase")
    Timestamp timeOfPurchase;


    public long getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(long transactionId) {
        this.transaction_id = transactionId;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPurchaserName() {
        return this.purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public float getPriceOfTransaction() {
        return this.priceOfTransaction;
    }

    public void setPriceOfTransaction(float priceOfTransaction) {
        this.priceOfTransaction = priceOfTransaction;
    }

    public Timestamp getTimeOfPurchase() {
        return this.timeOfPurchase;
    }

    public void setTimeOfPurchase(Timestamp timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public Transaction(long transactionId, Employee employee, String purchaserName, float priceOfTransaction, Timestamp timeOfPurchase) {
        this.transaction_id = transactionId;
        this.employee = employee;
        this.purchaserName = purchaserName;
        this.priceOfTransaction = priceOfTransaction;
        this.timeOfPurchase = timeOfPurchase;
    }

    public Transaction() {
    }

}
