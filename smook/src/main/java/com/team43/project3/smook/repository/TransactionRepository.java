package com.team43.project3.smook.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query(value = "SELECT MAX(transaction_id) FROM transaction", nativeQuery = true)
    long findCurrentId();

    @Query(value = "SELECT MAX(transaction_id) FROM transaction WHERE time_of_purchase BETWEEN ?1 AND ?2", nativeQuery = true)
    long findMaxIdInTime(Timestamp start, Timestamp end);

    @Query(value = "SELECT MIN(transaction_id) FROM transaction WHERE time_of_purchase BETWEEN ?1 AND ?2", nativeQuery = true)
    long findMinIdInTime(Timestamp start, Timestamp end);
}
