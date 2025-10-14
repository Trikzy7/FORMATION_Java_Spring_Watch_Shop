package com.example.tp_cbtw_java_watch_shop.repository;

import com.example.tp_cbtw_java_watch_shop.model.Specificity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SpecificityRepository extends JpaRepository<Specificity, Long> {
}
