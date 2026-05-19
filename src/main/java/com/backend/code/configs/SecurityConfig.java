package com.backend.code.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // ✅ Désactive CSRF pour H2
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable()) // ✅ Autorise les iframes (H2 en a besoin)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // ✅ Accès libre à H2
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}