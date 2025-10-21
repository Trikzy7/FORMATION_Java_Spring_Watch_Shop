package com.example.tp_cbtw_java_watch_shop.dto;

import com.example.tp_cbtw_java_watch_shop.model.Payment;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequestDTO {

    @NotBlank(message = "orderId est requis")
    private Long orderId;

    private Double amount;
    private LocalDateTime paymentDate;
    private Payment.PaymentStatus status;

}
