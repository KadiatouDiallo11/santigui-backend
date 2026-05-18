package com.backend.code.dtos;

public class UtilisateurResponseDTO {
	
	public Long id;
    public String nom;
    public String email;
    public String type;

    public String niveau;
    public String numeroExploitation;
    public String telephone;
    public String adresse;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
