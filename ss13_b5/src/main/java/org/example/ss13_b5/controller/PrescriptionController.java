package org.example.ss13_b5.controller;

import org.example.ss13_b5.model.Prescription;
import org.example.ss13_b5.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("prescriptions", prescriptionRepository.findAll());
        return "prescriptions/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "prescriptions/form";
    }

    @PostMapping
    public String save(@ModelAttribute Prescription prescription, BindingResult result) {
        // Kiểm tra số lượng thuốc không âm
        if (prescription.getDetails().stream().anyMatch(d -> d.getQuantity() < 0)) {
            result.rejectValue("details", "error.details", "Số lượng thuốc không được âm");
            return "prescriptions/form";
        }
        prescriptionRepository.save(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/search")
    public String search(@RequestParam String patientCode, Model model) {
        model.addAttribute("prescriptions", prescriptionRepository.findByPatientCode(patientCode));
        return "prescriptions/list";
    }
}

