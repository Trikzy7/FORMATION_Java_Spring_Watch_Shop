package com.example.tp_cbtw_java_watch_shop.repository;

import com.example.tp_cbtw_java_watch_shop.model.Payment;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
