package org.example.ss14_b3.service;

import org.example.ss14_b3.model.Order;
import org.example.ss14_b3.model.Product;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlashSaleService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void buyNow(Long productId, Long customerId, int quantity) {
        Session session = sessionFactory.getCurrentSession();

        // Khóa bản ghi sản phẩm để tránh race condition
        Product product = session.get(Product.class, productId, LockMode.PESSIMISTIC_WRITE);

        if (product.getStock() < quantity) {
            throw new RuntimeException("Hết hàng!");
        }

        // Trừ kho
        product.setStock(product.getStock() - quantity);
        session.update(product);

        // Tạo đơn hàng
        Order order = new Order();
        order.setProductId(productId);
        order.setCustomerId(customerId);
        order.setQuantity(quantity);
        order.setStatus("CONFIRMED");
        session.save(order);
    }
}
