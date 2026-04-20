package org.example.ss9.controller;

import org.example.ss9.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
@Controller
@RequestMapping
@SessionAttributes("dataLogin") // hệ thống tự động lưu trữ
public class AccountController {

    List<Account> accounts = List.of(
            new Account(1L, "Nguyen Van A", "nguyenvana", "123456"),
            new Account(2L, "Tran Thi B", "tranthib", "654321"),
            new Account(3L, "Le Van C", "levanc", "abcdef")
    );

    @GetMapping
    public String home(Model model) {
        model.addAttribute("accounts", accounts);
        return "home";
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {
        model.addAttribute("dataLogin", new Account());
        return "form-login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @ModelAttribute("dataLogin") Account account,
            Model model
    ) {

        Account dataLogin = accounts.stream().filter(
                a -> a.getUsername().equals(account.getUsername()) &&
                        a.getPassword().equals(account.getPassword())).findFirst().orElse(null);

        if (dataLogin != null) {
            model.addAttribute("account", dataLogin);
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
        }

        return "home";
    }
    @GetMapping("/logOut")
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // xóa dữ liệu trong session
        return "home";
    }
}