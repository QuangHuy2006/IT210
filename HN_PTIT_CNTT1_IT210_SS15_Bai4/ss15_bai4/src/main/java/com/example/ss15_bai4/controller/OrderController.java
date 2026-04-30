package com.example.ss15_bai4.controller;

import com.example.ss15_bai4.model.Order;
import com.example.ss15_bai4.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IProductService productService;

    @GetMapping
    public String getOrderHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ALL") String status,
            @RequestParam(defaultValue = "orderDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            Model model
    ) {

        Long userId = 1L;

        Page<Order> orderPage = productService.getOrderHistory(
                userId, status, page, size, sortBy, direction
        );

        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("currentPage", orderPage.getNumber());
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("status", status);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "order-history";
    }

    @PostMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        productService.cancelFraudulentOrder(id);
        return "redirect:/orders";
    }
}