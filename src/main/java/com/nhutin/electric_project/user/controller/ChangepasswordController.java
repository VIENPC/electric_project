package com.nhutin.electric_project.user.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.service.MailerService;

@Controller
public class ChangepasswordController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailerService mailer;

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository userDAO;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/changepassword")
    public String changepassword() {

        return "user/changepassword";
    }

   public User getNguoiDung() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDAO.findByEmail(auth.getName()).get();
    }
    @Transactional
    @PostMapping("/changepassword")
    public String postchangepassword(Model model, @RequestParam("password") String password,
            @RequestParam("newpassword") String newpassword, @RequestParam("repassword") String repassword) {

                
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User nguoiDung = userDAO.findByEmail(auth.getName()).get();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss  dd/MM/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);

        User users = userDAO.findByusernameLike(nguoiDung.getUsername());
        String repasswordecode = passwordEncoder.encode(repassword);

        if (!passwordEncoder.matches(password, users.getPassword())) {
            model.addAttribute("errorpassword", "Mật khẩu củ sai");
            return "user/changepassword";
        } else if (password.equals(newpassword)) {
            model.addAttribute("errornewpassword", "Đã trùng với mật khẩu củ");
            return "user/changepassword";
        } else if (!newpassword.equals(repassword)) {
            model.addAttribute("errorrepassword", "Nhập lại mật khẩu mới không trùng khớp");
            return "user/changepassword";
        } else {

            try {
                String subject = "Cảnh báo bảo mật";

                // Đọc nội dung của file email-template.html từ thư mục nguồn (resources)
                ClassPathResource resource = new ClassPathResource("templates/user/formemail/messageemail.html");
                byte[] templateBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
                String template = new String(templateBytes, "UTF-8");

                // Thay thế giá trị động trong template
                template = template.replace("[[formattedDateTime]]", formattedDateTime)
                        .replace("[[formatusername]]", nguoiDung.getUsername().replace("subject", subject));
                mailer.send(users.getEmail(), subject, template);
                userDAO.updatepassword(repasswordecode, nguoiDung.getUsername());
                System.out.println("Đổi thành công");
            } catch (Exception e) {
                System.out.println("Thất bại" + e);
            }

        }
        return "redirect:/login";
    }
}
