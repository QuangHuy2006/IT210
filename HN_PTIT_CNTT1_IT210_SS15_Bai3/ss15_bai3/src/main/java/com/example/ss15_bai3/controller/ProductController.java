package com.example.ss15_bai3.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.ss15_bai3.model.Product;
import com.example.ss15_bai3.service.IProductService;
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
}