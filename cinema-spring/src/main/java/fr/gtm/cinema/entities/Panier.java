//package fr.gtm.cinema.entities;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.SessionScoped;
//
//import fr.gtm.cinema.dao.FilmDTO;
//
//@SessionScoped
//public class Panier implements Serializable{
//	private List<FilmDTO> filmsdto = new ArrayList<FilmDTO>();
//	private int qte;
//	private double prixTotalTTC;
//	public static final Logger tchikita = Logger.getLogger("Cinema");
//	
//	public Panier() {
//		
//	}
//	
//	public Panier(List<FilmDTO> filmsdto) {
//		this.filmsdto = filmsdto;
//	}
//	
//	public int getQte() {
//		return qte;
//	}
//
//	public void setQte(int qte) {
//		this.qte = qte;
//	}
//
//	public List<FilmDTO> getFilmsdto() {
//		return filmsdto;
//	}
//
//	public void setFilmsdto(List<FilmDTO> filmsdto) {
//		this.filmsdto = filmsdto;
//	}
//	
//	public void addFilm(FilmDTO filmdto) {
//		filmsdto.add(filmdto);
//	}
//	
//	public void removeFilm(FilmDTO filmdto) {
//		filmsdto.remove(filmdto);
//	}
//	
//	public int getSize() {
//		return filmsdto.size();
//	}
//	
//	public double PrixTotalTTC(List<FilmDTO> films) {
//		prixTotalTTC = 0 ;
//		for(FilmDTO f : films) {
//			prixTotalTTC = prixTotalTTC + f.getPrixHT() + (f.getPrixHT()/10);
//		}
//		return prixTotalTTC;
//	}
//	
//	public double getPrixTotalTTC() {
//		return prixTotalTTC;
//	}
//
//}
