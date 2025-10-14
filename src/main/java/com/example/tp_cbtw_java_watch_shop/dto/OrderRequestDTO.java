package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequestDTO {
    private Long userId;
    private LocalDateTime orderDate;
    private Order.OrderStatus status;
}
