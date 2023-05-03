package com.team43.project3.smook.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team43.project3.smook.model.Menu_Tracker;

public interface MenuTrackerRepository extends JpaRepository<Menu_Tracker, Long> {
    @Query(value = "SELECT MAX(menu_tracker_id) FROM menu_tracker", nativeQuery = true)
    long findCurrentId();

    @Query(value = "SELECT * FROM menu_tracker WHERE transaction_id BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Menu_Tracker> findBetweenTransactionIds(long id1, long id2);

    @Query(value = "SELECT * FROM menu_tracker WHERE time BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Menu_Tracker> findBetweenTimes(Timestamp time1, Timestamp time2);
}
