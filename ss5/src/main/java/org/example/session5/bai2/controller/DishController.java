package org.example.session5.bai2.controller;

import org.example.session5.bai2.model.Dish;
import org.example.session5.bai2.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bai2")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/dishes")
    public String listDishes(Model model) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "dish-list";
    }
}