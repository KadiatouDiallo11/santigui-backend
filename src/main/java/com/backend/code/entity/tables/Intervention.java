package com.backend.code.entity.tables;

import com.backend.code.entity.enums.StatutIntervention;
import com.backend.code.entity.enums.TypeIntervention;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // 🔥 Champ concerné
    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;

    // 🔥 Auteur
    @ManyToOne
    @JoinColumn(name = "exploitant_id")
    private Exploitant exploitant;

    // 🔥 Date intervention
    private LocalDate dateIntervention;

    // 🔥 Type intervention
    @Enumerated(EnumType.STRING)
    private TypeIntervention typeIntervention;

    // 🔥 Description libre
    private String natureAction;

    // 🔥 Produits utilisés
    private String produitsUtilises;

    // 🔥 Durée en minutes
    private Integer duree;

    // 🔥 Observations
    @Column(length = 2000)
    private String observations;

    // 🔥 Statut
    @Enumerated(EnumType.STRING)
    private StatutIntervention statut;

    // 🔥 Dates automatiques
    private LocalDateTime dateCreation;

    private LocalDateTime dateModification;

    public Intervention() {
    }

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateModification = LocalDateTime.now();
    }

    // ================= GETTERS =================

    public String getId() {
        return id;
    }

    public Champ getChamp() {
        return champ;
    }

    public Exploitant getExploitant() {
        return exploitant;
    }

    public LocalDate getDateIntervention() {
        return dateIntervention;
    }

    public TypeIntervention getTypeIntervention() {
        return typeIntervention;
    }

    public String getNatureAction() {
        return natureAction;
    }

    public String getProduitsUtilises() {
        return produitsUtilises;
    }

    public Integer getDuree() {
        return duree;
    }

    public String getObservations() {
        return observations;
    }

    public StatutIntervention getStatut() {
        return statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    // ================= SETTERS =================

    public void setId(String id) {
        this.id = id;
    }

    public void setChamp(Champ champ) {
        this.champ = champ;
    }

    public void setExploitant(Exploitant exploitant) {
        this.exploitant = exploitant;
    }

    public void setDateIntervention(LocalDate dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public void setTypeIntervention(TypeIntervention typeIntervention) {
        this.typeIntervention = typeIntervention;
    }

    public void setNatureAction(String natureAction) {
        this.natureAction = natureAction;
    }

    public void setProduitsUtilises(String produitsUtilises) {
        this.produitsUtilises = produitsUtilises;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setStatut(StatutIntervention statut) {
        this.statut = statut;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
}