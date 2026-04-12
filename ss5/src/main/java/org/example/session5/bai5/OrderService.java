package org.example.session5.bai5;

import org.example.session5.bai2.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Map<String, Object> prepareOrderData() {
        List<Dish> items = repository.getQuickOrderMenu();

        double subTotal = items.stream().mapToDouble(Dish::getPrice).sum();
        double tax = subTotal * 0.1;
        double total = subTotal + tax;

        Map<String, Object> data = new HashMap<>();
        data.put("menuItems", items);
        data.put("subTotal", subTotal);
        data.put("tax", tax);
        data.put("total", total);

        return data;
    }
}