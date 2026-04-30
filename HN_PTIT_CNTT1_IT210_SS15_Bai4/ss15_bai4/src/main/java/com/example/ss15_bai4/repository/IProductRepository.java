package com.example.ss15_bai4.repository;

import com.example.ss15_bai4.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    int countByCategoryAndStatus(String category, String status);

    @Modifying
    @Query("""
        UPDATE Product p
        SET p.price = p.price - (p.price * :discount / 100)
        WHERE p.category = :category AND p.status = 'ACTIVE'
    """)
    int updatePriceByCategory(@Param("category") String category,
                              @Param("discount") Double discount);
}