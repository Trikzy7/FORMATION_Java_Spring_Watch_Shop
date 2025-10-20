package com.example.tp_cbtw_java_watch_shop.repository;

import com.example.tp_cbtw_java_watch_shop.model.OrderItem;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
}
