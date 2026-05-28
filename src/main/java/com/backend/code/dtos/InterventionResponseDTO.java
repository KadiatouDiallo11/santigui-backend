package com.backend.code.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.backend.code.entity.enums.StatutIntervention;
import com.backend.code.entity.enums.TypeIntervention;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representation detaillee d'une intervention")
public class InterventionResponseDTO {

    @Schema(description = "Identifiant unique de l'intervention", example = "interv-123")
    private String id;

    @Schema(description = "Identifiant du champ concerne", example = "champ-123")
    private String champId;

    @Schema(description = "Nom du champ concerne", example = "Parcelle Nord")
    private String nomChamp;

    @Schema(description = "Identifiant de l'exploitant", example = "exp-123")
    private String exploitantId;

    @Schema(description = "Nom de l'exploitant", example = "Kouassi")
    private String nomExploitant;

    @Schema(description = "Date de l'intervention", example = "2026-05-28")
    private LocalDate dateIntervention;

    @Schema(description = "Type d'intervention. Valeurs possibles: LABOUR, SEMIS, TRAITEMENT, IRRIGATION, RECOLTE, AUTRE", example = "TRAITEMENT", implementation = TypeIntervention.class)
    private TypeIntervention typeIntervention;

    @Schema(description = "Nature precise de l'action", example = "Traitement fongicide preventif")
    private String natureAction;

    @Schema(description = "Produits utilises", example = "Bouillie bordelaise")
    private String produitsUtilises;

    @Schema(description = "Duree en heures", example = "3")
    private Integer duree;

    @Schema(description = "Observations complementaires", example = "Intervention realisee avant la pluie")
    private String observations;

    @Schema(description = "Statut de l'intervention. Valeurs possibles: BROUILLON, SOUMISE, COMMENTEE", example = "SOUMISE", implementation = StatutIntervention.class)
    private StatutIntervention statut;

    @Schema(description = "Date de creation de la fiche", example = "2026-05-28T10:15:30")
    private LocalDateTime dateCreation;

    @Schema(description = "Date de derniere modification", example = "2026-05-28T11:00:00")
    private LocalDateTime dateModification;

    public InterventionResponseDTO() {
    }

    public InterventionResponseDTO(
            String id,
            String champId,
            String nomChamp,
            String exploitantId,
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

    public String getId() {
        return id;
    }

    public String getChampId() {
        return champId;
    }

    public String getNomChamp() {
        return nomChamp;
    }

    public String getExploitantId() {
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