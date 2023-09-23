package com.nhutin.electric_project.user.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhutin.electric_project.model.ConfirmationCode;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.ConfirmationCodeDAO;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.service.MailerService;

@Controller
public class RestPasswordController {

   
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailerService mailer;

    @Autowired
    UserRepository userDAO;

    @Autowired
    ConfirmationCodeDAO confirmationcodeDAO;

    @GetMapping("/reset-password")
    public String resetPassword(HttpServletRequest request) {
        return "taikhoan/reset-password";
    }

    @Transactional
    @PostMapping("/reset-password")
    public String postResetPassword(HttpSession session, Model model, @RequestParam("email") String email) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss  dd/MM/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        User users = userDAO.findByEmailLike(email);
        if (users == null) {
            model.addAttribute("error", "Email không tồn tại");
            System.out.println("Email kh\u00F4ng t\u1ED3n t\u1EA1i");
            return "taikhoan/reset-password";
        }
        int userID = users.getUserID();
        String generated_OTP = generateRandomOtp(); // Lưu trữ mã OTP đã tạo
        try {
            String subject = "Lấy Lại Mật Khẩu";

            // Đọc nội dung của file email-template.html từ thư mục nguồn (resources)
            ClassPathResource resource = new ClassPathResource("templates/user/formemail/formemail.html");
            byte[] templateBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String template = new String(templateBytes, "UTF-8");

            // Thay thế giá trị động trong template
            template = template.replace("[[formattedDateTime]]", formattedDateTime)
                    .replace("[[generated_OTP]]", generated_OTP.replace("subject", subject));
            mailer.send(email, subject, template);
            session.setAttribute("userID", userID);
            session.setAttribute("emailreotp", email);
            session.removeAttribute("otpExpirationTime");
            Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
            LocalDateTime expirationDateTime = currentDateTime.plus(5,
                    ChronoUnit.MINUTES);
            Timestamp expirationTimestamp = Timestamp.valueOf(expirationDateTime);

            ConfirmationCode confirmationcode = confirmationcodeDAO.findByUserIDLike(userID);
            if (confirmationcode == null) {
                ConfirmationCode code = new ConfirmationCode(userID, false, generated_OTP, currentTimestamp,
                        expirationTimestamp);
                confirmationcodeDAO.save(code);
            } else {
                confirmationcodeDAO.updateCodeOTP(generated_OTP, false, currentTimestamp, expirationTimestamp, userID);
            }
            System.out.println("lưu thành công");
            return "redirect:/codeVerification";
        } catch (Exception e) {
            System.out.println("Lỗi khi gửi email: " + e);
            return "redirect:/reset-password";
        }
    }

    @GetMapping("/codeVerification")
    public String showOtpForm(HttpSession session, Model model) {
        int userID = (int) session.getAttribute("userID");
        LocalDateTime currentDateTime = LocalDateTime.now();
        ConfirmationCode confirmationCode = confirmationcodeDAO.findByUserIDLike(userID);
        Timestamp CreationDate = confirmationCode.getOTPCreationDate();
        Timestamp ExpirationDate = confirmationCode.getOTPExpirationDate();
        long millisecondsDifference = ExpirationDate.getTime() - CreationDate.getTime();
        User users = userDAO.findByUserIDLike(userID);
        String email = users.getEmail();
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);

        int usernameLength = username.length();
        String maskedUsername = username.charAt(0) + "*".repeat(usernameLength - 2)
                + username.charAt(usernameLength - 1);
        String maskedEmail = maskedUsername + domain;

        Timestamp demo1 = Timestamp.valueOf(currentDateTime);
        long demo = ExpirationDate.getTime() - demo1.getTime();
        if (demo > 0) {
            Long otpExpirationTime = (Long) session.getAttribute("otpExpirationTime");
            long currentTimestamp = System.currentTimeMillis();
            boolean hasExpired = false;

            if (otpExpirationTime != null && currentTimestamp > otpExpirationTime) {
                hasExpired = true;
            }

            if (otpExpirationTime == null || hasExpired) {
                otpExpirationTime = currentTimestamp + millisecondsDifference; // time seconds
                session.setAttribute("otpExpirationTime", otpExpirationTime);
            }
            // long timeRemaining = hasExpired ? 0 : ;
            long timeRemaining = 0;
            if (confirmationCode.getIsConfirmed()) {
                timeRemaining = 0;
            } else {
                timeRemaining = ((otpExpirationTime - currentTimestamp) / 1000);
            }
            model.addAttribute("otpExpirationTime", otpExpirationTime);
            model.addAttribute("timeRemaining", timeRemaining);
            model.addAttribute("maskedEmail", maskedEmail);
            return "taikhoan/code-verification";
        } else {
            model.addAttribute("maskedEmail", maskedEmail);
            model.addAttribute("timeRemaining", 0);
            return "taikhoan/code-verification";
        }
    }

    @Transactional
    @PostMapping("/codeVerification")
    public String verifyOtp(HttpSession session, @RequestParam("otp") String enteredOtp, Model model) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
        int userID = (int) session.getAttribute("userID");
        ConfirmationCode confirmationCode = confirmationcodeDAO.findByUserIDLike(userID);
        User users = userDAO.findByUserIDLike(userID);
        String email = users.getEmail();
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);
        int usernameLength = username.length();
        String maskedUsername = username.charAt(0) + "*".repeat(usernameLength - 2)
                + username.charAt(usernameLength - 1);
        String maskedEmail = maskedUsername + domain;

        Timestamp CreationDate = confirmationCode.getOTPCreationDate();
        Timestamp ExpirationDate = confirmationCode.getOTPExpirationDate();
        long millisecondsDifference = ExpirationDate.getTime() - CreationDate.getTime();
        Long otpExpirationTime = (Long) session.getAttribute("otpExpirationTime");
        long currentTimestamp2 = System.currentTimeMillis();
        boolean hasExpired = false;
        if (otpExpirationTime != null && currentTimestamp2 > otpExpirationTime) {
            hasExpired = true;
        }
        if (otpExpirationTime == null || hasExpired) {
            otpExpirationTime = currentTimestamp2 + millisecondsDifference; // time seconds
            session.setAttribute("otpExpirationTime", otpExpirationTime);
        }
        long timeRemaining = 0;
        if (confirmationCode.getIsConfirmed()) {
            timeRemaining = 0;
        } else {
            timeRemaining = ((otpExpirationTime - currentTimestamp2) / 1000);
        }

        if (currentTimestamp.compareTo(confirmationCode.getOTPExpirationDate()) > 0 ||
                currentTimestamp.compareTo(confirmationCode.getOTPCreationDate()) < 0) {
            System.out.println("Hết thời gian");
            model.addAttribute("error", "Mã OTP đã hết hạn");
            model.addAttribute("maskedEmail", maskedEmail);
            model.addAttribute("timeRemaining", 0);
            return "taikhoan/code-verification";
        } else if (confirmationCode.getIsConfirmed()) {
            System.out.println("Mã không còn hiệu lực");
            model.addAttribute("otpExpirationTime", otpExpirationTime);
            model.addAttribute("timeRemaining", timeRemaining);
            model.addAttribute("maskedEmail", maskedEmail);
            model.addAttribute("error", "Mã OTP đã hết liệu lực");
            return "taikhoan/code-verification";
        } else if (enteredOtp.equals(confirmationCode.getOTPCode())) {
            System.out.println("Thành công");
            confirmationcodeDAO.updateIsConfirmed(true, userID);
            return "taikhoan/new-password";
        } else {
            System.out.println("Thất bại");
            model.addAttribute("otpExpirationTime", otpExpirationTime);
            model.addAttribute("timeRemaining", timeRemaining);
            model.addAttribute("maskedEmail", maskedEmail);
            model.addAttribute("error", "Nhập sai mã OTP");
            return "taikhoan/code-verification";
        }

    }

    private String generateRandomOtp() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    @Transactional
    @PostMapping("/resendOtp")
    public String resendOtp(HttpSession session, Model model) {
        String emailreotp = (String) session.getAttribute("emailreotp");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss  dd/MM/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        User users = userDAO.findByEmailLike(emailreotp);
        int userID = users.getUserID();
        String generated_OTP = generateRandomOtp(); // Lưu trữ mã OTP đã tạo

        try {
            String subject = "Lấy Lại Mật Khẩu";

            // Đọc nội dung của file email-template.html từ thư mục nguồn (resources)
            ClassPathResource resource = new ClassPathResource("templates/user/formemail/formemail.html");
            byte[] templateBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String template = new String(templateBytes, "UTF-8");

            // Thay thế giá trị động trong template
            template = template.replace("[[formattedDateTime]]", formattedDateTime)
                    .replace("[[generated_OTP]]", generated_OTP);
            mailer.queue(emailreotp, subject, template);
            session.setAttribute("userID", userID);
            session.setAttribute("emailreotp", emailreotp);
            session.removeAttribute("otpExpirationTime");
            Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
            LocalDateTime expirationDateTime = currentDateTime.plus(5,
                    ChronoUnit.MINUTES);
            Timestamp expirationTimestamp = Timestamp.valueOf(expirationDateTime);
            ConfirmationCode confirmationcode = confirmationcodeDAO.findByUserIDLike(userID);
            if (confirmationcode == null) {
                ConfirmationCode code = new ConfirmationCode(userID, false, generated_OTP, currentTimestamp,
                        expirationTimestamp);
                confirmationcodeDAO.save(code);
            } else {
                confirmationcodeDAO.updateCodeOTP(generated_OTP, false, currentTimestamp, expirationTimestamp, userID);
            }
            System.out.println("lưu thành công");
            return "redirect:/codeVerification";
        } catch (Exception e) {
            System.out.println("Lỗi khi gửi email: " + e);
            return "redirect:/codeVerification";
        }
    }

    @GetMapping("/new-password")
    public String newPassword(Model model) {
        return "taikhoan/new-password";
    }

    @Transactional
    @PostMapping("/new-password")
    public String postNewPassword(HttpSession session, Model model, @RequestParam("newpassword") String newpassword,
            @RequestParam("repassword") String repassword) {
        int userID = (int) session.getAttribute("userID");
        if (newpassword.equals(repassword)) {
            String encodedPassword = passwordEncoder.encode(newpassword);
            userDAO.updateUser(encodedPassword, userID);
            return "redirect:/login";
        } else {
            model.addAttribute("repassworderror", "Nhập lại mật khẩu không trùng khớp!");
            return "taikhoan/new-password";
        }
    }
}
