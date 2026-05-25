package com.backend.code.dtos;

import java.time.LocalDate;

import com.backend.code.entity.enums.StatutIntervention;
import com.backend.code.entity.enums.TypeIntervention;

public class InterventionRequestDTO {

    private String champId;

    private String exploitantId;

    private LocalDate dateIntervention;

    private TypeIntervention typeIntervention;

    private String natureAction;

    private String produitsUtilises;

    private Integer duree;

    private String observations;

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