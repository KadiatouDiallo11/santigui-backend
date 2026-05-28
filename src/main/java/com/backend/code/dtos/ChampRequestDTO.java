package com.backend.code.dtos;

import java.math.BigDecimal;

import com.backend.code.entity.enums.TypeCulture;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de creation ou modification d'un champ agricole")
public class ChampRequestDTO {

    @Schema(description = "Nom du champ", example = "Parcelle Nord")
    private String nom;

    @Schema(description = "Superficie du champ en hectares", example = "12.5")
    private BigDecimal superficie;

    @Schema(description = "Type de culture. Valeurs possibles: ANANAS, ORANGE, MANIOC", example = "ANANAS", implementation = TypeCulture.class)
    private TypeCulture typeCulture;

    @Schema(description = "Coordonnees GPS du champ", example = "5.3480,-4.0270")
    private String coordonneesGps;

    @Schema(description = "Identifiant de l'exploitant rattache", example = "exp-123")
    private String exploitantId;

    @Schema(description = "Identifiant de la ville de rattachement", example = "ville-001")
    private String villeId;

    public ChampRequestDTO() {
    }

    public String getNom() {
        return nom;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public TypeCulture getTypeCulture() {
        return typeCulture;
    }

    public String getCoordonneesGps() {
        return coordonneesGps;
    }

    public String getExploitantId() {
        return exploitantId;
    }

    public String getVilleId() {
        return villeId;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public void setTypeCulture(TypeCulture typeCulture) {
        this.typeCulture = typeCulture;
    }

    public void setCoordonneesGps(String coordonneesGps) {
        this.coordonneesGps = coordonneesGps;
    }

    public void setExploitantId(String exploitantId) {
        this.exploitantId = exploitantId;
    }

    public void setVilleId(String villeId) {
        this.villeId = villeId;
    }
}