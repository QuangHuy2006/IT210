package org.example.ss4.src.main.java.org.example.session4.Bai4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("LegacyControllerB4")
public class LegacyController {

    @GetMapping("/bai4/products")
    public String getProducts(
            @RequestParam("category") String category,
            @RequestParam("limit") int limit,
            ModelMap model
    ) {
        // Sử dụng 1 dòng code duy nhất với chaining method của ModelMap
        model.addAttribute("cat", category)
                .addAttribute("lim", limit)
                .addAttribute("msg", "Tìm kiếm thành công!");

        // Trả về tên file JSP (Spring sẽ tự tìm trong /WEB-INF/views/productList.jsp)
        return "productList";
    }
}