package com.backend.code.entity.tables;

import java.time.LocalDate;

import com.backend.code.entity.enums.TypeCommentaire;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="commentaires")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;
    private LocalDate dateCommentaire = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private TypeCommentaire typeCommentaire;

    @ManyToOne
    @JoinColumn(name = "intervention_id")
    private Intervention intervention;

    @ManyToOne
    @JoinColumn(name = "administrateur_id")
    private Administrateur redacteur;
}