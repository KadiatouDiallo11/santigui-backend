package com.backend.code.dtos;

import java.math.BigDecimal;

public class ChampResponseDTO {

    private String id;

    private String nom;

    private BigDecimal superficie;

    private String typeCulture;

    private String coordonneesGps;

    private String exploitantId;

    private String nomExploitant;

    private String villeId;

    private String nomVille;

    public ChampResponseDTO() {
    }

    public ChampResponseDTO(String id,
                            String nom,
                            BigDecimal superficie,
                            String typeCulture,
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

    public String getTypeCulture() {
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