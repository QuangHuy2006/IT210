package org.example.ss2.Bai5;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String list(HttpSession session, Model model) {

        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("techTotalSalary", employeeService.calculateTechDeptTotalSalary());
        return "/list";
    }

    @GetMapping("/{code}")
    public String detail(@PathVariable("code") String code, HttpSession session, Model model) {

        Employee emp = employeeService.getByCode(code);
        if (emp == null){
            model.addAttribute("errorMessage", "Nhân viên [" + code + "] không tồn tại trong hệ thống");
        }

        model.addAttribute("emp", emp);
        return "/detail";
    }
}