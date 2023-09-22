package com.nhutin.electric_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.security.SecureRandom;

import javax.sql.DataSource;

@Configuration
public class RememberMeKeyConfig {

    // @Bean
    // public String rememberMeKey() {
    //     SecureRandom secureRandom = new SecureRandom();
    //     byte[] key = new byte[16]; // 128 bits
    //     secureRandom.nextBytes(key);

    //     StringBuilder rememberMeKey = new StringBuilder();
    //     for (byte b : key) {
    //         rememberMeKey.append(String.format("%02x", b));
    //     }

    //     return rememberMeKey.toString();
    // }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}

