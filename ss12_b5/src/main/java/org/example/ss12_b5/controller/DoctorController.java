package org.example.ss12_b5.controller;

import org.example.ss12_b5.Model.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private List<Doctor> doctors = new ArrayList<>();

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    @GetMapping("/add")
    public String addDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctors.add(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/search")
    public String searchDoctor(@RequestParam String phone, Model model) {
        Doctor found = doctors.stream()
                .filter(d -> d.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
        model.addAttribute("doctor", found);
        return "doctor-detail";
    }
}
