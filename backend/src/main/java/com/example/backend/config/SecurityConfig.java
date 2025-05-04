package com.example.backend.config;


import com.example.backend.exception.RestAuthenticationEntryPoint;
import com.example.backend.filter.TokenAuthenticationFilter;
import com.example.backend.header.TokenAccessDeniedHandler;
import com.example.backend.service.AuthTokenProvider;
import com.example.backend.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;

    @Value("${auth.secretKey}")
    private String secretKeyStr;

    @Value("${auth.tokenExpiry}")
    private long tokenExpiry;

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
                        "/api/test/**", "/users/login", "/users/signUp"
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/actuator/**").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new TokenAuthenticationFilter(customUserDetailsService, authTokenProvider(), getRequestAttributeSecurityContextRepository()),
                        UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(tokenAccessDeniedHandler)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthTokenProvider authTokenProvider() {
        return new AuthTokenProvider(secretKeyStr, tokenExpiry, customUserDetailsService);
    }

    @Bean
    public RequestAttributeSecurityContextRepository getRequestAttributeSecurityContextRepository() {
        return new RequestAttributeSecurityContextRepository();
    }
}
