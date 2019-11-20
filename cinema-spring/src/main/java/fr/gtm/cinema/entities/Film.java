package fr.gtm.cinema.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Entity
@Table(name="films")
@Access(AccessType.FIELD)
@NamedQuery(name="Film.all",query = "SELECT f FROM Film f")
public class Film {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_film")
	private long id;
	private String titre;
	private String realisateur;
	@Column(name="date_sortie")
	private LocalDate dateSortie;
	@Min(1)
	private int duree;	// durée en minutes;
	@DecimalMin("1.0")
	private double prixHT;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="film_acteur",
				joinColumns = @JoinColumn(name="fk_film"),
				inverseJoinColumns = @JoinColumn(name="fk_acteur"))
	private Map<Role, Acteur> roles = new HashMap<Role, Acteur>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDate getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public double getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}

	public Map<Role, Acteur> getRoles() {
		return roles;
	}

	public void setRoles(Map<Role, Acteur> roles) {
		this.roles = roles;
	}
	
	public void add(Role role,Acteur acteur) {
		roles.put(role, acteur);
	}
	
}
