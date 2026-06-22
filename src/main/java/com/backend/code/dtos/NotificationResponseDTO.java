package com.backend.code.dtos;

import java.time.LocalDateTime;

import com.backend.code.entity.enums.TypeNotification;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representation d'une notification utilisateur")
public class NotificationResponseDTO {

    @Schema(description = "Identifiant unique de la notification", example = "notif-123")
    private String id;

    @Schema(description = "Identifiant du destinataire", example = "user-123")
    private String utilisateurId;

    @Schema(description = "Type du destinataire", example = "EXPLOITANT")
    private String typeUtilisateur;

    @Schema(description = "Titre court de la notification", example = "Nouvelle fiche soumise")
    private String titre;

    @Schema(description = "Message detaille affiche a l'utilisateur", example = "L'exploitant Kouassi a soumis une nouvelle fiche d'intervention.")
    private String message;

    @Schema(description = "Type fonctionnel de la notification", example = "FICHE_SOUMISE", implementation = TypeNotification.class)
    private TypeNotification typeNotification;

    @Schema(description = "Indique si la notification a ete lue", example = "false")
    private boolean lue;

    @Schema(description = "Identifiant de la fiche d'intervention liee, si disponible", example = "interv-123")
    private String interventionId;

    @Schema(description = "Identifiant du commentaire lie, si disponible", example = "comment-123")
    private String commentaireId;

    @Schema(description = "Date de creation de la notification", example = "2026-06-22T09:15:30")
    private LocalDateTime dateCreation;

    @Schema(description = "Date de lecture de la notification", example = "2026-06-22T10:00:00")
    private LocalDateTime dateLecture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    public boolean isLue() {
        return lue;
    }

    public void setLue(boolean lue) {
        this.lue = lue;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public String getCommentaireId() {
        return commentaireId;
    }

    public void setCommentaireId(String commentaireId) {
        this.commentaireId = commentaireId;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateLecture() {
        return dateLecture;
    }

    public void setDateLecture(LocalDateTime dateLecture) {
        this.dateLecture = dateLecture;
    }
}