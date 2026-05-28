package com.backend.code.dtos;

import java.math.BigDecimal;

import com.backend.code.entity.enums.TypeCulture;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representation d'un champ agricole")
public class ChampResponseDTO {

    @Schema(description = "Identifiant unique du champ", example = "champ-123")
    private String id;

    @Schema(description = "Nom du champ", example = "Parcelle Nord")
    private String nom;

    @Schema(description = "Superficie du champ en hectares", example = "12.5")
    private BigDecimal superficie;

    @Schema(description = "Type de culture. Valeurs possibles: ANANAS, ORANGE, MANIOC", example = "ANANAS", implementation = TypeCulture.class)
    private TypeCulture typeCulture;

    @Schema(description = "Coordonnees GPS du champ", example = "5.3480,-4.0270")
    private String coordonneesGps;

    @Schema(description = "Identifiant de l'exploitant", example = "exp-123")
    private String exploitantId;

    @Schema(description = "Nom de l'exploitant", example = "Kouassi")
    private String nomExploitant;

    @Schema(description = "Identifiant de la ville", example = "ville-001")
    private String villeId;

    @Schema(description = "Nom de la ville", example = "Abidjan")
    private String nomVille;

    public ChampResponseDTO() {
    }

    public ChampResponseDTO(String id,
                            String nom,
                            BigDecimal superficie,
                            TypeCulture typeCulture,
                            String coordonneesGps,
                            String exploitantId,
                            String nomExploitant,
                            String villeId,
                            String nomVille) {

        this.id = id;
        this.nom = nom;
        this.superficie = superficie;
        this.typeCulture = typeCulture;
        this.coordonneesGps = coordonneesGps;
        this.exploitantId = exploitantId;
        this.nomExploitant = nomExploitant;
        this.villeId = villeId;
        this.nomVille = nomVille;
    }

    public String getId() {
        return id;
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

    public String getNomExploitant() {
        return nomExploitant;
    }

    public String getVilleId() {
        return villeId;
    }

    public String getNomVille() {
        return nomVille;
    }
}