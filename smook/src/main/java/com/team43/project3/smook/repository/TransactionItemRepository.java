package com.team43.project3.smook.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Transaction;
import com.team43.project3.smook.model.Transaction_Item;

@Repository
public interface TransactionItemRepository extends JpaRepository<Transaction_Item, Long> {
    @Query(value = "SELECT O1.menu_id AS menu_id_1, O2.menu_id AS menu_id_2, COUNT(*) AS PurchaseFrequency FROM transaction_item AS O1 INNER JOIN transaction_item AS O2 ON O1.transaction_id = O2.transaction_id AND O1.menu_id < O2.menu_id AND O2.menu_id < 68 WHERE O1.transaction_id IN (SELECT transaction_id FROM transaction WHERE transaction.time_of_purchase BETWEEN CAST(?1 AS DATE) AND CAST(?2 AS DATE)) AND O2.transaction_id IN (SELECT transaction_id FROM transaction WHERE transaction.time_of_purchase BETWEEN CAST('2022-05-01' AS DATE) AND CAST('2022-10-05' AS DATE)) GROUP BY menu_id_1, menu_id_2 ORDER BY PurchaseFrequency DESC;", nativeQuery = true)
    List<List<Integer>> findPairs(Timestamp start, Timestamp end);

    @Query(value = "SELECT MAX(transaction_item_id) FROM transaction_item", nativeQuery = true)
    long findCurrentId();

    @Query(value = "SELECT * FROM transaction_item WHERE transaction_id BETWEEN ?1 AND ?2 ORDER BY transaction_id", nativeQuery = true)
    List<Transaction> findTransactionsInRange(long transId1, long transId2);
}
