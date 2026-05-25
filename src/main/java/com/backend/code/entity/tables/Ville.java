package com.backend.code.entity.tables;

import jakarta.persistence.*;

@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // 🔥 Nom de la ville
    private String nomVille;

    // 🔥 Code postal
    private String codePostal;

    public Ville() {
    }

    // ================= GETTERS =================

    public String getId() {
        return id;
    }

    public String getNomVille() {
        return nomVille;
    }

    public String getCodePostal() {
        return codePostal;
    }

    // ================= SETTERS =================

    public void setId(String id) {
        this.id = id;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}