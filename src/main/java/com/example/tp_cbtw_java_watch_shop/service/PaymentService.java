package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.dto.PaymentRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.PaymentResponseDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.PaymentMapper;
import com.example.tp_cbtw_java_watch_shop.model.Order;
import com.example.tp_cbtw_java_watch_shop.model.Payment;
import com.example.tp_cbtw_java_watch_shop.model.User;
import com.example.tp_cbtw_java_watch_shop.repository.PaymentRepository;
import com.example.tp_cbtw_java_watch_shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public PaymentService(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
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

    // Méthode pour payer une commande
    public Payment payOrder(Long orderId, User user) {
        // On récupère la commande via OrderService
        Order order = orderService.getOrderByIdEntity(orderId);

        // Vérifier que la commande appartient à l'utilisateur
        if (!order.getUserId().equals(user.getId())) {
            throw new RuntimeException("Vous n'êtes pas autorisé à payer cette commande");
        }

        // Calcul du montant total
        double amount = order.getOrderItems().stream()
                .mapToDouble(item -> item.getWatch().getPrice() * item.getQuantity())
                .sum();

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(Payment.PaymentStatus.COMPLETED);

        return paymentRepository.save(payment);
    }
}
