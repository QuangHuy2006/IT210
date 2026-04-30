package com.example.ss15_bai2.repository;


import com.example.ss15_bai2.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

@Transactional
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
            String category,
            Double maxPrice,
            Integer stockQuantity
    );

    @Modifying
    @Query("UPDATE Order o SET o.status = 'CANCELLED' WHERE o.orderId = :orderId")
    int cancelFraudulentOrder(@Param("orderId") Long orderId);


}