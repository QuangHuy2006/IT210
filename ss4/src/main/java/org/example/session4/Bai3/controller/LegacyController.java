package org.example.ss4.src.main.java.org.example.session4.Bai3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("LegacyControllerB3")
@RequestMapping("/bai3")
public class LegacyController {

    /**
     * Lấy chi tiết đơn hàng theo Cách A
     * Sử dụng @PathVariable để ánh xạ biến {id} từ URL vào tham số hàm ngắn gọn
     */
    @GetMapping("/orders/{id}")
    public String getOrderDetail(@PathVariable("id") Long id) {
        System.out.println("Đang truy vấn chi tiết đơn hàng ID: " + id);

        return "Chi tiết đơn hàng số " + id;
    }
}