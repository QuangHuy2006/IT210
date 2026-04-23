package org.example.ss14_b5.repository;

import org.example.ss14_b5.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    void save(Order order);
}

