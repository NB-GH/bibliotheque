package model;

import java.time.LocalDate;

/**
 * Représente un livre de la bibliothèque
 */
public class Livre {
	private int livreId;
	private String titre;
	private String auteur;
	private String isbn;
	private boolean disponible;
	private String categorie;
	private LocalDate dateAjout;
	
	/**
	 * Constructeur complet pour créer un objet Livre
	 * @param livreId identifiant du livre
	 * @param titre titre du livre
	 * @param auteur auteur du livre
	 * @param isbn numéro ISBN du livre
	 * @param disponible disponibilité du livre
	 * @param categorie catégorie du livre
	 * @param dateAjout date d'ajout du livre
	 */
	public Livre(int livreId, String titre, String auteur, String isbn, boolean disponible, String categorie, LocalDate dateAjout) {
		this.livreId = livreId;
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
		this.disponible = disponible;
		this.categorie = categorie;
		this.dateAjout = dateAjout;
	}

	/**
	 * Constructeur sans identifiant pour ajouter un livre
	 * @param titre titre du livre
	 * @param auteur auteur du livre
	 * @param isbn numéro ISBN du livre
	 * @param disponible disponibilité du livre
	 * @param categorie catégorie du livre
	 * @param dateAjout date d'ajout du livre
	 */
	public Livre (String titre, String auteur, String isbn, boolean disponible, String categorie, LocalDate dateAjout) {
		this(0, titre, auteur, isbn, disponible, categorie, dateAjout);
	}
	
	/**
	 * Constructeur sans date d'ajout pour modifier un livre
	 * @param livreId titre du livre
	 * @param auteur auteur du livre
	 * @param isbn numéro ISBN du livre
	 * @param disponible disponibilité du livre
	 * @param categorie catégorie du livre
	 */
	public Livre(int livreId, String titre, String auteur, String isbn, boolean disponible, String categorie) {
	    this(livreId, titre, auteur, isbn, disponible, categorie, null);
	}
	
	//getters
	public int getLivreId() { return livreId; }
	public String getTitre() { return titre; }
	public String getAuteur() { return auteur; }
	public String getIsbn() { return isbn; }
	public boolean isDisponible() { return disponible; }
	public String getCategorie() { return categorie; }
	public LocalDate getDateAjout() { return dateAjout; }
	//setters
	public void setLivreId(int livreId) { this.livreId = livreId; }
	public void setTitre(String titre) { this.titre = titre; }
	public void setAuteur(String auteur) { this.auteur = auteur; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	public void setDisponible(boolean disponible) { this.disponible = disponible; }
	public void setCategorie(String categorie) { this.categorie = categorie; }
	public void setDateAjout(LocalDate dateAjout) {	this.dateAjout = dateAjout;	}

	/**
	 * Retourne une représentation textuelle du livre
	 */
	@Override
	public String toString() {
		return "Livre [ID : " + livreId + ", Titre : " + titre + ", Auteur : " + auteur + ", ISBN : " + isbn + ", Catégorie : " + categorie + ", Disponible : " + disponible + ", Date d'ajout: " + dateAjout + "]";
    }

}


