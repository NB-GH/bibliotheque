package model;

import java.time.LocalDate;

public class Emprunt {

	private int empruntId;
	private Livre livre;
	private Adherent adherent;
	private LocalDate dateEmprunt;
	private LocalDate dateRetourPrevue;
	private LocalDate dateRetourReelle;

	//Constructeur
	public Emprunt(int empruntId, Livre livre, Adherent adherent, LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourReelle) {
		this.empruntId = empruntId;
		this.livre = livre;
		this.adherent = adherent;
		this.dateEmprunt = dateEmprunt;
		this.dateRetourPrevue = dateRetourPrevue;
		this.dateRetourReelle = dateRetourReelle;
	}
	
	//Constructeur sans id (pour les nouveaux emprunts) 
	public Emprunt(Livre livre, Adherent adherent, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
		this(0, livre, adherent, dateEmprunt, dateRetourPrevue, null);
	}

	//getters
	public int getEmpruntId() { return empruntId; }
	public Livre getLivre() { return livre; }
	public Adherent getAdherent() { return adherent; }
	public LocalDate getDateEmprunt() { return dateEmprunt; }
	public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
	public LocalDate getDateRetourReelle() { return dateRetourReelle; }
	
	//setters
	public void setEmpruntId(int empruntId) { this.empruntId = empruntId; }
	public void setLivre(Livre livre) { this.livre = livre; }
	public void setAdherent(Adherent adherent) { this.adherent = adherent; }
	public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }
	public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
	public void setDateRetourReelle(LocalDate dateRetourReelle) { this.dateRetourReelle = dateRetourReelle; }
	
	@Override
    public String toString() {
        return "Emprunt [ID : " + empruntId + ", Livre : " + livre.getTitre() + ", Adhérent : " + adherent.getNom() + " " + adherent.getPrenom() + ", Date d'emprunt : " + dateEmprunt + ", Date de retour prévue : " + dateRetourPrevue + ", Date de retour réelle : " + dateRetourReelle + "]";
    }
}