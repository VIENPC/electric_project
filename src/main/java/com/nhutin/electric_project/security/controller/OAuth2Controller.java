package com.nhutin.electric_project.security.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    @GetMapping("/loginSuccess")
    public String loginSuccess(OAuth2AuthenticationToken authentication) {
        // Xử lý logic sau khi đăng nhập thành công
        return "redirect:/index";
    }
}

