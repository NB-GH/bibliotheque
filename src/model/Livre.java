package model;

import java.time.LocalDate;

public class Livre {
	private int livreId;
	private String titre;
	private String auteur;
	private String isbn;
	private boolean disponible;
	private String categorie;
	private LocalDate dateAjout;
	
	//Constructeur complet
	public Livre(int livreId, String titre, String auteur, String isbn, boolean disponible, String categorie, LocalDate dateAjout) {
		this.livreId = livreId;
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
		this.disponible = disponible;
		this.categorie = categorie;
		this.dateAjout = dateAjout;
	}

	//Constructeur sans Id (pour les nouveaux livres)
	public Livre (String titre, String auteur, String isbn, boolean disponible, String categorie, LocalDate dateAjout) {
		this(0, titre, auteur, isbn, disponible, categorie, dateAjout);
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

	@Override
	public String toString() {
		return "Livre [ID : " + livreId + ", Titre : " + titre + ", Auteur : " + auteur + ", ISBN : " + isbn + ", Catégorie : " + categorie + ", Disponible : " + disponible + ", Date d'ajout: " + dateAjout + "]";
    }

}


