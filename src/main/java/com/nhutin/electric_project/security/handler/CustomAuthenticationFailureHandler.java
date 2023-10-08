package com.nhutin.electric_project.security.handler;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMessage = "";

		if (exception instanceof DisabledException) {
			errorMessage = "Tài khoản của bạn chưa được kích hoạt.</br>Kiểm tra email và xác nhận kích hoạt tài khoản.";
		} else if (exception instanceof BadCredentialsException) {
			errorMessage = "Sai thông tin đăng nhập. Vui lòng kiểm tra lại.";
		} else if (exception instanceof AccountExpiredException) {
			errorMessage = "Tài khoản của bạn đã hết hạn.";
		} else if (exception instanceof LockedException) {
			errorMessage = "Tài khoản của bạn đã bị khóa.</br>Kiểm tra email để xác nhận tạo lại mật khẩu mới";
		} else {
			System.out.println(exception);
			errorMessage = "Đăng nhập không thành công. Vui lòng thử lại.";
		}

		String encodedMessage = errorMessage;
		String decodedMessage = URLEncoder.encode(encodedMessage, "UTF-8");

		// Chuyển hướng người dùng đến trang đăng nhập với thông báo lỗi
		response.sendRedirect("/login?error=true&message=" + decodedMessage);
	}
}
