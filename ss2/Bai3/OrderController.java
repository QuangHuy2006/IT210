package org.example.session02.Bai3;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @GetMapping("/orders")
    public String showOrders(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        List<Order> orders = Arrays.asList(
                new Order("ORD001", "Laptop Dell", 25000000, new Date()),
                new Order("ORD002", "Chuột Logitech", 500000, new Date()),
                new Order("ORD003", "Bàn phím cơ", 1200000, new Date())
        );
        request.setAttribute("orderList", orders);

        ServletContext application = request.getServletContext();
        synchronized (application) {
            Integer total = (Integer) application.getAttribute("totalViewCount");
            if (total == null) total = 0;
            application.setAttribute("totalViewCount", total + 1);
        }

        return "orders";
    }
}
