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

@Entity
@Table(name = "transaction")
public class Transaction {
    
    @Id
    @Column(name = "transaction_id")
    long transaction_id;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false, insertable=false, updatable=false)
    Employee employee;

    @Column(name = "purchaser_name")
    String purchaserName;

    @Column(name = "price_of_transaction")
    float priceOfTransaction;

    @Column(name = "time_of_purchase")
    Date timeOfPurchase;


    public long getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(long transactionId) {
        this.transaction_id = transactionId;
    }

    public long getEmployeeId() {
        return this.employee.getEmployee_id();
    }

    public void setEmployeeId(long employeeId) {
        this.employee.setEmployee_id(employeeId);;
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

    public Date getTimeOfPurchase() {
        return this.timeOfPurchase;
    }

    public void setTimeOfPurchase(Date timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public Transaction(long transactionId, long employeeId, String purchaserName, float priceOfTransaction, Date timeOfPurchase) {
        this.transaction_id = transactionId;
        this.employee.setEmployee_id(employeeId);
        this.purchaserName = purchaserName;
        this.priceOfTransaction = priceOfTransaction;
        this.timeOfPurchase = timeOfPurchase;
    }

    public Transaction() {
    }

}
