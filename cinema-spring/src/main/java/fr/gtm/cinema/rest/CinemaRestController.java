package fr.gtm.cinema.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.annotation.JSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gtm.cinema.dto.ActeurDto;
import fr.gtm.cinema.dto.FilmDto;
import fr.gtm.cinema.dto.RoleDto;
import fr.gtm.cinema.entities.Acteur;
import fr.gtm.cinema.entities.Film;
import fr.gtm.cinema.entities.Role;
import fr.gtm.cinema.repositories.ActeurRepository;
import fr.gtm.cinema.repositories.FilmRepository;
import fr.gtm.cinema.util.MailReceptor;

@RestController
//permet  à js de communiquer avec un autre serveur que celui d'origine
@CrossOrigin(origins = "http://localhost:4200")
public class CinemaRestController {
	@Autowired
	private ActeurRepository acteurRepository;
	@Autowired
	private FilmRepository filmRepository;
	private Logger LOGMAIL = Logger.getLogger("MAIL");
	private Logger LOGCINE = Logger.getLogger("CINEMA");

	// envoi des acteurs qui des films qui ont des acteurs... à l'infini!
//	@GetMapping(path = "/acteur/{id}")
//	public Acteur findActeurById(@PathVariable("id") long id) {
//		Optional<Acteur> optional  = acteurRepository.findById(id);
//		if (optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//		
//	}

	// http://localhost:7070/acteur/5
	@GetMapping(path = "/acteur/{id}")
	public ActeurDto findActeurById(@PathVariable("id") long id) {
		Optional<Acteur> optional = acteurRepository.findById(id);
		if (optional.isPresent()) {
			ActeurDto dto = new ActeurDto(optional.get());
			return dto;
		}
		return null;

	}

	// http://localhost:7070/acteur/new
	// on peut tester avec :{"civilite":"M","nom":"Hauer","prenom":"Rutger"}
	@PostMapping("/acteur/new")
	public String creationActeur(@RequestBody ActeurDto dto) {
		acteurRepository.save(dto.toActeur());
		return "ça marche";
	}

	/**
	 * 
	 * ajout de fonctinalité pour les mails la dépendance mvn
	 * spring-boot-starter-mail a été ajoutée au pom
	 */
	@Autowired
	private JavaMailSender mailsender;

	private void sendMail() {
		SimpleMailMessage mail = new SimpleMailMessage();
		// le domaine doit exister, y compris en local
		mail.setTo("gaston@bovoyages.net");
		// cette adresse email doit être valide
		mail.setFrom("Salade@tomates.oignon");
		mail.setSubject("kebab");
		mail.setText("sauce blanche");
		mailsender.send(mail);
	}

	// http://localhost:7070/mail/test
	// on passe en multithread pour ne pas faire attendre côté navigateur
	@GetMapping("/mail/test")
	public String testMail() {
		try {
			new Thread(() -> sendMail()).start();
			LOGMAIL.fine("le mail a été envoyé");
		} catch (Exception e) {
			LOGMAIL.severe("le mail n'a pas été envoyé" + e.getMessage());
		}
		return "svp chef";
	}

	@PostMapping("/mail/send")
	// on peut tester avec le json : {"mail":"ssa@tss.oss"} pour mr
	public String testrMail(@RequestBody MailReceptor mr) {
		try {
			new Thread(() -> sendMail()).start();
			LOGMAIL.fine("le mail a été envoyé");
		} catch (Exception e) {
			LOGMAIL.severe("le mail n'a pas été envoyé" + e.getMessage());
		}
		return "envoyé à " + mr.getMail();
	}

	@GetMapping("/film/all")
	public List<FilmDto> getAllFilms() {
		List<Film> films = filmRepository.findAll();
		List<FilmDto> dtos = new ArrayList<FilmDto>();
		for (Film f : films) {
			dtos.add(new FilmDto(f));
		}
		return dtos;
	}

