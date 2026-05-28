
package com.backend.code.dtos;

import java.time.LocalDateTime;

import com.backend.code.entity.enums.TypeCommentaire;

public class CommentaireResponseDTO {

    private String id;
    private String contenu;
    private TypeCommentaire typeCommentaire;
    private LocalDateTime datePublication;
    private String interventionId;
    private String redacteurNom;

    public CommentaireResponseDTO() {
    }

    // ===== GETTERS =====

    public String getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public TypeCommentaire getTypeCommentaire() {
        return typeCommentaire;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public String getRedacteurNom() {
        return redacteurNom;
    }

    // ===== SETTERS =====

    public void setId(String id) {
        this.id = id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setTypeCommentaire(TypeCommentaire typeCommentaire) {
        this.typeCommentaire = typeCommentaire;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public void setRedacteurNom(String redacteurNom) {
        this.redacteurNom = redacteurNom;
    }
}
