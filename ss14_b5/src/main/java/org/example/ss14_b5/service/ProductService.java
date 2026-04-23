package org.example.ss14_b5.service;

import org.example.ss14_b5.model.Product;
import org.example.ss14_b5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> listAll() {
        return productRepo.findAll();
    }

    public Product findById(Long id) {
        return productRepo.findById(id);
    }

    @Transactional
    public void save(Product product) {
        productRepo.save(product);
    }
}

