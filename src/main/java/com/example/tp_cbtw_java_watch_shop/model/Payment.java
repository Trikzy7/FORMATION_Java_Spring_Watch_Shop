package com.example.tp_cbtw_java_watch_shop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Double amount;
    private String paymentDate;
    private PaymentStatus status;

    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
}
