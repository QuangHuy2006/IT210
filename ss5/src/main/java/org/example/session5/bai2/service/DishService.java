package org.example.session5.bai2.service;

import org.example.session5.bai2.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    public List<Dish> getAllDishes() {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(1L, "Phở Bò Hà Nội", 55000.0, true));
        dishes.add(new Dish(2L, "Bún Chả Cầu Giấy", 45000.0, false)); // Hết hàng
        dishes.add(new Dish(3L, "Nem Công Chả Phượng", 150000.0, true));
        return dishes;
    }
}