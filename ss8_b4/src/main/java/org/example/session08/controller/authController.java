package org.example.session08.controller;

import jakarta.validation.Valid;
import org.example.session08.dto.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class authController {

    @GetMapping("/register")
    public String form(Model model){
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String submit(
            @Valid @ModelAttribute("registerDTO") RegisterDTO dto,
            BindingResult result){

        if(result.hasErrors()){
            return "register";
        }

        return "success";
    }
}