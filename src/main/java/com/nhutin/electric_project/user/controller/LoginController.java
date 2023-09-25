package com.nhutin.electric_project.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "message", required = false) String message, Model model) {
        if (error != null) {
            String errorMessage = "Đăng nhập không thành công. Vui lòng kiểm tra lại địa chỉ email và mật khẩu.";
            model.addAttribute("errorMessage", errorMessage);
        }
        if (message != null) {
            model.addAttribute("errorMessage", message);
        }
        return "taikhoan/login";
    }

    @GetMapping("/logout")
    public String logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout=true";
    }

    @RequestMapping("/access-denied")
    public String denied(Model model) {
        model.addAttribute("message", "Từ chối truy cập vào trang");
        return "taikhoan/access-denied";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}
