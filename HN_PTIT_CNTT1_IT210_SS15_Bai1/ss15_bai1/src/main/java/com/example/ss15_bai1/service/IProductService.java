package com.example.ss15_bai1.service;

import com.example.ss15_bai1.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getProductsByCategoryAndMaxPrice(String category, Double maxPrice);

}