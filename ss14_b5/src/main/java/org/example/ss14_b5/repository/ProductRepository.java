package org.example.ss14_b5.repository;

import org.example.ss14_b5.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
    void save(Product pro);
}
