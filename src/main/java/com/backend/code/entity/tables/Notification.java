package com.backend.code.entity.tables;

import java.time.LocalDateTime;

import com.backend.code.entity.enums.TypeNotification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false, length = 150)
    private String titre;

    @Column(nullable = false, length = 2000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TypeNotification typeNotification;

    @Column(nullable = false)
    private boolean lue;

    private String interventionId;

    private String commentaireId;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    private LocalDateTime dateLecture;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.lue = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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