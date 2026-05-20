package com.backend.code.dtos;

import java.math.BigDecimal;

public class ChampRequestDTO {

    private String nom;

    private BigDecimal superficie;

    private String typeCulture;

    private Long exploitantId;

    private String coordonneesGps;

    public ChampRequestDTO() {
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

    public String getCoordonneesGps() {
        return coordonneesGps;
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

    public void setCoordonneesGps(String coordonneesGps) {
        this.coordonneesGps = coordonneesGps;
    }
}