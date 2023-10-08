package com.nhutin.electric_project.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CookieUtils {
	// Tạo và gửi cookie về client để lưu
	public static Cookie add(String name, String value, int hours, HttpServletResponse resp) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath("/");
		resp.addCookie(cookie);
		return cookie;
	}

	// Đọc giá trị cookie gửi từ client
	public static String get(String name, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
	public static void delete(String name, HttpServletResponse resp, HttpServletRequest req) {
		Cookie[] cookie = req.getCookies();
		
		if (cookie != null) {
			for(Cookie c : cookie) {
				if (c.getName().equals(name)) {
					c.setMaxAge(0);
					c.setValue("");
					c.setPath("/");
					resp.addCookie(c);
					break;
				}
			}
		}
	}
}
