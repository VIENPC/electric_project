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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
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
	private UserRepository nguoiDungDAO;

	@Autowired
	private UserDetailsServiceImpl userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String provider = determineProvider((OAuth2AuthenticationToken) authentication); // Xác định nhà cung cấp

		if ("Google".equals(provider)) {
			// Xử lý đăng nhập bằng Google
			DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
			String email = oidcUser.getAttribute("email");
			String name = oidcUser.getFullName();
			String phone = oidcUser.getPhoneNumber();

			UserDetails userDetails;
			if (nguoiDungDAO.findByEmail(email).isEmpty()) {
				userDetails = userService.createUserAfterOAuthLogin(email, name, phone);
			} else {
				User user = nguoiDungDAO.findByEmail(email).get();
				userDetails = userService.updateUserAfterOAuthLogin(user, name);
			}
			session.setAttribute("tenDangNhapLogin", oidcUser.getAttribute("email"));
			Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuthentication);
			CookieUtils.add("tenDangNhapCookie", email, 7 * 24, response);

		} else if ("Facebook".equals(provider)) {
			// Xử lý đăng nhập bằng Facebook
			DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
			String email = oAuth2User.getAttribute("email");
			String name = oAuth2User.getAttribute("name");

			UserDetails userDetails;
			if (nguoiDungDAO.findByEmail(email).isEmpty()) {
				userDetails = userService.createUserAfterOAuthLogin(email, name, null);
			} else {
				User user = nguoiDungDAO.findByEmail(email).get();
				userDetails = userService.updateUserAfterOAuthLogin(user, name);
			}
			session.setAttribute("tenDangNhapLogin", email);
			Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuthentication);
			CookieUtils.add("tenDangNhapCookie", email, 7 * 24, response);
		}

		super.setDefaultTargetUrl("/home");
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private String determineProvider(Authentication authentication) {
		Object principal = authentication.getPrincipal();

		if (principal instanceof DefaultOidcUser) {
			System.out.println("Google");
			return "Google"; // Đây là đăng nhập bằng Google
		} else if (principal instanceof DefaultOAuth2User) {
			System.out.println("Facebook");
			return "Facebook"; // Đây là đăng nhập bằng Facebook
		} else {
			return "Unknown"; // Không xác định được nhà cung cấp
		}
	}
}
