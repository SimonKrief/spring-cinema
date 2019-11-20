package fr.gtm.cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;

public interface ActeurRepository  extends JpaRepository<Acteur, Long>{
	//!\ l'écriture de mauvaises methodes fait tout bugger
	//cette methode existe déjà sous le nom de findById
	Acteur getActeurById(long id);

	List<Acteur> findAllByOrderByIdDesc();

}
