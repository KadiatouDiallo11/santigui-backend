package com.backend.code.dtos;

import com.backend.code.entity.enums.NiveauAdmin;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de creation ou modification d'un administrateur")
public class AdministrateurRequestDTO {

    @Schema(description = "Nom de famille", example = "Kouassi")
    public String nom;

    @Schema(description = "Prenom", example = "Amani")
    public String prenom;

    @Schema(description = "Adresse email", example = "admin@gmail.com")
    public String email;

    @Schema(description = "Mot de passe du compte", example = "MotDePasse123")
    public String password;

    @Schema(description = "Niveau administratif. Valeurs possibles: SUPER_ADMIN, SECRETAIRE", example = "SUPER_ADMIN", implementation = NiveauAdmin.class)
    public NiveauAdmin niveau;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NiveauAdmin getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauAdmin niveau) {
        this.niveau = niveau;
    }
}