package org.example.ss14_b5.service;

import org.example.ss14_b5.model.Order;
import org.example.ss14_b5.model.OrderDetail;
import org.example.ss14_b5.model.Product;
import org.example.ss14_b5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Autowired
    private WalletService walletService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepo;

    @Transactional(propagation = Propagation.REQUIRED)
    public void payOrder(Order order) {
        double total = order.getTotalAmount();
        walletService.decreaseBalance(order.getCustomerId(), total);

        for (OrderDetail detail : order.getDetails()) {
            payVendor(detail);
        }

        orderRepo.save(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void payVendor(OrderDetail detail) {
        Product product = productService.findById(detail.getProduct().getId());
        if (product.getStock() < detail.getQuantity()) {
            throw new RuntimeException("Kho không đủ cho sản phẩm: " + product.getName());
        }
        product.setStock(product.getStock() - detail.getQuantity());
        productService.save(product);
    }
}

