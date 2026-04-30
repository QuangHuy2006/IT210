package com.example.ss15_bai2.service;

import com.example.ss15_bai2.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getProductsByCategoryAndMaxPrice(String category, Double maxPrice);

    void cancelFraudulentOrder(Long orderId);
}