	@GetMapping(path = "/film/{id}")
	public FilmDto findFilmById(@PathVariable("id") long id) {
		Optional<Film> optional = filmRepository.findById(id);
		if (optional.isPresent()) {
			FilmDto dto = new FilmDto(optional.get());
			return dto;
		}
		return null;
	}

//	{
//	    "titre": "2001, l'Odyssée de l'espace",
//	    "realisateur": "Stanley Kubrick"
//	}

//	@GetMapping(path = "/film/{id}")
//	public List<String> getAllRolesFromFilm(@PathVariable("id") long id){
//		// ne permet pas d'obtenir les roles
////		Optional<Film> film = filmRepository.findById(id);
//		// ajoutée au repo
//		Film film = filmRepository.getFilmById(id);
//		Map<Role, Acteur> roles = film.getRoles();
//		//conversion en liste pour pouvoir itérer
//		List<Role> list = new ArrayList<Role>(roles.keySet());
//		
//		List<String> listeRoles = new ArrayList<String>();
//		for(Role r : list) {
//			listeRoles.add(r.toString());
//		}
//		return listeRoles;
//
//	}

//	@GetMapping(path = "/film/{id}")
//	public String getAllRolesFromFilm(@PathVariable("id") long id){
//		Film film = filmRepository.getFilmById(id);
//		Map<Role, Acteur> roles = film.getRoles();
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		String json = "";
//        try {
//            json = objectMapper.writeValueAsString(roles);
//        } catch (JsonProcessingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return json;
//
//	}

	@GetMapping(path = "/film/roles/{id}")
	public List<String> getAllRolesFromFilm(@PathVariable("id") long id) {
		Film film = filmRepository.getFilmById(id);
		List<String> roles = FilmDto.roleDtoFilm(film);
		return roles;

	}

//	@PostMapping("/film/save")
//	public FilmDto saveFilm(@RequestBody Film f) {
//		filmRepository.save(f);
//		return new FilmDto(f);
//	}

	@PostMapping("/film/save")
//	{"titre": "2001, l'Odyssée de l'espace","realisateur": "Stanley Kubrick"}
	public String saveFilm(@RequestBody FilmDto dto) {
		try {
			filmRepository.save(dto.toFilm());
			LOGCINE.fine("film créé");
			return "ok";
		} catch (Exception e) {
			LOGCINE.severe("film pas créé");
			return "raté";
		}
	}

//	{"id":666, "titre" : "mon film", "realisateur":"le real",
//		"roles":[{"role":{"role":"principal"}, "acteur": {"nom":"le nom"}},
//		         {"role":{"role":"secondaire"}, "acteur": {"nom":"le nom"}}
//		         ]}

	@PostMapping("/film/save/actor/{id}")
//	{"civilite": "M","nom": "Smith","prenom": "Roger"}
//	{"role": "Extra-terrestre"}
//	{"id": "666","role": "Extra-terrestre","civilite": "M","nom": "Smith","prenom": "Roger"}
	
	public String saveActorFilm(@RequestBody RoleDto wrapper, @PathVariable("id") long id) {
		try {
//			acteurRepository.save(dto.toActeur());
//			LOGCINE.fine("acteur créé");
//			
//			Optional<Film> film = filmRepository.findById(id);

			Film f = filmRepository.getFilmById(1);

			Acteur a = wrapper.toActeur();
			
			Role r = wrapper.toRole();

			Map<Role, Acteur> roles = f.getRoles();
			roles.putIfAbsent(r, a);
			filmRepository.save(f);

			LOGCINE.fine("acteur ajouté");
			return "ok";
		} catch (Exception e) {
			LOGCINE.severe("acteur pas ajouté");
			return "raté";
		}
	}

	@PostMapping("/film/save/acteurs")
	public String saveFilm(@RequestBody Film film) {
		try {
			filmRepository.save(film);
			return "ok";
		} catch (Exception e) {
			return "raté";
		}

	}

}
