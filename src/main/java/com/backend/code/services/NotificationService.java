package com.backend.code.services;

import java.util.List;

import com.backend.code.dtos.NotificationResponseDTO;
import com.backend.code.entity.tables.Commentaire;
import com.backend.code.entity.tables.Intervention;

public interface NotificationService {

    List<NotificationResponseDTO> getByUtilisateurId(String utilisateurId);

    NotificationResponseDTO markAsRead(String notificationId);

    List<NotificationResponseDTO> markAllAsReadByUtilisateurId(String utilisateurId);

    void delete(String notificationId);

    void deleteAllByUtilisateurId(String utilisateurId);

    void notifyAdministrateursForInterventionSubmission(Intervention intervention);

    void notifyExploitantForCommentaire(Commentaire commentaire);
}