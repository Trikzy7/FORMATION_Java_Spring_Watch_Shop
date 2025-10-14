package com.example.tp_cbtw_java_watch_shop.mapper;

import com.example.tp_cbtw_java_watch_shop.dto.PaymentRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.PaymentResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.Payment;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setOrderId(dto.getOrderId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setStatus(dto.getStatus());
        return payment;
    }

    public static PaymentResponseDTO toDto(Payment entity) {
        return new PaymentResponseDTO(
            entity.getId(),
            entity.getOrderId(),
            entity.getAmount(),
            entity.getPaymentDate(),
            entity.getStatus().name()
        );
    }
}
