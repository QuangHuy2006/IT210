package com.example.ss15_bai3.repository;


import com.example.ss15_bai3.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
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


}