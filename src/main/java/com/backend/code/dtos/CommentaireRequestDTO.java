
package com.backend.code.dtos;

import com.backend.code.entity.enums.TypeCommentaire;

public class CommentaireRequestDTO {

    private String contenu;

    private TypeCommentaire typeCommentaire;

    private String interventionId;

    private String redacteurId;

    // ===== GETTERS =====

    public String getContenu() {
        return contenu;
    }

    public TypeCommentaire getTypeCommentaire() {
        return typeCommentaire;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public String getRedacteurId() {
        return redacteurId;
    }

    // ===== SETTERS =====

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setTypeCommentaire(TypeCommentaire typeCommentaire) {
        this.typeCommentaire = typeCommentaire;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public void setRedacteurId(String redacteurId) {
        this.redacteurId = redacteurId;
    }
}
