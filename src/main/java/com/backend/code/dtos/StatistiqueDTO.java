package com.backend.code.dtos;

import java.time.LocalDate;

import com.backend.code.entity.enums.TypeIntervention;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Donnees statistiques agregees sur les interventions")
public class StatistiqueDTO {

    @Schema(description = "Identifiant unique de la statistique", example = "stat-001")
    private String id;

    @Schema(description = "Periode de calcul concernee", example = "2026-05")
    private String periode;

    @Schema(description = "Nombre total d'interventions pour la periode", example = "14")
    private Integer nombreInterventions;

    @Schema(description = "Type d'intervention analyse. Valeurs possibles: LABOUR, SEMIS, TRAITEMENT, IRRIGATION, RECOLTE, AUTRE", example = "RECOLTE", implementation = TypeIntervention.class)
    private TypeIntervention typeIntervention;

    @Schema(description = "Date de calcul de la statistique", example = "2026-05-28")
    private LocalDate dateCalcul;

    public StatistiqueDTO() {
    }

    public String getId() {
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

    public void setId(String id) {
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