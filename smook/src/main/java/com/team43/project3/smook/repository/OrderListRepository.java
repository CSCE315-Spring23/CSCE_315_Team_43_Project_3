package com.team43.project3.smook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Order_List;

@Repository
public interface OrderListRepository extends JpaRepository<Order_List, Long> {
    @Query(value = "SELECT MAX(order_list_id) FROM order_list", nativeQuery = true)
    long findCurrentId();

    @Query(value = "SELECT * FROM order_list WHERE order_id = ?1", nativeQuery = true)
    List<Order_List> findByOrderId(Long order_id);
}
