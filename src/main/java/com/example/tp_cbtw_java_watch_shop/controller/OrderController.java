package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.OrderRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.OrderResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.Order;
import com.example.tp_cbtw_java_watch_shop.model.OrderItem;
import com.example.tp_cbtw_java_watch_shop.model.User;
import com.example.tp_cbtw_java_watch_shop.security.JwtService;
import com.example.tp_cbtw_java_watch_shop.service.OrderService;
import com.example.tp_cbtw_java_watch_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final JwtService jwtService;

    private UserService userService;

    public OrderController(OrderService orderService, JwtService jwtService, UserService userService) {
        this.orderService = orderService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    // Create a new order (status + userId)
//    @PostMapping
//    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO requestDTO) {
//        // Création simple de l’Order, sans OrderItems pour l’instant
//        OrderResponseDTO createdOrder = orderService.createOrder(requestDTO, List.of());
//        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody List<OrderItem> items) {

        // Extraire l’utilisateur connecté à partir du token JWT
        String email = jwtService.extractEmail(authHeader.replace("Bearer ", ""));
        User user = userService.findByEmail(email);

        Order createdOrder = orderService.createOrder(user.getId(), items);
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
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // GET /api/orders/history/{userId}
//    @GetMapping("/history/{userId}")
//    public List<OrderResponseDTO> getUserOrderHistory(@PathVariable Long userId) {
//        return orderService.getOrdersByUserId(userId);
//    }

    // GET /api/orders/history
    @GetMapping("/history")
    public List<OrderResponseDTO> getUserOrderHistory(@RequestHeader("Authorization") String authHeader) {

        // Renvoyer ses commandes
        return orderService.getOrdersByUserId(authHeader);
    }


}