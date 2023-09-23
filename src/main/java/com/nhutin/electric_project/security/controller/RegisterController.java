package com.nhutin.electric_project.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhutin.electric_project.model.RegistrationRequest;
import com.nhutin.electric_project.repository.ConfirmationCodeDAO;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.security.service.RegistrationService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
//@RequestMapping(path = "registration")
public class RegisterController {

    private RegistrationService registrationService;
    private UserRepository userDAO;

    @GetMapping(path = "registration")
    public String form(Model model, @ModelAttribute("request") RegistrationRequest request) {
        return "register";
    }

    @PostMapping(path = "registration")
    public String register(Model model,
            @Valid @ModelAttribute("request") RegistrationRequest request,
            BindingResult result) {
        Boolean checked = true;
        if (userDAO.findByEmail(request.getEmail())
                .isPresent()) {
            model.addAttribute("duplicateEmail", "Địa chỉ email đã được sử dụng.");
            checked = false;
        }
        if (userDAO.findByUsername(request.getUsername())
                .isPresent()) {
            model.addAttribute("duplicateUsername", "Tên người dùng đã được sử dụng.");
            checked = false;
        }
        if (userDAO.findByPhoneNumber(request.getPhonenumber())
                .isPresent()) {
            model.addAttribute("duplicatePhonenumber", "Số điện thoại đã được sử dụng.");
            checked = false;
        }
        // Kiểm tra xem password và confirmPassword có giá trị null hay không
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            model.addAttribute("passwordNullError", "Mật khẩu hoặc mật khẩu xác nhận là null.");
            checked = false;
        } else if (!request.getPassword().equalsIgnoreCase(request.getConfirmPassword())) {
            model.addAttribute("doesntMatchPassword", "Mật khẩu xác nhận không khớp.");
            checked = false;
        }
        if (result.hasErrors()) {
            checked = false;
        }
        if (checked) { // Tạo người dùng
            registrationService.register(request);
            System.out.println("Create user successfully.");
            model.addAttribute("successMessage",
                    "Mã xác nhận đã được gửi vào email của bạn.</br>Hãy kiểm tra email và xác nhận kích hoạt tài khoản.");
        }
        return "register";
    }


    @Autowired
    ConfirmationCodeDAO confirmationCodeDAO;

    @GetMapping(path = "/registration/confirm")
    public String confirm(@RequestParam("token") String token, Model model,
            @ModelAttribute("request") RegistrationRequest request) {
        try {
            registrationService.confirmToken(token);
            model.addAttribute("successMessage", "Xác nhận tài khoản thành công.");
            return "register";
        } catch (IllegalStateException ex) {
            // Hiển thị lỗi ra màn hình
            model.addAttribute("errorMessage", ex.getMessage());
            return "register";
        }
    }

    @GetMapping("/resend-confirmation")
    public String resendConfirmation(@RequestParam("email") String email) {
        // Gọi service để gửi lại email xác nhận
        // Hãy chắc chắn kiểm tra trạng thái của email trước khi gửi lại
        // Ví dụ, kiểm tra xem email đã được xác nhận chưa
        // Nếu đã xác nhận, có thể không cần gửi lại
        // Nếu chưa xác nhận, gửi lại email xác nhận
        return "redirect:/confirmation-sent"; // Trang thông báo gửi lại email
    }

}

