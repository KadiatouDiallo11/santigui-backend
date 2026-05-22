package com.backend.code.dtos;

import java.math.BigDecimal;

public class ChampResponseDTO {

    private Long id;

    private String nom;

    private BigDecimal superficie;

    private String typeCulture;

    private String coordonneesGps;

    private Long exploitantId;

    private String nomExploitant;

    private Long villeId;

    private String nomVille;

    public ChampResponseDTO() {
    }

    public ChampResponseDTO(Long id,
                            String nom,
                            BigDecimal superficie,
                            String typeCulture,
                            String coordonneesGps,
                            Long exploitantId,
                            String nomExploitant,
                            Long villeId,
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

    public Long getId() {
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

    public Long getExploitantId() {
        return exploitantId;
    }

    public String getNomExploitant() {
        return nomExploitant;
    }

    public Long getVilleId() {
        return villeId;
    }

    public String getNomVille() {
        return nomVille;
    }
}