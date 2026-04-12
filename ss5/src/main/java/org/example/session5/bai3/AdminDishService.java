package org.example.session5.bai3;

import org.example.session5.bai2.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminDishService {
    private List<Dish> mockData = new ArrayList<>();

    public AdminDishService() {
        mockData.add(new Dish(1L, "Phở Bò", 55000.0, true));
        mockData.add(new Dish(2L, "Bún Chả", 45000.0, false));
    }

    public List<Dish> getAll() { return mockData; }

    public Optional<Dish> findById(Long id) {
        return mockData.stream().filter(d -> d.getId().equals(id)).findFirst();
    }
}