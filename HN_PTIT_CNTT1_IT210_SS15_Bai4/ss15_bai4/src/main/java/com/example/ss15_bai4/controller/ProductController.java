package com.example.ss15_bai4.controller;


import com.example.ss15_bai4.model.Product;
import com.example.ss15_bai4.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public String filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double maxPrice,
            Model model
    ) {
        List<Product> products = productService.getProductsByCategoryAndMaxPrice(category, maxPrice);

        model.addAttribute("products", products);
        return "product-list";
    }

    // form discount
    @GetMapping("/discount")
    public String showDiscountForm() {
        return "discount-form";
    }

    // apply discount
    @PostMapping("/discount")
    public String applyDiscount(
            @RequestParam String category,
            @RequestParam Double discount,
            Model model
    ) {
        try {
            int updated = productService.applyDiscount(category, discount);

            model.addAttribute("message", "Cập nhật thành công " + updated + " sản phẩm");

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "discount-form";
    }
}