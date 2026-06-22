package com.backend.code.dtos;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Indicateurs du tableau de bord administrateur")
public class DashboardAdministrateurDTO {

    @Schema(description = "Nombre d'exploitants crees par l'administrateur", example = "12")
    private Long nombreExploitantsAjoutes;

    @Schema(description = "Repartition des interventions par ville", example = "{\"Bamako\":5,\"Sikasso\":3}")
    private Map<String, Long> repartitionInterventionsParVille;

    @Schema(description = "Repartition des interventions par type", example = "{\"LABOUR\":2,\"TRAITEMENT\":5}")
    private Map<String, Long> repartitionInterventionsParType;

    @Schema(description = "Nombre de fiches soumises mais non encore commentees", example = "4")
    private Long nombreFichesEnAttente;

    public DashboardAdministrateurDTO() {
    }

    public Long getNombreExploitantsAjoutes() {
        return nombreExploitantsAjoutes;
    }

    public void setNombreExploitantsAjoutes(Long nombreExploitantsAjoutes) {
        this.nombreExploitantsAjoutes = nombreExploitantsAjoutes;
    }

    public Map<String, Long> getRepartitionInterventionsParVille() {
        return repartitionInterventionsParVille;
    }

    public void setRepartitionInterventionsParVille(Map<String, Long> repartitionInterventionsParVille) {
        this.repartitionInterventionsParVille = repartitionInterventionsParVille;
    }

    public Map<String, Long> getRepartitionInterventionsParType() {
        return repartitionInterventionsParType;
    }

    public void setRepartitionInterventionsParType(Map<String, Long> repartitionInterventionsParType) {
        this.repartitionInterventionsParType = repartitionInterventionsParType;
    }

    public Long getNombreFichesEnAttente() {
        return nombreFichesEnAttente;
    }

    public void setNombreFichesEnAttente(Long nombreFichesEnAttente) {
        this.nombreFichesEnAttente = nombreFichesEnAttente;
    }
}