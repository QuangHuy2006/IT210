package com.example.ss15_bai2.service;


import com.example.ss15_bai2.model.Product;
import com.example.ss15_bai2.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public List<Product> getProductsByCategoryAndMaxPrice(String category, Double maxPrice) {

        return productRepository
                .findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
                        category,
                        maxPrice,
                        0
                );
    }

    @Override
    public void cancelFraudulentOrder(Long orderId) {

        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("OrderId không hợp lệ");
        }

        int updated = productRepository.cancelFraudulentOrder(orderId);

        if (updated == 0) {
            throw new RuntimeException(
                    "Không thể hủy đơn (không tồn tại hoặc đã giao)"
            );
        }
    }
}