package com.nhutin.electric_project.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nhutin.electric_project.model.CustomOAuth2User; // Sử dụng CustomOAuth2User thay vì DefaultOidcUser
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.security.service.UserDetailsServiceImpl;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    HttpSession session;

    @Autowired
    private UserRepository nguoiDungDAO;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal(); // Ép kiểu thành CustomOAuth2User

        String email = oauthUser.getEmail(); // Sử dụng getter của CustomOAuth2User
        String name = oauthUser.getFullName();
        String phone = oauthUser.getPhoneNumber();

        UserDetails userDetails;
        if (nguoiDungDAO.findByEmail(email).isEmpty()) {
            userDetails = userService.createUserAfterOAuthLogin(email, name, phone);
        } else {
            User user = nguoiDungDAO.findByEmail(email).get();
            userDetails = userService.updateUserAfterOAuthLogin(user, name);
        }
        session.setAttribute("tenDangNhapLogin", email); // Sử dụng biến email thay vì oauthUser.getAttribute("email")
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        System.out.println("Customer's Email: " + email);

        super.setDefaultTargetUrl("/home");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
