package fr.gtm.cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.gtm.cinema.entities.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
	//!\ l'écriture de mauvaises methodes fait tout bugger
	Film getByTitreLike(String titre);

	Film getFilmById(long id);

	// le mappage des roles doit faire appelle à une query du type:
//	@Query("SELECT f FROM FIlm f JOIN FETCH f.roles WHERE f.id = ?1")
//	Film getFilm
}
