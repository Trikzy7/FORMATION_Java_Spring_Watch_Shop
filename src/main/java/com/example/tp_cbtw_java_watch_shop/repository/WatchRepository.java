package com.example.tp_cbtw_java_watch_shop.repository;

import com.example.tp_cbtw_java_watch_shop.model.Watch;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {
}
