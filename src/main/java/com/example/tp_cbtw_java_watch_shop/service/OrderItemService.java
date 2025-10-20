package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.model.OrderItem;
import com.example.tp_cbtw_java_watch_shop.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Create
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Get all
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get by ID
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));
    }

    // Update
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem existing = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));

        existing.setWatch(updatedOrderItem.getWatch());
        existing.setQuantity(updatedOrderItem.getQuantity());
        existing.setOrder(updatedOrderItem.getOrder());

        return orderItemRepository.save(existing);
    }

    // Delete
    public void deleteOrderItem(Long id) {
        OrderItem existing = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));
        orderItemRepository.delete(existing);
    }

    // Récupérer tous les OrderItems d'une commande
    public List<OrderItem> getItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}