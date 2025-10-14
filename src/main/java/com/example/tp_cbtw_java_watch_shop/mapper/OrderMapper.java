package com.example.tp_cbtw_java_watch_shop.mapper;
import com.example.tp_cbtw_java_watch_shop.dto.OrderRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.OrderResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.Order;

public class OrderMapper {

    public static Order toEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        return order;
    }

    public static OrderResponseDTO toDto(Order entity) {
        return OrderResponseDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus().name())
                .orderItems(entity.getOrderItems())
                .build();
    }
}
