package com.backend.code.entity.tables;

import jakarta.persistence.*;

@Entity
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "exploitant_id")
    private Exploitant exploitant;

    public Champ() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Exploitant getExploitant() {
        return exploitant;
    }

    public void setExploitant(Exploitant exploitant) {
        this.exploitant = exploitant;
    }
}