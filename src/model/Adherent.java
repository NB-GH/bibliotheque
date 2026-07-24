package model;

import java.time.LocalDate;

/**
 * Représente un adhérent de la bibliothèque
 */
public class Adherent {
	private int adherentId;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String adresse;
	private LocalDate dateInscription;
	
	/**
	 * Constructeur complet pour créer un objet Adhérent
	 * @param adherentId identifiant de l'adhérent
	 * @param nom nom de l'adhérent
	 * @param prenom prénom de l'adhérent
	 * @param email adresse mail de l'adhérent
	 * @param telephone téléphone de l'adhérent
	 * @param adresse adresse de l'adhérent
	 * @param dateInscription date d'inscription de l'adhérent
	 */
	public Adherent(int adherentId, String nom, String prenom, String email, String telephone, String adresse, LocalDate dateInscription) {
		this.adherentId = adherentId;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.dateInscription = dateInscription;	
	}

	/**
	 * Constructeur sans identifiant pour ajouter un nouvel adhérent
	 * @param nom nom de l'adhérent
	 * @param prenom prénom de l'adhérent
	 * @param email adresse mail de l'adhérent
	 * @param telephone téléphone de l'adhérent
	 * @param adresse adresse de l'adhérent
	 * @param dateInscription date d'inscription de l'adhérent
	 */
	public Adherent(String nom, String prenom, String email, String telephone, String adresse, LocalDate dateInscription) {
		this(0, nom, prenom, email, telephone, adresse, dateInscription);
	}
	
	/**
	 * Constructeur sans date d'inscription pour modifier un adhérent
	 * @param adherentId identifiant de l'adhérent
	 * @param nom nom de l'adhérent
	 * @param prenom prénom de l'adhérent
	 * @param email adresse mail de l'adhérent
	 * @param telephone téléphone de l'adhérent
	 * @param adresse adresse de l'adhérent
	 */ 
	public Adherent(int adherentId, String nom, String prenom, String email, String telephone, String adresse) {
	    this(adherentId, nom, prenom, email, telephone, adresse, null);
	} 
	
	//getters
	public int getAdherentId() { return adherentId; }
	public String getNom() { return nom; }
	public String getPrenom() {	return prenom; }
	public String getEmail() { return email; }
	public String getTelephone() { return telephone; }
	public String getAdresse() { return adresse; }
	public LocalDate getDateInscription() { return dateInscription; }
	
	//setters 
	public void setAdherentId(int adherentId) {	this.adherentId = adherentId; }
	public void setNom(String nom) { this.nom = nom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }
	public void setEmail(String email) { this.email = email; }
	public void setTelephone(String telephone) { this.telephone = telephone; }
	public void setAdresse(String adresse) { this.adresse = adresse; }
	public void setDate_inscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }
	
	/**
	 * Retourne une représentation textuelle de l'adhérent
	 */
	@Override
	public String toString() {
		return "Adhérent [ID : "+ adherentId + ", Nom : " + nom + ", Prénom: " + prenom + ", Email: " + email + ", Téléphone: " + telephone + ", Adresse: " + adresse + ", Date d'inscription: " + dateInscription + "]";
	}

}