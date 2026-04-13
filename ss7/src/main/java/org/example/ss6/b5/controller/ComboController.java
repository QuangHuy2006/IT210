package org.example.ss6.b5.controller;

import org.example.ss6.b5.Model.Combo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/combo")
public class ComboController {

    private static List<Combo> comboList = new ArrayList<>();

    @PostMapping("/add")
    public String saveCombo(
            @RequestParam("comboName") String comboName,
            @RequestParam(value = "itemList", required = false) List<String> itemList,
            @RequestParam("banner") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        // 1. Kiểm tra nghiệp vụ: Ít nhất 2 món
        if (itemList == null || itemList.size() < 2) {
            redirectAttributes.addFlashAttribute("error", "Siêu Combo phải có ít nhất 2 món ăn!");
            return "redirect:/combo/add";
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Combo newCombo = new Combo(comboName, itemList, fileName);
        comboList.add(newCombo);

        System.out.println("=== NEW COMBO ADDED ===");
        System.out.println("{");
        System.out.println("  \"comboName\": \"" + comboName + "\",");
        System.out.println("  \"items\": " + itemList.toString() + ",");
        System.out.println("  \"banner\": \"" + fileName + "\"");
        System.out.println("}");
        System.out.println("=======================");

        return "redirect:/combo/success";
    }
}