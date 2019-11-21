package fr.gtm.cinema.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;
import fr.gtm.cinema.entities.Role;

public class RoleDto {
	
	private long id;
	private String role;
	private String civilite;
	private String nom;
	private String prenom;
	
	public Acteur toActeur() {
		Acteur a= new Acteur(civilite, nom, prenom);
		a.setId(id);
		return a;
	}
	public Role toRole() {
		Role r= new Role(role);
		return r;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> RoleDtoFilm(Film film) {
		Map<Role, Acteur> roles = film.getRoles();
		// conversion en liste pour pouvoir itérer
		List<Role> list = new ArrayList<Role>(roles.keySet());

		List<String> listeRoles = new ArrayList<String>();
		for (Role r : list) {
			listeRoles.add(r.toString());
		}
		return listeRoles;

	}

}
