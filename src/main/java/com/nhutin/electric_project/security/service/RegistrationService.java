package com.nhutin.electric_project.security.service;

import java.sql.Timestamp;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nhutin.electric_project.model.Cart;
import com.nhutin.electric_project.model.ConfirmationCode;
import com.nhutin.electric_project.model.RegistrationRequest;
import com.nhutin.electric_project.model.Role;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.ConfirmationCodeDAO;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.service.OtpService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    OtpService otpService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    ConfirmationCodeDAO confirmationCodeDAO;

    public String register(RegistrationRequest request) {
        String token = userDetailsServiceImpl.signUpUser(
                new User(request.getUsername(),
                        request.getFullname(),
                        request.getGender(),
                        request.getBirthday(),
                        request.getEmail(),
                        request.getPhonenumber(),
                        request.getPassword(),
                        request.getAddress(),
                        Role.USER));

        String link = "http://localhost:8080/registration/confirm?token=" + token;

        try {
            String subject = "XÁC NHẬN EMAIL - NHUTIN";

            MimeMessage messageMime = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(messageMime, true);

            helper.setTo(request.getEmail());
            helper.setSubject(subject);

            Context context = new Context();
            context.setVariable("recipientName", request.getUsername());
            context.setVariable("confirmationLink", link);

            String processedTemplate = templateEngine.process("email-template", context);

            helper.setText(processedTemplate, true);

            javaMailSender.send(messageMime);
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }

        return "token";
    }

    @Autowired
    UserRepository userDAO;

    // @Transactional
    public String confirmToken(String token) {
        ConfirmationCode confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy mã xác nhận."));
        if (userDAO.findByConfirmationCode(confirmationToken).get().getLoginPermission()) {
            throw new IllegalStateException("Email đã được xác nhận.");
        }

        Timestamp expiredAt = confirmationToken.getOTPExpirationDate();

        User user = confirmationCodeDAO.findByOTPCode(token).get().getUser();
        String newToken = UUID.randomUUID().toString();
        // Đặt loginPermission thành true
        user.setLoginPermission(true);

        // Lưu đối tượng User đã thay đổi
        userDAO.save(user);
        System.out.println(user.getEmail());

        // Lấy ngày giờ hiện tại
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        // Thêm 15 phút
        long millisecondsIn15Minutes = 15 * 60 * 1000; // 15 phút * 60 giây/phút * 1000 mili giây/giây
        Timestamp newTimestamp = new Timestamp(currentTimestamp.getTime() + millisecondsIn15Minutes);
        // Send confirmation token

        ConfirmationCode newConfirmationCode = confirmationCodeDAO.findByUserID(user.getUserID()).get();
        newConfirmationCode.setOTPCode(newToken);
        newConfirmationCode.setOTPCreationDate(currentTimestamp);
        newConfirmationCode.setOTPExpirationDate(newTimestamp);

        confirmationCodeDAO.save(newConfirmationCode);

        if (expiredAt.before(new Timestamp(System.currentTimeMillis()))) {
            String link = "http://localhost:8080/registration/confirm?token=" + newToken;

            try {
                String subject = "XÁC NHẬN EMAIL - NHUTIN";

                MimeMessage messageMime = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(messageMime, true);

                helper.setTo(user.getEmail());
                helper.setSubject(subject);

                Context context = new Context();
                context.setVariable("recipientName", user.getUsername());
                context.setVariable("confirmationLink", link);

                String processedTemplate = templateEngine.process("email-template", context);

                helper.setText(processedTemplate, true);

                javaMailSender.send(messageMime);
            } catch (Exception e) {
                return "Error sending email: " + e.getMessage();
            }
            throw new IllegalStateException(
                    "Mã xác nhận này đã hết hạn. " +
                            "Mã xác nhận mới đã được gửi lại, vui lòng kiểm tra để xác nhận tài khoản.");
        }

        Cart cart = new Cart();
        cart.setUser(confirmationToken.getUser());

        confirmationTokenService.setConfirmedAt(token);

        userDetailsServiceImpl.enableUser(
                confirmationToken.getUser().getEmail());

        return "confirmed";
    }

}
