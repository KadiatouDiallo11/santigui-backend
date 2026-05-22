package com.backend.code.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.backend.code.entity.enums.StatutIntervention;
import com.backend.code.entity.enums.TypeIntervention;

public class InterventionResponseDTO {

    private Long id;

    private Long champId;

    private String nomChamp;

    private Long exploitantId;

    private String nomExploitant;

    private LocalDate dateIntervention;

    private TypeIntervention typeIntervention;

    private String natureAction;

    private String produitsUtilises;

    private Integer duree;

    private String observations;

    private StatutIntervention statut;

    private LocalDateTime dateCreation;

    private LocalDateTime dateModification;

    public InterventionResponseDTO() {
    }

    public InterventionResponseDTO(
            Long id,
            Long champId,
            String nomChamp,
            Long exploitantId,
            String nomExploitant,
            LocalDate dateIntervention,
            TypeIntervention typeIntervention,
            String natureAction,
            String produitsUtilises,
            Integer duree,
            String observations,
            StatutIntervention statut,
            LocalDateTime dateCreation,
            LocalDateTime dateModification) {

        this.id = id;
        this.champId = champId;
        this.nomChamp = nomChamp;
        this.exploitantId = exploitantId;
        this.nomExploitant = nomExploitant;
        this.dateIntervention = dateIntervention;
        this.typeIntervention = typeIntervention;
        this.natureAction = natureAction;
        this.produitsUtilises = produitsUtilises;
        this.duree = duree;
        this.observations = observations;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    public Long getId() {
        return id;
    }

    public Long getChampId() {
        return champId;
    }

    public String getNomChamp() {
        return nomChamp;
    }

    public Long getExploitantId() {
        return exploitantId;
    }

    public String getNomExploitant() {
        return nomExploitant;
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
}