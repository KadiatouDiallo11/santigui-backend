package com.backend.code.dtos;

import java.time.LocalDate;

import com.backend.code.entity.enums.TypeIntervention;

public class StatistiqueDTO {

    private Long id;

    private String periode;

    private Integer nombreInterventions;

    private TypeIntervention typeIntervention;

    private LocalDate dateCalcul;

    public StatistiqueDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getPeriode() {
        return periode;
    }

    public Integer getNombreInterventions() {
        return nombreInterventions;
    }

    public TypeIntervention getTypeIntervention() {
        return typeIntervention;
    }

    public LocalDate getDateCalcul() {
        return dateCalcul;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public void setNombreInterventions(Integer nombreInterventions) {
        this.nombreInterventions = nombreInterventions;
    }

    public void setTypeIntervention(TypeIntervention typeIntervention) {
        this.typeIntervention = typeIntervention;
    }

    public void setDateCalcul(LocalDate dateCalcul) {
        this.dateCalcul = dateCalcul;
    }
}