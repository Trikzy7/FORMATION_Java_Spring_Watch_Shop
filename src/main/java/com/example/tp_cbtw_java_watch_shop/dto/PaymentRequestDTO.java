package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Payment;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long orderId;
    private Double amount;
    private String paymentDate;
    private Payment.PaymentStatus status;

}
