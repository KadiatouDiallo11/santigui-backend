package com.backend.code.entity.tables;

import java.util.List;

import com.backend.code.entity.enums.NiveauAdmin;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrateur extends Utilisateur {

    @Enumerated(EnumType.STRING)
    private NiveauAdmin niveau;

    @OneToMany(mappedBy = "redacteur", cascade = CascadeType.ALL)
    private List<Commentaire> commentairesRediges;

    public NiveauAdmin getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauAdmin niveau) {
        this.niveau = niveau;
    }

    public List<Commentaire> getCommentairesRediges() {
        return commentairesRediges;
    }

    public void setCommentairesRediges(List<Commentaire> commentairesRediges) {
        this.commentairesRediges = commentairesRediges;
    }
}