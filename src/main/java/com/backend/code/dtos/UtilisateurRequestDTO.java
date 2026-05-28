package com.backend.code.dtos;

import com.backend.code.entity.enums.NiveauAdmin;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload de creation ou modification d'un utilisateur")
public class UtilisateurRequestDTO {

	@Schema(description = "Nom de famille", example = "Kouassi")
    public String nom;

	@Schema(description = "Prenom", example = "Amani")
    public String prenom;

	@Schema(description = "Adresse email", example = "explo@gmail.com")
    public String email;

	@Schema(description = "Mot de passe du compte", example = "MotDePasse123")
    public String password;

    // type: ADMIN ou EXPLOITANT
	@Schema(description = "Type d'utilisateur. Valeurs possibles: ADMIN, EXPLOITANT", example = "EXPLOITANT", allowableValues = {"ADMIN", "EXPLOITANT"})
    public String type;

    // ADMIN
	@Schema(description = "Niveau administratif si le type vaut ADMIN. Valeurs possibles: SUPER_ADMIN, SECRETAIRE", example = "SUPER_ADMIN", implementation = NiveauAdmin.class)
    public NiveauAdmin niveau;

    // EXPLOITANT
	@Schema(description = "Numero d'exploitation si le type vaut EXPLOITANT", example = "EXP-2026-001")
    public String numeroExploitation;

	@Schema(description = "Telephone de l'exploitant", example = "+2250700000000")
    public String telephone;

	@Schema(description = "Adresse de l'exploitant", example = "Yamoussoukro, quartier Habitat")
    public String adresse;
    
    
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public NiveauAdmin getNiveau() {
		return niveau;
	}
	public void setNiveau(NiveauAdmin niveau) {
		this.niveau = niveau;
	}
	public String getNumeroExploitation() {
		return numeroExploitation;
	}
	public void setNumeroExploitation(String numeroExploitation) {
		this.numeroExploitation = numeroExploitation;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    
}