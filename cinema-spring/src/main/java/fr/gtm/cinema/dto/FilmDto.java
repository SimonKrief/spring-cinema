package fr.gtm.cinema.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;
import fr.gtm.cinema.entities.Role;

public class FilmDto {
	private String titre;
	private String realisateur;
	
	public FilmDto() {}
	
	public FilmDto(Film film) {
		super();
		this.titre = film.getTitre();
		this.realisateur = film.getRealisateur();
	}
	
	
	
	public FilmDto(String titre, String realisateur) {
		super();
		this.titre = titre;
		this.realisateur = realisateur;
	}

	public Film toFilm() {
		Film film = new Film();
		film.setTitre(this.getTitre());
		film.setRealisateur(this.getRealisateur());
		return film;
	}
	
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	
	public static List<String> roleDtoFilm(Film film) {
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
