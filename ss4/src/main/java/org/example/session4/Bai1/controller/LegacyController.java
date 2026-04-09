package org.example.ss4.src.main.java.org.example.session4.Bai1.controller;

import org.example.ss4.src.main.java.org.example.session4.Bai1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("legacyControllerBai1")
@RequestMapping
public class LegacyController {

    private final OrderService orderService;

    @Autowired
    public LegacyController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public String createOrder() {
        return "Đã tạo đơn hàng mới thành công!";
    }
}