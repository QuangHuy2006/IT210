package org.example.ss4.src.main.java.org.example.session4.Bai5.repository;


import org.springframework.stereotype.Repository;

@Repository("OrderRepositoryB5")
public class OrderRepository {
    public String findOrder(Long id) {
        return "Dữ liệu đơn hàng số " + id + " từ Database";
    }

    public String saveOrder() {
        return "Đã lưu 01 đơn hàng mới vào Database";
    }

    public String deleteOrder(Long id) {
        return "Đã xóa đơn hàng số " + id + " khỏi hệ thống";
    }
}