package com.example.tp_cbtw_java_watch_shop.repository;

import com.example.tp_cbtw_java_watch_shop.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}