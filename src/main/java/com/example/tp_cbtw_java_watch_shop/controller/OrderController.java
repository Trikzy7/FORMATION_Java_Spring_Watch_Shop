package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.OrderRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.OrderResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.OrderItem;
import com.example.tp_cbtw_java_watch_shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order (status + userId)
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO requestDTO) {
        // Création simple de l’Order, sans OrderItems pour l’instant
        OrderResponseDTO createdOrder = orderService.createOrder(requestDTO, List.of());
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Update order status
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        OrderResponseDTO updatedOrder = orderService.updateOrderStatus(
                id, Enum.valueOf(com.example.tp_cbtw_java_watch_shop.model.Order.OrderStatus.class, status)
        );
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}