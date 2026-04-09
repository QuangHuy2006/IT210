package org.example.ss4.src.main.java.org.example.session4.Bai4.repository;

import org.springframework.stereotype.Repository;

@Repository("OrderRepositoryB4")
public class OrderRepository {
    public String getAllOrders() {
        return "Danh sach toan bo don hang";
    }

    public String getOrderById(Long id) {
        return "Thong tin don hang voi ID: " + id;
    }
}