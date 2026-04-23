package org.example.ss14_b1.service;


import org.example.ss14_b1.model.Wallet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.example.ss14_b1.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PaymentService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void processPayment(Long orderId, Long walletId, double totalAmount) {
        Session session = (Session) sessionFactory.getCurrentSession();

        // 1. Lấy đơn hàng và cập nhật trạng thái
        Order order = session.get(Order.class, orderId);
        order.setStatus("PAID");
        session.update(order);

        if (true) throw new RuntimeException("Kết nối đến cổng thanh toán thất bại!");

        // 2. Trừ tiền trong ví khách hàng
        Wallet wallet = session.get(Wallet.class, walletId);
        if (wallet.getBalance() < totalAmount) {
            throw new RuntimeException("Số dư không đủ!");
        }
        wallet.setBalance(wallet.getBalance() - totalAmount);
        session.update(wallet);
    }
}


