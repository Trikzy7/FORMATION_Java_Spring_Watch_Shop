package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Order;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequestDTO {

//    @NotBlank(message = "userId est requis")
    private Long userId;

    private LocalDateTime orderDate;
    private Order.OrderStatus status;
}
