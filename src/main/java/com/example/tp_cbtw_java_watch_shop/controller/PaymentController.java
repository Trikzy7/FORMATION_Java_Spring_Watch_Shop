package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.PaymentRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.PaymentResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.Payment;
import com.example.tp_cbtw_java_watch_shop.model.User;
import com.example.tp_cbtw_java_watch_shop.security.JwtService;
import com.example.tp_cbtw_java_watch_shop.service.PaymentService;
import com.example.tp_cbtw_java_watch_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final JwtService jwtService;
    private final UserService userService;

    public PaymentController(PaymentService paymentService, JwtService jwtService, UserService userService) {
        this.paymentService = paymentService;
        this.jwtService = jwtService;
        this.userService = userService;
    }
    // Create payment
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO payment) {
        PaymentResponseDTO created = paymentService.createPayment(payment);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get all payments
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    // Get payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.getPaymentById(id), HttpStatus.OK);
    }

    // Update payment by ID
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO payment) {
        return new ResponseEntity<>(paymentService.updatePayment(id, payment), HttpStatus.OK);
    }

    // Delete payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<Payment> payOrder(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long orderId) {

        String email = jwtService.extractEmail(authHeader.replace("Bearer ", ""));
        User user = userService.findByEmail(email);

        Payment payment = paymentService.payOrder(orderId, user);
        return ResponseEntity.ok(payment);
    }
}