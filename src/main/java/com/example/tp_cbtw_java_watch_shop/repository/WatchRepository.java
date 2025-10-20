package com.example.tp_cbtw_java_watch_shop.repository;

import com.example.tp_cbtw_java_watch_shop.model.Watch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {

    @Query("""
        SELECT w FROM Watch w
        WHERE (:brand IS NULL OR w.brand = :brand)
        AND (:category IS NULL OR w.category = :category)
        AND (:minPrice IS NULL OR w.price >= :minPrice)
        AND (:maxPrice IS NULL OR w.price <= :maxPrice)
    """)
    List<Watch> filterWatches(
            @Param("brand") String brand,
            @Param("category") String category,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
