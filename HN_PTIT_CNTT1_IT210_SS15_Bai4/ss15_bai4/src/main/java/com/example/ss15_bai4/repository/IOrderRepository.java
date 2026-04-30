package com.example.ss15_bai4.repository;


import com.example.ss15_bai4.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("""
        UPDATE Order o
        SET o.status = 'CANCELLED'
        WHERE o.orderId = :orderId AND o.status <> 'DELIVERED'
    """)
    int cancelFraudulentOrder(@Param("orderId") Long orderId);


    // Xử lý phân trang & sap xep
    Page<Order> findByUserId(Long userId, Pageable pageable);

    Page<Order> findByUserIdAndStatus(Long userId, String status, Pageable pageable);
}