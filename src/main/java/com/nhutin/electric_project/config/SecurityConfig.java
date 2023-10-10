package com.nhutin.electric_project.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.util.UrlPathHelper;

import com.nhutin.electric_project.model.Cart;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.cartsRepository;
import com.nhutin.electric_project.security.handler.CustomAuthenticationFailureHandler;
import com.nhutin.electric_project.security.handler.OAuth2LoginSuccessHandler;
import com.nhutin.electric_project.security.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    cartsRepository cartDAO;

    @Autowired
    UserRepository userDAO;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
                "uniqueAndSecretKey", userDetailsService);
        rememberMeServices.setTokenValiditySeconds(86400); // 1 day
        return rememberMeServices;
    }

    private String determineTargetUrl(Authentication authentication) {
        String role = authentication.getAuthorities().toString();
        if (role.contains("ADMIN")) {
            return "/admin/index";
        } else {
            return "/home";
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        http
                .authorizeRequests(requests -> requests
                        .antMatchers("/assets/**", "/", "logout", "/login**", "/home", "/shop",
                                "/error**", "/api/**", "/reset-password", "/codeVerification", "/resendOtp",
                                "/new-password", "/rest/productdetails", "/rest/products", "/rest/productsbycate/**",
                                "/rest/products/**", "/rest/**", "/product", "/registration/**", "/oauth2/**")
                        .permitAll()
                        .antMatchers("/admin/**", "/rest/orders/**")
                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            // Lấy thông tin đăng nhập của người dùng, ví dụ: email
                            String email = request.getParameter("email");

                            // Lưu cookie với thông tin đăng nhập
                            CookieUtils.add("tenDangNhapCookie", email, 7 * 24, response);

                            // Lấy giá trị email từ cookie
                            String emailFromCookie = CookieUtils.get("tenDangNhapCookie", request);

                            // Kiểm tra xem có giá trị email từ cookie hay không
                            if (!emailFromCookie.isEmpty()) {
                                System.out.println("Email từ cookie: " + emailFromCookie);
                            } else {
                                System.out.println("Cookie email không tồn tại hoặc rỗng.");
                            }

                            String targetUrl = determineTargetUrl(authentication);
                            response.sendRedirect(targetUrl);
                        })
                        .failureHandler(authenticationFailureHandler)
                        .usernameParameter("email")
                        .permitAll())
                .rememberMe(rememberMe -> rememberMe
                        .key("uniqueAndSecretKey")
                        .rememberMeServices(rememberMeServices())
                        .tokenValiditySeconds(604800))
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/access-denied")
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect("/login?error=true");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendRedirect("/access-denied");
                        }))
                .oauth2Login(t -> t
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .userInfoEndpoint().userService(oAuth2UserService).and()
                        .successHandler(oAuth2LoginSuccessHandler)
                        .permitAll().permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/home")
                        .logoutSuccessHandler(
                                new LogoutSuccessHandler() {
                                    @Override
                                    public void onLogoutSuccess(
                                            HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication)
                                            throws IOException,
                                            ServletException {
                                        UrlPathHelper helper = new UrlPathHelper();
                                        String context = helper.getContextPath(
                                                request);

                                        response.sendRedirect(context
                                                + "/login");
                                    }
                                })
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll());
    }
}
