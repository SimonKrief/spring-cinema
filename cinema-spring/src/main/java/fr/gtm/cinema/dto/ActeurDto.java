package fr.gtm.cinema.dto;

import fr.gtm.cinema.entities.Acteur;

public class ActeurDto {
	private long id;
	private String civilite;
	private String nom;
	private String prenom;
	
	public ActeurDto() {}
	
	
	public ActeurDto(Acteur acteur) {
		super();
		this.id = acteur.getId();
		this.civilite = acteur.getCivilite();
		this.nom = acteur.getNom();
		this.prenom = acteur.getPrenom();
	}
	
	public Acteur toActeur() {
		Acteur acteur= new Acteur(civilite, nom, prenom);
		acteur.setId(id);
		return acteur;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
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
	

}
