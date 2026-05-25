package com.backend.code.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.code.dtos.UtilisateurRequestDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;
import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Exploitant;
import com.backend.code.entity.tables.Utilisateur;
import com.backend.code.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository repo,  PasswordEncoder passwordEncoder) {
        this.repo = repo;
		this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtilisateurResponseDTO createUser(UtilisateurRequestDTO dto) {

        Utilisateur user;

        if ("ADMIN".equalsIgnoreCase(dto.type)) {
            Administrateur admin = new Administrateur();
            admin.setNiveau(dto.niveau);
            user = admin;

        } else {
            Exploitant exp = new Exploitant();
            exp.setAdresse(dto.adresse);
            exp.setTelephone(dto.telephone);
            exp.setNumeroExploitation(dto.numeroExploitation);
            user = exp;
        }

        user.setNom(dto.nom);
        user.setEmail(dto.email);
        user.setPassword(passwordEncoder.encode(dto.password));

        return toDTO(repo.save(user));
    }

    @Override
    public List<UtilisateurResponseDTO> findAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UtilisateurResponseDTO findById(String id) {
        return repo.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    private UtilisateurResponseDTO toDTO(Utilisateur u) {
        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();

        dto.id = u.getId();
        dto.nom = u.getNom();
        dto.email = u.getEmail();
        dto.actif = u.isActif();

        if (u instanceof Administrateur a) {
            dto.type = "ADMIN";
            dto.niveau = a.getNiveau() != null ? a.getNiveau().name() : null;
        }

        if (u instanceof Exploitant e) {
            dto.type = "EXPLOITANT";
            dto.adresse = e.getAdresse();
            dto.telephone = e.getTelephone();
            dto.numeroExploitation = e.getNumeroExploitation();
        }

        return dto;
    }

    @Override
    public UtilisateurResponseDTO update(String id, UtilisateurRequestDTO dto) {

        Utilisateur existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        // update base fields
        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());

        // ADMIN
        if (existing instanceof Administrateur admin) {

            if (dto.getNiveau() != null) {
                admin.setNiveau(dto.getNiveau());
            }
        }

        // EXPLOITANT
        if (existing instanceof Exploitant exp) {

            if (dto.getNumeroExploitation() != null) {
                exp.setNumeroExploitation(dto.getNumeroExploitation());
            }

            if (dto.getTelephone() != null) {
                exp.setTelephone(dto.getTelephone());
            }

            if (dto.getAdresse() != null) {
                exp.setAdresse(dto.getAdresse());
            }
        }
        
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return toDTO(repo.save(existing));
    }

    @Override
    public UtilisateurResponseDTO activerCompte(String id) {
        Utilisateur user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (user.isActif()) {
            throw new RuntimeException("Le compte est déjà actif.");
        }

        user.setActif(true);
        return toDTO(repo.save(user));
    }

    @Override
    public UtilisateurResponseDTO desactiverCompte(String id) {
        Utilisateur user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!user.isActif()) {
            throw new RuntimeException("Le compte est déjà désactivé.");
        }

        user.setActif(false);
        return toDTO(repo.save(user));
    }
}