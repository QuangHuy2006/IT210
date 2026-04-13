package org.example.ss6.b4.controller;

import org.example.ss6.b4.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AppController {
    public static List<Food> listFood = new ArrayList<>();

    @PostMapping("/add")
    public String addFood(@RequestParam("name") String name,
                          @RequestParam("price") double price,
                          @RequestParam("file") MultipartFile file,
                          Model model) {
        if (file.isEmpty()) {
            model.addAttribute("error", "file chua duoc them");
            return "food-add";
        }
        String filename = file.getOriginalFilename();
        if (filename == null ||
                !(filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))) {

            model.addAttribute("error", "file khong dugn dinh dang");
        }

        if(price < 0){
            model.addAttribute("error", "Gia phai lon hon 0");
        }
        try {
            String uploadDir = "C:/RikkeiFood_Temp/";
            String customizeFilename = System.currentTimeMillis() + String.valueOf(Math.random()*100);
            File dest = new File(uploadDir + customizeFilename);
            if (dest.exists()) {
                model.addAttribute("error", "Tên file đã tồn tại, vui lòng đổi tên!");
                return "food-add";
            }
            file.transferTo(dest);

            Food food = new Food(name, price, file);
            listFood.add(food);

            model.addAttribute("message", "Thêm món ăn thành công!");
        } catch (IOException e) {
            model.addAttribute("error", "Lỗi khi lưu file: " + e.getMessage());
        }

        return "food-add";
    }
    @GetMapping("/list")
    public String listFoods(Model model) {
        model.addAttribute("foods", listFood);
        return "food-list";
    }
}
