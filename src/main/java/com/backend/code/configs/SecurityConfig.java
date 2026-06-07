package com.backend.code.configs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
            .exceptionHandling(exceptions -> exceptions
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder
                            .getContext()
                            .getAuthentication();
                        String principal = authentication != null ? authentication.getName() : "anonymous";
                        Object authorities = authentication != null
                            ? authentication.getAuthorities().stream().map(granted -> granted.getAuthority()).toList()
                            : List.of();

                        log.warn(
                            "Acces refuse pour {} {} - principal={} - authorities={} - cause={}",
                            request.getMethod(),
                            request.getRequestURI(),
                            principal,
                            authorities,
                            accessDeniedException.getMessage());

                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acces refuse");
                    })
            )
            .authorizeHttpRequests(auth -> auth
            		
                    // ── Endpoints publics (aucune authentification requise) ──

                    // Login : forcément public, c'est le point d'entrée
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/error").permitAll()
                    // Console H2 pour le développement (à retirer en production !)
                    .requestMatchers("/h2-console/**").permitAll()
                    // Health checks, métriques (utile pour les outils de monitoring)
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                    
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // Login : public, c'est le point d'entree
                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                    // POST = creation d'un utilisateur -> ADMIN seulement
                    .requestMatchers(HttpMethod.POST, "/api/utilisateurs").hasRole("ADMIN")
                    // GET = liste des utilisateurs -> ADMIN seulement
                    .requestMatchers(HttpMethod.GET, "/api/utilisateurs").hasRole("ADMIN")
                    // GET = detail d'un utilisateur -> ADMIN seulement
                    .requestMatchers(HttpMethod.GET, "/api/utilisateurs/*").hasRole("ADMIN")
                    // PUT = modification d'un utilisateur -> ADMIN seulement
                    .requestMatchers(HttpMethod.PUT, "/api/utilisateurs/*").hasRole("ADMIN")
                    // DELETE = suppression d'un utilisateur -> ADMIN seulement
                    .requestMatchers(HttpMethod.DELETE, "/api/utilisateurs/*").hasRole("ADMIN")
                    // PATCH /activer = activation du compte -> ADMIN seulement
                    .requestMatchers(HttpMethod.PATCH, "/api/utilisateurs/*/activer").hasRole("ADMIN")
                    // PATCH /desactiver = desactivation du compte -> ADMIN seulement
                    .requestMatchers(HttpMethod.PATCH, "/api/utilisateurs/*/desactiver").hasRole("ADMIN")

                    // CRUD dedie des administrateurs -> ADMIN seulement
                    .requestMatchers(HttpMethod.POST, "/api/administrateurs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/administrateurs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/administrateurs/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/administrateurs/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/administrateurs/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/administrateurs/*/activer").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/administrateurs/*/desactiver").hasRole("ADMIN")

                    // CRUD dedie des exploitants -> ADMIN seulement
                    .requestMatchers(HttpMethod.POST, "/api/exploitants").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/exploitants").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/exploitants/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/exploitants/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/exploitants/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/exploitants/*/activer").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/exploitants/*/desactiver").hasRole("ADMIN")

                    // POST = creation d'une ville -> ADMIN seulement
                    .requestMatchers(HttpMethod.POST, "/api/villes").hasRole("ADMIN")
                    // PUT = modification d'une ville -> ADMIN seulement
                    .requestMatchers(HttpMethod.PUT, "/api/villes/*").hasRole("ADMIN")
                    // DELETE = suppression d'une ville -> ADMIN seulement
                    .requestMatchers(HttpMethod.DELETE, "/api/villes/*").hasRole("ADMIN")
                    // GET = lecture des villes -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/villes").hasAnyRole("ADMIN", "EXPLOITANT")
                    // GET = detail d'une ville -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/villes/*").hasAnyRole("ADMIN", "EXPLOITANT")

                    // POST = creation d'un champ -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.POST, "/api/champs").hasAnyRole("ADMIN", "EXPLOITANT")
                    // PUT = modification d'un champ -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.PUT, "/api/champs/*").hasAnyRole("ADMIN", "EXPLOITANT")
                    // DELETE = suppression d'un champ -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.DELETE, "/api/champs/*").hasAnyRole("ADMIN", "EXPLOITANT")
                    // GET = lecture des champs -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/champs").hasAnyRole("ADMIN", "EXPLOITANT")
                    // GET = detail d'un champ -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/champs/*").hasAnyRole("ADMIN", "EXPLOITANT")

                    // POST = creation d'une intervention -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.POST, "/api/interventions").hasAnyRole("ADMIN", "EXPLOITANT")
                    // PUT = modification d'une intervention -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.PUT, "/api/interventions/*").hasAnyRole("ADMIN", "EXPLOITANT")
                    // DELETE = suppression d'une intervention -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.DELETE, "/api/interventions/*").hasAnyRole("ADMIN", "EXPLOITANT")
                    // GET = lecture des interventions -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/interventions").hasAnyRole("ADMIN", "EXPLOITANT")
                    // GET = detail d'une intervention -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/interventions/*").hasAnyRole("ADMIN", "EXPLOITANT")

                    // POST = creation d'un commentaire -> ADMIN seulement
                    .requestMatchers(HttpMethod.POST, "/api/commentaires").hasRole("ADMIN")
                    // DELETE = suppression d'un commentaire -> ADMIN seulement
                    .requestMatchers(HttpMethod.DELETE, "/api/commentaires/*").hasRole("ADMIN")
                    // GET = lecture des commentaires -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/commentaires").hasAnyRole("ADMIN", "EXPLOITANT")
                    // GET = detail d'un commentaire -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/commentaires/*").hasAnyRole("ADMIN", "EXPLOITANT")

                    // GET = dashboard exploitant -> ADMIN ou EXPLOITANT
                    .requestMatchers(HttpMethod.GET, "/api/statistiques/exploitant/*").hasAnyRole("ADMIN", "EXPLOITANT")

                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}