package com.backend.code.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.code.dtos.AdministrateurRequestDTO;
import com.backend.code.dtos.ExploitantRequestDTO;
import com.backend.code.dtos.UtilisateurRequestDTO;
import com.backend.code.dtos.UtilisateurResponseDTO;
import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Exploitant;
import com.backend.code.entity.tables.Utilisateur;
import com.backend.code.repository.AdministrateurRepository;
import com.backend.code.repository.ExploitantRepository;
import com.backend.code.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repo;
    private final AdministrateurRepository administrateurRepository;
    private final ExploitantRepository exploitantRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(
            UtilisateurRepository repo,
            AdministrateurRepository administrateurRepository,
            ExploitantRepository exploitantRepository,
            PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.administrateurRepository = administrateurRepository;
        this.exploitantRepository = exploitantRepository;
		this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtilisateurResponseDTO createUser(UtilisateurRequestDTO dto) {
        if ("ADMIN".equalsIgnoreCase(dto.type)) {
            AdministrateurRequestDTO adminDto = new AdministrateurRequestDTO();
            adminDto.setNom(dto.getNom());
            adminDto.setPrenom(dto.getPrenom());
            adminDto.setEmail(dto.getEmail());
            adminDto.setPassword(dto.getPassword());
            adminDto.setNiveau(dto.getNiveau());
            return createAdministrateur(adminDto);
        }

        ExploitantRequestDTO exploitantDto = new ExploitantRequestDTO();
        exploitantDto.setNom(dto.getNom());
        exploitantDto.setPrenom(dto.getPrenom());
        exploitantDto.setEmail(dto.getEmail());
        exploitantDto.setPassword(dto.getPassword());
        exploitantDto.setNumeroExploitation(dto.getNumeroExploitation());
        exploitantDto.setTelephone(dto.getTelephone());
        exploitantDto.setAdresse(dto.getAdresse());
        return createExploitant(exploitantDto);
    }

    @Override
    public UtilisateurResponseDTO createAdministrateur(AdministrateurRequestDTO dto) {
        Administrateur admin = new Administrateur();
        applyBaseFields(admin, dto.getNom(), dto.getPrenom(), dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setNiveau(dto.getNiveau());
        return toDTO(administrateurRepository.save(admin));
    }

    @Override
    public UtilisateurResponseDTO createExploitant(ExploitantRequestDTO dto) {
        Exploitant exploitant = new Exploitant();
        applyBaseFields(exploitant, dto.getNom(), dto.getPrenom(), dto.getEmail());
        exploitant.setPassword(passwordEncoder.encode(dto.getPassword()));
        exploitant.setNumeroExploitation(dto.getNumeroExploitation());
        exploitant.setTelephone(dto.getTelephone());
        exploitant.setAdresse(dto.getAdresse());
        return toDTO(exploitantRepository.save(exploitant));
    }

    @Override
    public List<UtilisateurResponseDTO> findAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurResponseDTO> findAllAdministrateurs() {
        return administrateurRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurResponseDTO> findAllExploitants() {
        return exploitantRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UtilisateurResponseDTO findById(String id) {
        return repo.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @Override
    public UtilisateurResponseDTO findAdministrateurById(String id) {
        return toDTO(findAdministrateur(id));
    }

    @Override
    public UtilisateurResponseDTO findExploitantById(String id) {
        return toDTO(findExploitant(id));
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAdministrateur(String id) {
        administrateurRepository.delete(findAdministrateur(id));
    }

    @Override
    public void deleteExploitant(String id) {
        exploitantRepository.delete(findExploitant(id));
    }

    private UtilisateurResponseDTO toDTO(Utilisateur u) {
        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();

        dto.id = u.getId();
        dto.nom = u.getNom();
        dto.prenom = u.getPrenom();
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

        applyBaseFields(existing, dto.getNom(), dto.getPrenom(), dto.getEmail());

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
    public UtilisateurResponseDTO updateAdministrateur(String id, AdministrateurRequestDTO dto) {
        Administrateur admin = findAdministrateur(id);
        applyBaseFields(admin, dto.getNom(), dto.getPrenom(), dto.getEmail());

        if (dto.getNiveau() != null) {
            admin.setNiveau(dto.getNiveau());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return toDTO(administrateurRepository.save(admin));
    }

    @Override
    public UtilisateurResponseDTO updateExploitant(String id, ExploitantRequestDTO dto) {
        Exploitant exploitant = findExploitant(id);
        applyBaseFields(exploitant, dto.getNom(), dto.getPrenom(), dto.getEmail());

        if (dto.getNumeroExploitation() != null) {
            exploitant.setNumeroExploitation(dto.getNumeroExploitation());
        }

        if (dto.getTelephone() != null) {
            exploitant.setTelephone(dto.getTelephone());
        }

        if (dto.getAdresse() != null) {
            exploitant.setAdresse(dto.getAdresse());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            exploitant.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return toDTO(exploitantRepository.save(exploitant));
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
    public UtilisateurResponseDTO activerAdministrateur(String id) {
        Administrateur admin = findAdministrateur(id);

        if (admin.isActif()) {
            throw new RuntimeException("Le compte est déjà actif.");
        }

        admin.setActif(true);
        return toDTO(administrateurRepository.save(admin));
    }

    @Override
    public UtilisateurResponseDTO activerExploitant(String id) {
        Exploitant exploitant = findExploitant(id);

        if (exploitant.isActif()) {
            throw new RuntimeException("Le compte est déjà actif.");
        }

        exploitant.setActif(true);
        return toDTO(exploitantRepository.save(exploitant));
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

    @Override
    public UtilisateurResponseDTO desactiverAdministrateur(String id) {
        Administrateur admin = findAdministrateur(id);

        if (!admin.isActif()) {
            throw new RuntimeException("Le compte est déjà désactivé.");
        }

        admin.setActif(false);
        return toDTO(administrateurRepository.save(admin));
    }

    @Override
    public UtilisateurResponseDTO desactiverExploitant(String id) {
        Exploitant exploitant = findExploitant(id);

        if (!exploitant.isActif()) {
            throw new RuntimeException("Le compte est déjà désactivé.");
        }

        exploitant.setActif(false);
        return toDTO(exploitantRepository.save(exploitant));
    }

    private void applyBaseFields(Utilisateur utilisateur, String nom, String prenom, String email) {
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
    }

    private Administrateur findAdministrateur(String id) {
        return administrateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));
    }

    private Exploitant findExploitant(String id) {
        return exploitantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exploitant introuvable"));
    }
}