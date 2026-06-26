package model;

import java.time.LocalDate;

public class Avis {
	private int avisId;
	private Livre livre;
	private Adherent adherent;
	private String texte;
	private LocalDate dateAvis;
	
	public Avis(int avisId, Livre livre, Adherent adherent, String texte, LocalDate dateAvis) {
		this.avisId = avisId;
		this.livre = livre;
		this.adherent = adherent;
		this.texte = texte;
		this.dateAvis = dateAvis;
	}

	public Avis(Livre livre, Adherent adherent, String texte, LocalDate dateAvis) {
		this(0, livre, adherent, texte, dateAvis);
	}
	
	public int getAvisId() {
		return avisId;
	}
	public Livre getLivre() {
		return livre;
	}
	public Adherent getAdherent() {
		return adherent;
	}
	public String getTexte() {
		return texte;
	}
	public LocalDate getDateAvis() {
		return dateAvis;
	}
	
	public void setAvisId(int avisId) {
		this.avisId = avisId;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public void setDateAvis(LocalDate dateAvis) {
		this.dateAvis = dateAvis;
	}
	
}

