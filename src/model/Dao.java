package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dao {

	// Méthodes pour gérer les livres
	// Méthode pour voir l'ensemble des livres
	public static List<Livre> getAllLivres() throws SQLException {
		List<Livre> livres = new ArrayList<>();
		String query = "SELECT * FROM livres";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				LocalDate dateAjout = rs.getDate("date_ajout") != null ? rs.getDate("date_ajout").toLocalDate() : null;
				livres.add(new Livre(rs.getInt("livre_id"), rs.getString("titre"), rs.getString("auteur"),
						rs.getString("isbn"), rs.getBoolean("disponible"), rs.getString("categorie"), dateAjout));
			}
		}
		return livres;
	}

	// Méthode pour voir un seul livre
	public static Livre getLivreById(int livreId) throws SQLException {
		String query = "SELECT * FROM livres WHERE livre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)){
				pstmt.setInt(1, livreId);
				ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				LocalDate dateAjout = rs.getDate("date_ajout") != null ? rs.getDate("date_ajout").toLocalDate() : null;
				return new Livre(rs.getInt("livre_id"), rs.getString("titre"), rs.getString("auteur"),
						rs.getString("isbn"), rs.getBoolean("disponible"), rs.getString("categorie"), dateAjout);
			}
		}
		return null;
	}

	// Méthode pour ajouter un livre
	public static void addLivre(Livre livre) throws SQLException {
		String query = "INSERT INTO livres (titre, auteur, isbn, disponible, categorie, date_ajout) VALUES (?,?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, livre.getTitre());
			pstmt.setString(2, livre.getAuteur());
			pstmt.setString(3, livre.getIsbn());
			pstmt.setBoolean(4, livre.isDisponible());
			pstmt.setString(5, livre.getCategorie());
			pstmt.setDate(6, java.sql.Date.valueOf(livre.getDateAjout()));
			pstmt.executeUpdate();

			// Récupération de l'ID généré
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				livre.setLivreId(rs.getInt(1));
			}
		}
	}

	// Méthode pour supprimer un livre
	public static void deleteLivre(int livreId) throws SQLException {
		String query = "DELETE FROM livres WHERE livre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, livreId);
			pstmt.executeUpdate();
		}
	}

	// Méthode pour modifier un livre
	public static void updateLivre(Livre livre) throws SQLException {
		String query = "UPDATE livres SET titre = ?, auteur = ?, isbn = ?, disponible = ?, categorie = ?, date_ajout = ? WHERE livre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, livre.getTitre());
			pstmt.setString(2, livre.getAuteur());
			pstmt.setString(3, livre.getIsbn());
			pstmt.setBoolean(4, livre.isDisponible());
			pstmt.setString(5, livre.getCategorie());
			pstmt.setDate(6, java.sql.Date.valueOf(livre.getDateAjout()));
			pstmt.setInt(7, livre.getLivreId());
			pstmt.executeUpdate();
		}
	}

	// Méthodes pour gérer les adhérents
	// Méthode pour voir l'ensemble des adhérents
	public static List<Adherent> getAllAdherents() throws SQLException {
		List<Adherent> adherents = new ArrayList<>();
		String query = "SELECT * FROM adherents";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				adherents.add(new Adherent(rs.getInt("adherent_id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("adresse"),
						rs.getDate("date_inscription").toLocalDate()));
			}
		}
		return adherents;
	}

	// Méthode pour voir un seul adhérent
	public static Adherent getAdherentById(int adherentId) throws SQLException {
		String query = "SELECT * FROM adherents WHERE adherent_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, adherentId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Adherent(rs.getInt("adherent_id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("adresse"),
						rs.getDate("date_inscription").toLocalDate());
			}
		}
		return null;
	}

	// Méthode pour ajouter un adhérent
	public static void addAdherent(Adherent adherent) throws SQLException {
		String query = "INSERT INTO adherents (nom, prenom, email, telephone, adresse, date_inscription) VALUES (?,?,?,?,?, now())";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, adherent.getNom());
			pstmt.setString(2, adherent.getPrenom());
			pstmt.setString(3, adherent.getEmail());
			pstmt.setString(4, adherent.getTelephone());
			pstmt.setString(5, adherent.getAdresse());
			pstmt.executeUpdate();

			// Récupération de l'ID généré
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				adherent.setAdherentId(rs.getInt(1));
			}
		}
	}

	// Méthode pour supprimer un adhérent
	public static void deleteAdherent(int adherentId) throws SQLException {
		String query = "DELETE FROM adherents WHERE adherent_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, adherentId);
			pstmt.executeUpdate();
		}
	}

	// Méthode pour modifier un adhérent
	public static void updateAdherent(Adherent adherent) throws SQLException {
		String query = "UPDATE adherents SET nom = ?, prenom = ?, email = ?, telephone = ?, adresse = ?, date_inscription = ? WHERE adherent_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, adherent.getNom());
			pstmt.setString(2, adherent.getPrenom());
			pstmt.setString(3, adherent.getEmail());
			pstmt.setString(4, adherent.getTelephone());
			pstmt.setString(5, adherent.getAdresse());
			pstmt.setDate(6, java.sql.Date.valueOf(adherent.getDateInscription()));
			pstmt.setInt(7, adherent.getAdherentId());
			pstmt.executeUpdate();
		}
	}

	// Méthodes pour administrer les emprunts
	// Méthode pour voir l'ensemble des emprunts
	public static List<Emprunt> getAllEmprunts() throws SQLException {
		List<Emprunt> emprunts = new ArrayList<>();
		String query ="""
			SELECT
				e.emprunt_id,
	            e.date_emprunt,
	            e.date_retour_prevue,
	            l.livre_id, l.titre, l.auteur, l.isbn, l.disponible, l.categorie, l.date_ajout,
	            a.adherent_id, a.nom, a.prenom, a.email, a.telephone, a.adresse, a.date_inscription
	        FROM emprunts e
	        JOIN livres l ON e.livre_id = l.livre_id
	        JOIN adherents a ON e.adherent_id = a.adherent_id
	        """;
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				LocalDate dateAjout = rs.getDate("date_ajout") != null ? rs.getDate("date_ajout").toLocalDate() : null;
				Livre livre = new Livre(rs.getInt("livre_id"), rs.getString("titre"), rs.getString("auteur"),
					rs.getString("isbn"), rs.getBoolean("disponible"), rs.getString("categorie"), dateAjout);
	          	            
				Adherent adherent = new Adherent(rs.getInt("adherent_id"), rs.getString("nom"), rs.getString("prenom"),
					rs.getString("email"), rs.getString("telephone"), rs.getString("adresse"),
					rs.getDate("date_inscription").toLocalDate());
				
				Emprunt emprunt = new Emprunt(
					rs.getInt("emprunt_id"),
					livre,
					adherent,
					rs.getDate("date_emprunt").toLocalDate(),
					rs.getDate("date_retour_prevue").toLocalDate(),
					null
				);
				emprunts.add(emprunt);
			}
		}
		return emprunts;
	}

	// Méthode pour voir un seul emprunt
	public static Emprunt getEmpruntById(int empruntId) throws SQLException {
		String query = "SELECT * FROM emprunts WHERE emprunt_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, empruntId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Livre livre = Dao.getLivreById(rs.getInt("livre_id"));
				Adherent adherent = Dao.getAdherentById(rs.getInt("adherent_id"));
				LocalDate dateEmprunt = rs.getDate("date_emprunt").toLocalDate();
				LocalDate dateRetourPrevue = rs.getDate("date_retour_prevue").toLocalDate();
				return new Emprunt(rs.getInt("emprunt_id"), livre, adherent, dateEmprunt, dateRetourPrevue, null);
			}
		}
		return null;
	}

	// Méthode pour ajouter un emprunt
	public static void addEmprunt(Emprunt emprunt) throws SQLException {
		String query = "INSERT INTO emprunts (livre_id, adherent_id, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, emprunt.getLivre().getLivreId());
			pstmt.setInt(2, emprunt.getAdherent().getAdherentId());
			pstmt.setDate(3, java.sql.Date.valueOf(emprunt.getDateEmprunt()));
			pstmt.setDate(4, java.sql.Date.valueOf(emprunt.getDateRetourPrevue()));
			pstmt.executeUpdate();

			// Récupération de l'ID généré
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setEmpruntId(rs.getInt(1));
			}
		}
	}

	// Méthode pour retourner un livre et supprimer l'emprunt
	public static void returnEmprunt(int empruntId) throws SQLException {
		String queryUpdate = "UPDATE livres l JOIN emprunts e ON l.livre_id = e.livre_id SET l.disponible = TRUE WHERE e.emprunt_id = ?";
		String queryDelete = "DELETE FROM emprunts WHERE emprunt_id = ?";
		
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmtUpdate = conn.prepareStatement(queryUpdate); 
	            PreparedStatement pstmtDelete = conn.prepareStatement(queryDelete)){
			
					pstmtUpdate.setInt(1, empruntId);
		            pstmtUpdate.executeUpdate(); 
		            
        			pstmtDelete.setInt(1, empruntId);
                    pstmtDelete.executeUpdate();            
		}
	}
}