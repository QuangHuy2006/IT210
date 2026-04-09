package org.example.ss4.src.main.java.org.example.session4.Bai2.controller;


import org.example.ss4.src.main.java.org.example.session4.Bai2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("legacyControllerBai2")
@RequestMapping("/bai2")
public class LegacyController {

    private final OrderService orderService;

    @Autowired
    public LegacyController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public String getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/menu")
    public String getMenu(
            @RequestParam(
                    value = "category",
                    required = false,
                    defaultValue = "chay"
            ) String category
    ) {
        System.out.println("Đang truy cập thực đơn loại: " + category);

        if ("chay".equalsIgnoreCase(category)) {
            return "Thực đơn món CHAY: Đậu phụ sốt cà, Nấm kho tộ, Canh rau củ.";
        } else if ("man".equalsIgnoreCase(category)) {
            return "Thực đơn món MẶN: Thịt kho tàu, Cá điêu hồng chiên xù, Sườn xào chua ngọt.";
        } else {
            return "Thực đơn cho loại '" + category + "' hiện đang cập nhật!";
        }
    }
}