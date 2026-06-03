package com.backend.code.configs;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Utilisateur;
import com.backend.code.repository.UtilisateurRepository;
import com.backend.code.services.JwtService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtService jwtService;
    private final UtilisateurRepository userRepo;

    


	public JwtAuthFilter(JwtService jwtService, UtilisateurRepository userRepo) {
		super();
		this.jwtService = jwtService;
		this.userRepo = userRepo;
	}


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug("Aucun bearer token pour {} {}", request.getMethod(), request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);

        try {
            final String email = jwtService.extractUsername(token);
            log.debug("Token JWT detecte pour {} {}", request.getMethod(), request.getRequestURI());

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.debug("Utilisateur extrait du token: {}", email);
                Utilisateur user = userRepo.findByEmail(email).orElse(null);

                if (user != null && jwtService.isTokenValid(token, user)) {
                    String role = user instanceof Administrateur ? "ROLE_ADMIN" : "ROLE_EXPLOITANT";
                    UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(new SimpleGrantedAuthority(role))
                        );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info(
                        "Authentification JWT etablie pour {} avec le role {} via {}",
                        email,
                        role,
                        user.getClass().getSimpleName()
                    );
                    log.debug(
                        "Autorites courantes pour {}: {}",
                        email,
                        authToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                    );
                } else if (user == null) {
                    log.warn("Aucun utilisateur trouve pour le token JWT associe a {}", email);
                } else {
                    log.warn("Token JWT invalide ou utilisateur inactif pour {}", email);
                }
            }
        } catch (JwtException | IllegalArgumentException exception) {
            SecurityContextHolder.clearContext();
            log.warn("JWT invalide ou expire pour {} {}: {}",
                request.getMethod(),
                request.getRequestURI(),
                exception.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}