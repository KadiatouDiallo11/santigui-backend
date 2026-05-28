package com.backend.code.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representation d'une ville")
public class VilleDTO {

    @Schema(description = "Identifiant unique de la ville", example = "ville-001")
    private String id;

    @Schema(description = "Nom de la ville", example = "Abidjan")
    private String nomVille;

    @Schema(description = "Code postal de la ville", example = "00225")
    private String codePostal;

    public VilleDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}