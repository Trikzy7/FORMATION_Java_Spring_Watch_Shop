package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.dto.PaymentRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.PaymentResponseDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.PaymentMapper;
import com.example.tp_cbtw_java_watch_shop.model.Payment;
import com.example.tp_cbtw_java_watch_shop.repository.PaymentRepository;
import com.example.tp_cbtw_java_watch_shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Create
    public PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO) {
        Payment payment = PaymentMapper.toEntity(requestDTO);
        Payment saved = paymentRepository.save(payment);
        return PaymentMapper.toDto(saved);
    }

    // Get all
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get by ID
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return PaymentMapper.toDto(payment);
    }

    // Update
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO requestDTO) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));


        existing.setAmount(requestDTO.getAmount());
        existing.setPaymentDate(requestDTO.getPaymentDate());
        existing.setStatus(requestDTO.getStatus());

        Payment updated = paymentRepository.save(existing);
        return PaymentMapper.toDto(updated);
    }

    // Delete
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        paymentRepository.delete(payment);
    }
}
