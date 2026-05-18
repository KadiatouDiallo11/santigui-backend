package com.backend.code.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.code.dtos.LoginRequestDTO;
import com.backend.code.dtos.LoginResponseDTO;
import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Utilisateur;
import com.backend.code.repository.UtilisateurRepository;

@Service
public class AuthService {

    private final UtilisateurRepository repo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UtilisateurRepository repo, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        Utilisateur user = repo.findByEmail(dto.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔐 CHECK PASSWORD (IMPORTANT)
        if (!passwordEncoder.matches(dto.password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        LoginResponseDTO res = new LoginResponseDTO();
        res.token = jwtService.generateToken(user);
        res.email = user.getEmail();
        res.type = (user instanceof Administrateur) ? "ADMIN" : "EXPLOITANT";

        return res;
    }
}