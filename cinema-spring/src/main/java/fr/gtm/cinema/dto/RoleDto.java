package fr.gtm.cinema.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;
import fr.gtm.cinema.entities.Role;

public class RoleDto {

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
