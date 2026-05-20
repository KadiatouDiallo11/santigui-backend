package com.backend.code.dtos;

import java.math.BigDecimal;

public class ChampResponseDTO {

    private Long id;

    private String nom;

    private BigDecimal superficie;

    private String typeCulture;

    private Long exploitantId;

    private String nomExploitant;

    private String coordonneesGps;

    public ChampResponseDTO() {
    }

    public ChampResponseDTO(Long id,
                            String nom,
                            BigDecimal superficie,
                            String typeCulture,
                            Long exploitantId,
                            String nomExploitant,
                            String coordonneesGps) {

        this.id = id;
        this.nom = nom;
        this.superficie = superficie;
        this.typeCulture = typeCulture;
        this.exploitantId = exploitantId;
        this.nomExploitant = nomExploitant;
        this.coordonneesGps = coordonneesGps;
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

    public Long getExploitantId() {
        return exploitantId;
    }

    public String getNomExploitant() {
        return nomExploitant;
    }

    public String getCoordonneesGps() {
        return coordonneesGps;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public void setTypeCulture(String typeCulture) {
        this.typeCulture = typeCulture;
    }

    public void setExploitantId(Long exploitantId) {
        this.exploitantId = exploitantId;
    }

    public void setNomExploitant(String nomExploitant) {
        this.nomExploitant = nomExploitant;
    }

    public void setCoordonneesGps(String coordonneesGps) {
        this.coordonneesGps = coordonneesGps;
    }
}