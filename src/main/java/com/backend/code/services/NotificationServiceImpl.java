package com.backend.code.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.code.dtos.NotificationResponseDTO;
import com.backend.code.entity.enums.TypeNotification;
import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Commentaire;
import com.backend.code.entity.tables.Intervention;
import com.backend.code.entity.tables.Notification;
import com.backend.code.entity.tables.Utilisateur;
import com.backend.code.repository.AdministrateurRepository;
import com.backend.code.repository.NotificationRepository;
import com.backend.code.repository.UtilisateurRepository;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AdministrateurRepository administrateurRepository;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository,
            UtilisateurRepository utilisateurRepository,
            AdministrateurRepository administrateurRepository) {

        this.notificationRepository = notificationRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.administrateurRepository = administrateurRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getByUtilisateurId(String utilisateurId) {

        validateUtilisateur(utilisateurId);

        return notificationRepository.findByUtilisateurIdOrderByDateCreationDesc(utilisateurId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDTO markAsRead(String notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification introuvable"));

        if (!notification.isLue()) {
            notification.setLue(true);
            notification.setDateLecture(LocalDateTime.now());
            notification = notificationRepository.save(notification);
        }

        return mapToDTO(notification);
    }

    @Override
    public List<NotificationResponseDTO> markAllAsReadByUtilisateurId(String utilisateurId) {

        validateUtilisateur(utilisateurId);

        List<Notification> notifications = notificationRepository
                .findByUtilisateurIdOrderByDateCreationDesc(utilisateurId);

        LocalDateTime now = LocalDateTime.now();
        boolean changed = false;

        for (Notification notification : notifications) {
            if (!notification.isLue()) {
                notification.setLue(true);
                notification.setDateLecture(now);
                changed = true;
            }
        }

        if (changed) {
            notificationRepository.saveAll(notifications);
        }

        return notifications.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String notificationId) {

        if (!notificationRepository.existsById(notificationId)) {
            throw new RuntimeException("Notification introuvable");
        }

        notificationRepository.deleteById(notificationId);
    }

    @Override
    public void deleteAllByUtilisateurId(String utilisateurId) {

        validateUtilisateur(utilisateurId);
        notificationRepository.deleteByUtilisateurId(utilisateurId);
    }

    @Override
    public void notifyAdministrateursForInterventionSubmission(Intervention intervention) {

        List<Administrateur> administrateurs = administrateurRepository.findAll();
        String nomExploitant = intervention.getExploitant().getNom();
        String titre = "Nouvelle fiche soumise";
        String message = "L'exploitant " + nomExploitant
                + " a soumis la fiche d'intervention " + intervention.getId() + ".";

        for (Administrateur administrateur : administrateurs) {
            createNotification(
                    administrateur,
                    titre,
                    message,
                    TypeNotification.FICHE_SOUMISE,
                    intervention.getId(),
                    null);
        }
    }

    @Override
    public void notifyExploitantForCommentaire(Commentaire commentaire) {

        Intervention intervention = commentaire.getIntervention();
        Utilisateur exploitant = intervention.getExploitant();
        String nomRedacteur = commentaire.getRedacteur().getNom();

        createNotification(
                exploitant,
                "Nouveau commentaire sur une fiche",
                "L'administrateur " + nomRedacteur
                        + " a commente votre fiche d'intervention " + intervention.getId() + ".",
                TypeNotification.COMMENTAIRE_RECU,
                intervention.getId(),
                commentaire.getId());
    }

    private void createNotification(
            Utilisateur utilisateur,
            String titre,
            String message,
            TypeNotification typeNotification,
            String interventionId,
            String commentaireId) {

        Notification notification = new Notification();
        notification.setUtilisateur(utilisateur);
        notification.setTitre(titre);
        notification.setMessage(message);
        notification.setTypeNotification(typeNotification);
        notification.setInterventionId(interventionId);
        notification.setCommentaireId(commentaireId);

        notificationRepository.save(notification);
    }

    private Utilisateur validateUtilisateur(String utilisateurId) {

        return utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    private NotificationResponseDTO mapToDTO(Notification notification) {

        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setUtilisateurId(notification.getUtilisateur().getId());
        dto.setTypeUtilisateur(resolveTypeUtilisateur(notification.getUtilisateur()));
        dto.setTitre(notification.getTitre());
        dto.setMessage(notification.getMessage());
        dto.setTypeNotification(notification.getTypeNotification());
        dto.setLue(notification.isLue());
        dto.setInterventionId(notification.getInterventionId());
        dto.setCommentaireId(notification.getCommentaireId());
        dto.setDateCreation(notification.getDateCreation());
        dto.setDateLecture(notification.getDateLecture());
        return dto;
    }

    private String resolveTypeUtilisateur(Utilisateur utilisateur) {

        if (utilisateur instanceof Administrateur) {
            return "ADMIN";
        }

        return "EXPLOITANT";
    }
}