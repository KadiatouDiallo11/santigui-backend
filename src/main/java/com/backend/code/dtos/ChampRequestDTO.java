package com.backend.code.dtos;

import java.math.BigDecimal;

public class ChampRequestDTO {

    private String nom;

    private BigDecimal superficie;

    private String typeCulture;

    private String coordonneesGps;

    private String exploitantId;

    private String villeId;

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

    public void setTypeCulture(String typeCulture) {
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