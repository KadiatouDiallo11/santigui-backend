package com.backend.code.entity.tables;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    // 🔥 Surface en hectares
    private BigDecimal superficie;

    // 🔥 Culture principale
    private String typeCulture;

    // 🔥 Coordonnées GPS (optionnel)
    private String coordonneesGps;

    @ManyToOne
    @JoinColumn(name = "exploitant_id")
    private Exploitant exploitant;

    public Champ() {
    }

    // ================= GETTERS =================

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

    public Exploitant getExploitant() {
        return exploitant;
    }

    // ================= SETTERS =================

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

    public void setCoordonneesGps(String coordonneesGps) {
        this.coordonneesGps = coordonneesGps;
    }

    public void setExploitant(Exploitant exploitant) {
        this.exploitant = exploitant;
    }
}