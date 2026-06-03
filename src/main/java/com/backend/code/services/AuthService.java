package com.backend.code.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.code.dtos.LoginRequestDTO;
import com.backend.code.dtos.LoginResponseDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;
import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Exploitant;
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
            throw new RuntimeException("Mot de passe incorrect.");
        }

        // 🚫 CHECK COMPTE ACTIF
        if (!user.isActif()) {
            throw new RuntimeException("Ce compte est désactivé. Veuillez contacter un administrateur.");
        }

        LoginResponseDTO res = new LoginResponseDTO();
        res.token = jwtService.generateToken(user);
        res.email = user.getEmail();
        res.type = (user instanceof Administrateur) ? "ADMIN" : "EXPLOITANT";
        res.user = toDTO(user);

        return res;
    }

    private UtilisateurResponseDTO toDTO(Utilisateur user) {
        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();
        dto.id = user.getId();
        dto.nom = user.getNom();
        dto.prenom = user.getPrenom();
        dto.email = user.getEmail();
        dto.actif = user.isActif();

        if (user instanceof Administrateur admin) {
            dto.type = "ADMIN";
            dto.niveau = admin.getNiveau() != null ? admin.getNiveau().name() : null;
        }

        if (user instanceof Exploitant exploitant) {
            dto.type = "EXPLOITANT";
            dto.numeroExploitation = exploitant.getNumeroExploitation();
            dto.telephone = exploitant.getTelephone();
            dto.adresse = exploitant.getAdresse();
        }

        return dto;
    }
}