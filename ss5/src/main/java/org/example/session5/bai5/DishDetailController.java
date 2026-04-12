package org.example.session5.bai5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bai5")
public class DishDetailController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/quick-order")
    public String quickOrderPage(Model model) {
        model.addAllAttributes(orderService.prepareOrderData());
        return "quick-order";
    }
}