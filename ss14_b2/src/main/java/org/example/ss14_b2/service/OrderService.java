package org.example.ss14_b2.service;

import org.example.ss14_b2.model.Order;
import org.example.ss14_b2.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void cancelOrder(Long orderId) {
        Session session = sessionFactory.getCurrentSession();

        // Lấy đơn hàng
        Order order = session.get(Order.class, orderId);
        if (order == null) {
            throw new RuntimeException("Đơn hàng không tồn tại!");
        }

        // Bước 1: Hủy đơn hàng
        order.setStatus("CANCELLED");
        session.update(order);

        // Bước 2: Hoàn kho
        Product product = session.get(Product.class, order.getProductId());
        if (product == null) {
            throw new RuntimeException("Sản phẩm không tồn tại!");
        }
        product.setStock(product.getStock() + order.getQuantity());
        session.update(product);

        // Không cần commit/rollback thủ công,
        // Spring sẽ tự commit nếu không có exception,
        // rollback nếu có exception.
    }
}
