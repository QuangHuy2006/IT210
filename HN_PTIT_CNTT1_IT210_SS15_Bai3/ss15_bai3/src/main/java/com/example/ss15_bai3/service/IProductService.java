package com.example.ss15_bai3.service;

import com.example.ss15_bai3.model.Order;
import com.example.ss15_bai3.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {

    List<Product> getProductsByCategoryAndMaxPrice(String category, Double maxPrice);

    void cancelFraudulentOrder(Long orderId);

    Page<Order> getOrderHistory(
            Long userId,
            String status,
            int page,
            int size,
            String sortBy,
            String direction
    );
}