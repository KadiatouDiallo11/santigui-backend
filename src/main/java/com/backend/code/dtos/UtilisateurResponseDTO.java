package com.backend.code.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representation d'un utilisateur")
public class UtilisateurResponseDTO {
	
	@Schema(description = "Identifiant unique de l'utilisateur", example = "user-123")
	public String id;

	@Schema(description = "Nom de famille", example = "Kouassi")
    public String nom;

	@Schema(description = "Prenom", example = "Amani")
    public String prenom;

	@Schema(description = "Adresse email", example = "explo@gmail.com")
    public String email;

	@Schema(description = "Type d'utilisateur. Valeurs possibles: ADMIN, EXPLOITANT", example = "EXPLOITANT", allowableValues = {"ADMIN", "EXPLOITANT"})
    public String type;

	@Schema(description = "Indique si le compte est actif", example = "true")
    public boolean actif;

	@Schema(description = "Niveau administratif si le compte est un ADMIN. Valeurs possibles: SUPER_ADMIN, SECRETAIRE", example = "SUPER_ADMIN", allowableValues = {"SUPER_ADMIN", "SECRETAIRE"})
    public String niveau;

	@Schema(description = "Numero d'exploitation si le compte est un EXPLOITANT", example = "EXP-2026-001")
    public String numeroExploitation;

	@Schema(description = "Telephone de l'exploitant", example = "+2250700000000")
    public String telephone;

	@Schema(description = "Adresse de l'exploitant", example = "Yamoussoukro, quartier Habitat")
    public String adresse;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
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
    
    
}
