package com.nhutin.electric_project.security.handler;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nhutin.electric_project.config.CookieUtils;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.security.service.UserDetailsServiceImpl;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	HttpSession session;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsServiceImpl userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println("Customer's OAuth2User: " + oauth2User);

		String email = oauth2User.getAttribute("email");
		String name = oauth2User.getAttribute("name");

		// Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu hay chưa
		Optional<User> existingUser = userRepository.findByEmail(email);
		UserDetails userDetails;
		if (existingUser.isPresent()) {
			// Nếu tồn tại, cập nhật thông tin người dùng
			userDetails = userService.updateUserAfterOAuthLogin(existingUser.get(), name);
		} else {
			// Nếu không tồn tại, tạo một tài khoản người dùng mới
			userDetails = userService.createUserAfterOAuthLogin(email, name, null);
		}

		// Lưu thông tin đăng nhập vào session và cookie
		session.setAttribute("tenDangNhapLogin", email);
		Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		CookieUtils.add("tenDangNhapCookie", email, 7 * 24, response);
		System.out.println("Customer's Email: " + email);

		// Đặt trang mặc định sau khi đăng nhập thành công
		super.setDefaultTargetUrl("/home");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
