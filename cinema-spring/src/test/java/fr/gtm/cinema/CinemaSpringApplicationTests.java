package fr.gtm.cinema;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.gtm.cinema.dto.FilmDto;
import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;
import fr.gtm.cinema.entities.Role;
import fr.gtm.cinema.repositories.ActeurRepository;
import fr.gtm.cinema.repositories.FilmRepository;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
// pour l'utilisation d'une base de test  à mettre en ressource:
//@DataJpaTest
//@Sql(cinema)
class CinemaSpringApplicationTests {
	
	/*******testsfilms************/
	@Autowired FilmRepository filmRepository;

	@Test
	void contextLoadsFilms() {
		assertNotNull(filmRepository);
	}
	
	@Test
	void getAllFilms() {
		List<Film> films = filmRepository.findAll();
		assertTrue(films.size()>0);
	}
	
	@Test
	void getByTitreLike() {
		Film film = filmRepository.getByTitreLike("Blade Runner");
		assertNotNull(film);
	}
	
	/*******testsacteurs************/
	@Autowired ActeurRepository acteurRepository;
	
	@Test
	void contextLoadsActeurs() {
		assertNotNull(acteurRepository);
	}
	
	@Test
	void getAllActeurs() {
		List<Acteur> acteurs =acteurRepository.findAll();
		assertTrue(acteurs.size()>0);
	}
	
	
	@Test
	void getActeurById() {
//		Acteur acteur = acteurRepository.getActeurById(4);
		Optional<Acteur> acteur = acteurRepository.findById((long) 4);
		//!\ l'optionnal est un conteneur non vide, il faut tester le get
		assertNotNull(acteur.get());
	}
	
	/******testjointures************/
	//	@Test
//	List<Acteur> acteurs = acteurRepository.
	
	/******test creation************/
	
	@Test
	void creationActeur() {
		int n = acteurRepository.findAll().size();
		
		Acteur acteur = new Acteur( "M.", "Cage", "Nicolas" );
		acteurRepository.save(acteur);
		
		assertTrue(n+1== acteurRepository.findAll().size());
	}
	
	@Test
	void deletionActeur() {
		int n = acteurRepository.findAll().size();
		
		List<Acteur> acteurs = acteurRepository.findAllByOrderByIdDesc();
		//permet de faire la même chose mais pas besoin de déclarer de methode.
//		List<Acteur> acteurs = acteurRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		Acteur acteur = acteurs.get(0);
		acteurRepository.delete(acteur);
		
		assertTrue(n-1==acteurRepository.findAll().size());
	}
	
	@Test
	void creationFilm() {
		int n = filmRepository.findAll().size();
		//Remarque : certaines contraintes commentées dans les entités pour permettre cela
		FilmDto dto = new FilmDto("2001, l'Odyssée de l'espace","Stanley Kubrick");
		filmRepository.save(dto.toFilm());
		assertTrue(n+1== filmRepository.findAll().size());
	}
	
	@Test
	void ajoutActeurFilm() {
//		Optional<Film> film = filmRepository.findById((long) 1);
		Film f =filmRepository.getFilmById(1);
		
		Acteur a = new Acteur("?", "Smith", "Roger");
		Role r = new Role("Extra-terrestre");
		
		Map<Role, Acteur> roles = f.getRoles();
		int n = roles.size();
		roles.putIfAbsent(r, a);
		filmRepository.save(f);
		
		assertTrue(n+1==roles.size());

	}
	
	

}
