package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.TourDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TourController {

    @GetMapping("/create-tour")
    public String form(Model model) {
        model.addAttribute("tourDTO", new TourDTO());
        return "create-tour";
    }

    @PostMapping("/create-tour")
    public String submit(
            @Valid @ModelAttribute("tourDTO") TourDTO dto,
            BindingResult result) {

        if (result.hasErrors()) {
            return "create-tour";
        }

        return "success";
    }
}