package com.backend.code.dtos;

import com.backend.code.entity.enums.NiveauAdmin;

public class UtilisateurRequestDTO {

    public String nom;
    public String email;
    public String password;

    // type: ADMIN ou EXPLOITANT
    public String type;

    // ADMIN
    public NiveauAdmin niveau;

    // EXPLOITANT
    public String numeroExploitation;
    public String telephone;
    public String adresse;
    
    
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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