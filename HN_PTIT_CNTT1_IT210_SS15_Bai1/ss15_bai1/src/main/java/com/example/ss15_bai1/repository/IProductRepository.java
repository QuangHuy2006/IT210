package com.example.ss15_bai1.repository;

import com.example.ss15_bai1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
            String category,
            Double maxPrice,
            Integer stockQuantity
    );
}