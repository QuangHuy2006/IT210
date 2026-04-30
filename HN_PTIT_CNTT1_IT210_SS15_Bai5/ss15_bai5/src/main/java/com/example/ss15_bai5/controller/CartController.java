package com.example.ss15_bai5.controller;

import lombok.RequiredArgsConstructor;
import com.example.ss15_bai5.service.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final VoucherService voucherService;

    @PostMapping("/apply-voucher")
    public String applyVoucher(
            @RequestParam String code,
            Model model
    ) {
        Long userId = 1L;

        try {
            voucherService.applyVoucher(userId, code);
            model.addAttribute("success", "Áp dụng voucher thành công!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "cart";
    }
}