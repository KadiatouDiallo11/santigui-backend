package com.backend.code.dtos;

import java.time.LocalDate;

import com.backend.code.entity.enums.StatutIntervention;
import com.backend.code.entity.enums.TypeIntervention;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de creation ou modification d'une intervention")
public class InterventionRequestDTO {

    @Schema(description = "Identifiant du champ concerne", example = "champ-123")
    private String champId;

    @Schema(description = "Identifiant de l'exploitant responsable", example = "exp-123")
    private String exploitantId;

    @Schema(description = "Date de l'intervention", example = "2026-05-28")
    private LocalDate dateIntervention;

    @Schema(description = "Type d'intervention. Valeurs possibles: LABOUR, SEMIS, TRAITEMENT, IRRIGATION, RECOLTE, AUTRE", example = "TRAITEMENT", implementation = TypeIntervention.class)
    private TypeIntervention typeIntervention;

    @Schema(description = "Nature precise de l'action realisee", example = "Traitement fongicide preventif")
    private String natureAction;

    @Schema(description = "Produits utilises pendant l'intervention", example = "Bouillie bordelaise")
    private String produitsUtilises;

    @Schema(description = "Duree de l'intervention en heures", example = "3")
    private Integer duree;

    @Schema(description = "Observations complementaires", example = "Intervention realisee avant la pluie")
    private String observations;

    @Schema(description = "Statut de l'intervention. Valeurs possibles: BROUILLON, SOUMISE, COMMENTEE", example = "SOUMISE", implementation = StatutIntervention.class)
    private StatutIntervention statut;

    public InterventionRequestDTO() {
    }

    public String getChampId() {
        return champId;
    }

    public String getExploitantId() {
        return exploitantId;
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

    public void setChampId(String champId) {
        this.champId = champId;
    }

    public void setExploitantId(String exploitantId) {
        this.exploitantId = exploitantId;
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
}