package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.dto.OrderRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.OrderResponseDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.OrderMapper;
import com.example.tp_cbtw_java_watch_shop.model.Order;
import com.example.tp_cbtw_java_watch_shop.model.OrderItem;
import com.example.tp_cbtw_java_watch_shop.repository.OrderRepository;
import com.example.tp_cbtw_java_watch_shop.repository.OrderItemRepository;
import com.example.tp_cbtw_java_watch_shop.security.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final JwtService jwtService;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, JwtService jwtService, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    // Create
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO, List<OrderItem> orderItems) {
        Order order = OrderMapper.toEntity(requestDTO);
        order.setOrderItems(orderItems);

        // Lier les OrderItems à la commande
        orderItems.forEach(item -> item.setOrder(order));

        Order saved = orderRepository.save(order);
        return OrderMapper.toDto(saved);
    }

    // Get all
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get by ID
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return OrderMapper.toDto(order);
    }

    // Update (status only)
    public OrderResponseDTO updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        order.setStatus(status);
        Order updated = orderRepository.save(order);
        return OrderMapper.toDto(updated);
    }

    // Delete
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

    public Order getOrderByIdEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    // Historique des commandes d'un utilisateur
    public List<OrderResponseDTO> getOrdersByUserId(String authHeader) {

        String email = jwtService.extractEmail(authHeader.replace("Bearer ", ""));
        Long userId = userService.findByEmail(email).getId();

        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }
}
