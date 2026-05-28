package com.backend.code.entity.tables;

import jakarta.persistence.*;

import java.math.BigDecimal;

import com.backend.code.entity.enums.TypeCulture;

@Entity
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // 🔥 Nom du champ
    private String nom;

    // 🔥 Surface en hectares
    private BigDecimal superficie;

    // 🔥 Culture principale
    @Enumerated(EnumType.STRING)
    private TypeCulture typeCulture;

    // 🔥 Coordonnées GPS
    private String coordonneesGps;

    // 🔥 Exploitant responsable
    @ManyToOne
    @JoinColumn(name = "exploitant_id")
    private Exploitant exploitant;

    // 🔥 Ville de rattachement
    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    public Champ() {
    }

    // ================= GETTERS =================

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

    public Exploitant getExploitant() {
        return exploitant;
    }

    public Ville getVille() {
        return ville;
    }

    // ================= SETTERS =================

    public void setId(String id) {
        this.id = id;
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

    public void setExploitant(Exploitant exploitant) {
        this.exploitant = exploitant;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}