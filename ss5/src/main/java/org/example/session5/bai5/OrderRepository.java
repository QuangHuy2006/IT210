package org.example.session5.bai5;


import org.example.session5.bai2.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    public List<Dish> getQuickOrderMenu() {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish(101L, "Sườn Xào Chua Ngọt", 120000.0, true));
        menu.add(new Dish(102L, "Gà Không Lối Thoát", 350000.0, true));
        menu.add(new Dish(103L, "Lẩu Thái Hải Sản", 450000.0, true));
        return menu;
    }
}