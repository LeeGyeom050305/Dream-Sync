package com.example.backend.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
//    @Value("${auth.secretKey}")
//    private String secretKeyStr;
//
//    @Value("${auth.tokenExpiry}")
//    private long tokenExpiry;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(
                        "/h2-console/**",
                        "/swagger-ui/**",
                        "/v3/api-docs", "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/actuator/**",
                        "/", "/index.html", "/manifest.json", "/static/**", "/*.png", "/error", "/*.ico", "/reports/**",
                        "/api/test/**", "/user/login"
                );
    }

}
