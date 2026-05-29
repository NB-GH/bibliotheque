package model;

import java.time.LocalDate;

/**
 * Représente l'emprunt d'un livre par un adhérent de la bibliothèque
 */
public class Emprunt {
	private int empruntId;
	private Livre livre;
	private Adherent adherent;
	private LocalDate dateEmprunt;
	private LocalDate dateRetourPrevue;
	//private LocalDate dateRetourReelle;

	/**
	 * Constructeur complet pour créer un objet Emprunt
	 * @param empruntId identifiant de l'emprunt
	 * @param livre livre emprunté
	 * @param adherent adhérent qui emprunte
	 * @param dateEmprunt date de l'emprunt
	 * @param dateRetourPrevue date de retour prévue de l'emprunt
	 */
	public Emprunt(int empruntId, Livre livre, Adherent adherent, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
		this.empruntId = empruntId;
		this.livre = livre;
		this.adherent = adherent;
		this.dateEmprunt = dateEmprunt;
		this.dateRetourPrevue = dateRetourPrevue;
		//this.dateRetourReelle = dateRetourReelle;
	}
	
	/**
	 * Constructeur sans identifiant pour ajouter un nouvel emprunt 
	 * @param livre livre emprunté
	 * @param adherent adhérent qui emprunte
	 * @param dateEmprunt date de l'emprunt
	 * @param dateRetourPrevue date de retour prévue de l'emprunt
	 */
	public Emprunt(Livre livre, Adherent adherent, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
		this(0, livre, adherent, dateEmprunt, dateRetourPrevue);
	}

	//getters
	public int getEmpruntId() { return empruntId; }
	public Livre getLivre() { return livre; }
	public Adherent getAdherent() { return adherent; }
	public LocalDate getDateEmprunt() { return dateEmprunt; }
	public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
	//public LocalDate getDateRetourReelle() { return dateRetourReelle; }
	
	//setters
	public void setEmpruntId(int empruntId) { this.empruntId = empruntId; }
	public void setLivre(Livre livre) { this.livre = livre; }
	public void setAdherent(Adherent adherent) { this.adherent = adherent; }
	public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }
	public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
	//public void setDateRetourReelle(LocalDate dateRetourReelle) { this.dateRetourReelle = dateRetourReelle; }
	
	/**
	 * Retourne une représentation textuelle de l'emprunt
	 */
	@Override
    public String toString() {
        return "Emprunt [ID : " + empruntId + ", Livre : " + livre.getTitre() + ", Adhérent : " + adherent.getNom() + " " + adherent.getPrenom() + ", Date d'emprunt : " + dateEmprunt + ", Date de retour prévue : " + dateRetourPrevue + "]";
    }
}