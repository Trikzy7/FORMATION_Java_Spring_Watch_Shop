package com.example.tp_cbtw_java_watch_shop.dto;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long id;
    private Long orderId;
    private Double amount;
    private String paymentDate;
    private String status;

    // Constructeur pratique
    public PaymentResponseDTO(Long id, Long orderId, Double amount, String paymentDate, String status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }
}
