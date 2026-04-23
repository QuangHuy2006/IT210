package org.example.ss14_b4.service;

import org.example.ss14_b4.model.Order;
import org.example.ss14_b4.model.Product;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CheckoutService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void checkout(Long productId, Long customerId, int quantity) {
        Session session = sessionFactory.getCurrentSession();

        Product product = session.get(Product.class, productId, LockMode.PESSIMISTIC_WRITE);
        if (product.getStock() < quantity) {
            throw new RuntimeException("Hết hàng!");
        }

        // Trừ kho (reserve)
        product.setStock(product.getStock() - quantity);
        session.update(product);

        // Tạo đơn hàng trạng thái PENDING
        Order order = new Order();
        order.setProductId(productId);
        order.setCustomerId(customerId);
        order.setQuantity(quantity);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        session.save(order);
    }
}

