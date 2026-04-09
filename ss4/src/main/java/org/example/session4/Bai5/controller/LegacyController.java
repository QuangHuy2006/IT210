package org.example.ss4.src.main.java.org.example.session4.Bai5.controller;


import org.example.ss4.src.main.java.org.example.session4.Bai5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController("LegacyControllerB5")
@RequestMapping("/bai5/orders")
public class LegacyController {
    private final OrderService orderService;

    @Autowired
    public LegacyController(OrderService orderService) {
        System.out.println("ok");
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id) {
        return orderService.getOrderDetail(id);
    }

    @PostMapping
    public String createOrder() {
        return orderService.createNewOrder();
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch() {
        return "Thông báo: ID đơn hàng phải là một số (nguyên dương). Vui lòng kiểm tra lại!";
    }
}