package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Order;
import com.example.tp_cbtw_java_watch_shop.model.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class OrderResponseDTO {

    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItem> orderItems;

//    public OrderResponseDTO(Order order) {
//        this.id = order.getId();
//        this.userId = order.getUserId();
//        this.orderDate = order.getOrderDate();
//        this.status = order.getStatus().name();
//        this.orderItems = order.getOrderItems().stream()
//                .map(item -> new OrderItem(item.getWatch(), item.getQuantity()))
//                .collect(Collectors.toList());
//    }

//    public OrderResponseDTO(Order order) {
//        this.id = order.getId();
//        this.userId = order.getUserId();
//        this.orderDate = order.getOrderDate();
//        this.status = order.getStatus().name();
//        this.orderItems = order.getOrderItems();
//    }
}