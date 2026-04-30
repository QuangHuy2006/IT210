package com.example.ss15_bai1.service;

import com.example.ss15_bai1.model.Product;
import com.example.ss15_bai1.repository.IProductRepository;
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
}