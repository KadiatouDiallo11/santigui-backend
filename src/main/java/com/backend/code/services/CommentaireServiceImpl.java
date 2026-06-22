package com.backend.code.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.code.dtos.CommentaireRequestDTO;
import com.backend.code.dtos.CommentaireResponseDTO;
import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Commentaire;
import com.backend.code.entity.tables.Intervention;
import com.backend.code.repository.AdministrateurRepository;
import com.backend.code.repository.CommentaireRepository;
import com.backend.code.repository.InterventionRepository;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final InterventionRepository interventionRepository;
    private final AdministrateurRepository administrateurRepository;
        private final NotificationService notificationService;

    public CommentaireServiceImpl(
            CommentaireRepository commentaireRepository,
            InterventionRepository interventionRepository,
                        AdministrateurRepository administrateurRepository,
                        NotificationService notificationService) {

        this.commentaireRepository = commentaireRepository;
        this.interventionRepository = interventionRepository;
        this.administrateurRepository = administrateurRepository;
                this.notificationService = notificationService;
    }

    @Override
    public CommentaireResponseDTO create(CommentaireRequestDTO dto) {

        Intervention intervention = interventionRepository
                .findById(dto.getInterventionId())
                .orElseThrow(() ->
                        new RuntimeException("Intervention introuvable"));

        Administrateur redacteur = administrateurRepository
                .findById(dto.getRedacteurId())
                .orElseThrow(() ->
                        new RuntimeException("Administrateur introuvable"));

        Commentaire commentaire = new Commentaire();

        commentaire.setContenu(dto.getContenu());
        commentaire.setTypeCommentaire(dto.getTypeCommentaire());
        commentaire.setIntervention(intervention);
        commentaire.setRedacteur(redacteur);

        Commentaire saved = commentaireRepository.save(commentaire);

                notificationService.notifyExploitantForCommentaire(saved);

        return mapToDTO(saved);
    }

    @Override
    public List<CommentaireResponseDTO> getAll() {

        return commentaireRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentaireResponseDTO getById(String id) {

        Commentaire commentaire = commentaireRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Commentaire introuvable"));

        return mapToDTO(commentaire);
    }

    @Override
    public void delete(String id) {

        commentaireRepository.deleteById(id);
    }

    // ===== MAPPING =====

    private CommentaireResponseDTO mapToDTO(Commentaire commentaire) {

        CommentaireResponseDTO dto =
                new CommentaireResponseDTO();

        dto.setId(commentaire.getId());
        dto.setContenu(commentaire.getContenu());
        dto.setTypeCommentaire(commentaire.getTypeCommentaire());
        dto.setDatePublication(commentaire.getDatePublication());

        dto.setInterventionId(
                commentaire.getIntervention().getId());

        dto.setRedacteurNom(
                commentaire.getRedacteur().getNom());

        return dto;
    }
}