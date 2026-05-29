package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Dao permettant l'accès et la gestion
 * des données de la bibliothèque dans la base de données
 */
public class Dao {

	// Méthodes pour gérer les livres
	/**
	 * Récupère la liste de tous les livres de la bibliothèque
	 * @return liste des livres
	 * @throws SQLException erreur lors de la récuperation des livres
	 */
	public static List<Livre> getAllLivres() throws SQLException {
		List<Livre> livres = new ArrayList<>();
		String query = "SELECT * FROM livres";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				livres.add(new Livre(
						rs.getInt("livre_id"), 
						rs.getString("titre"), 
						rs.getString("auteur"),
						rs.getString("isbn"), 
						rs.getBoolean("disponible"), 
						rs.getString("categorie"), 
						rs.getDate("date_ajout").toLocalDate()
				));
			}
		}
		return livres;
	}

	/**
	 * Récupère un livre de la bibliothèque à partir de son identifiant
	 * @param livreId identifiant du livre
	 * @return livre qui nous interesse
	 * @throws SQLException erreur lors de la récuperation du livre
	 */
	public static Livre getLivreById(int livreId) throws SQLException {
		String query = "SELECT * FROM livres WHERE livre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)){
				pstmt.setInt(1, livreId);
				ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Livre(rs.getInt("livre_id"), 
						rs.getString("titre"), 
						rs.getString("auteur"),
						rs.getString("isbn"), 
						rs.getBoolean("disponible"), 
						rs.getString("categorie"), 
						rs.getDate("date_ajout").toLocalDate()
				);
			}
		}
		return null;
	}

	/**
	 * Ajoute un livre dans la bibliothèque
	 * @param livre livre qu'on souhaite ajouter
	 * @throws SQLException erreur lors de l'ajout d'un livre
	 */
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

	/**
	 * Supprime un livre de la bibliothèque
	 * @param livreId identifiant du livre qu'on souhaite supprimer
	 * @throws SQLException erreur lors de la suppression d'un livre
	 */
	public static void deleteLivre(int livreId) throws SQLException {
		String query = "DELETE FROM livres WHERE livre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, livreId);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Modifie un livre de la bibliothèque
	 * @param livre livre qu'on souhaite modifier
	 * @throws SQLException erreur lors de la modification d'un livre
	 */
	public static void updateLivre(Livre livre) throws SQLException {
		String query = "UPDATE livres SET titre = ?, auteur = ?, isbn = ?, disponible = ?, categorie = ? WHERE livre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, livre.getTitre());
			pstmt.setString(2, livre.getAuteur());
			pstmt.setString(3, livre.getIsbn());
			pstmt.setBoolean(4, livre.isDisponible());
			pstmt.setString(5, livre.getCategorie());
			pstmt.setInt(6, livre.getLivreId());
			pstmt.executeUpdate();
		}
	}

	// Méthodes pour gérer les adhérents
	/**
	 * Récupère la liste de tous les adhérents de la bibliothèque
	 * @return liste des adhérents
	 * @throws SQLException erreur lors de la récuperation des adhérents
	 */
	public static List<Adherent> getAllAdherents() throws SQLException {
		List<Adherent> adherents = new ArrayList<>();
		String query = "SELECT * FROM adherents";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				adherents.add(new Adherent(
						rs.getInt("adherent_id"), 
						rs.getString("nom"), 
						rs.getString("prenom"),
						rs.getString("email"), 
						rs.getString("telephone"), 
						rs.getString("adresse"),
						rs.getDate("date_inscription").toLocalDate()
				));
			}
		}
		return adherents;
	}

	/**
	 * Récupère un adhérent de la bibliothèque à partir de son identifiant
	 * @param adherentId identifiant de l'adhérent
	 * @return adherent qui nous interesse
	 * @throws SQLException erreur lors de la récuperation d'un adhérent
	 */
	public static Adherent getAdherentById(int adherentId) throws SQLException {
		String query = "SELECT * FROM adherents WHERE adherent_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, adherentId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Adherent(rs.getInt("adherent_id"), 
						rs.getString("nom"), 
						rs.getString("prenom"),
						rs.getString("email"), 
						rs.getString("telephone"), 
						rs.getString("adresse"),
						rs.getDate("date_inscription").toLocalDate()
				);
			}
		}
		return null;
	}

	/**
	 * Ajoute un adherent dans la bibliothèque
	 * @param adherent adherent qu'on souhaite ajouter
	 * @throws SQLException erreur lors de l'ajout d'un adherent
	 */
	public static void addAdherent(Adherent adherent) throws SQLException {
		String query = "INSERT INTO adherents (nom, prenom, email, telephone, adresse, date_inscription) VALUES (?,?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, adherent.getNom());
			pstmt.setString(2, adherent.getPrenom());
			pstmt.setString(3, adherent.getEmail());
			pstmt.setString(4, adherent.getTelephone());
			pstmt.setString(5, adherent.getAdresse());
			pstmt.setDate(6, java.sql.Date.valueOf(adherent.getDateInscription()));
			pstmt.executeUpdate();

			// Récupération de l'ID généré
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				adherent.setAdherentId(rs.getInt(1));
			}
		}
	}

	/**
	 * Supprime un adhérent de la bibliothèque
	 * @param adherentId identifiant de l'adhérent qu'on souhaite supprimer
	 * @throws SQLException erreur lors de la suppression d'un adhérent
	 */
	public static void deleteAdherent(int adherentId) throws SQLException {
		String query = "DELETE FROM adherents WHERE adherent_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, adherentId);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Modifie un adhérent de la bibliothèque
	 * @param adherent adhérent qu'on souhaite modifier
	 * @throws SQLException erreur lors de la modification d'un adhérent
	 */
	public static void updateAdherent(Adherent adherent) throws SQLException {
		String query = "UPDATE adherents SET nom = ?, prenom = ?, email = ?, telephone = ?, adresse = ? WHERE adherent_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, adherent.getNom());
			pstmt.setString(2, adherent.getPrenom());
			pstmt.setString(3, adherent.getEmail());
			pstmt.setString(4, adherent.getTelephone());
			pstmt.setString(5, adherent.getAdresse());
			pstmt.setInt(6, adherent.getAdherentId());
			pstmt.executeUpdate();
		}
	}

	// Méthodes pour administrer les emprunts
	/**
	 * Récupère la liste de tous les emprunts de la bibliothèque
	 * @return liste des emprunts
	 * @throws SQLException erreur lors de la récupération des emprunts
	 */
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
				Livre livre = new Livre(
						rs.getInt("livre_id"), 
						rs.getString("titre"), 
						rs.getString("auteur"),
						rs.getString("isbn"), 
						rs.getBoolean("disponible"), 
						rs.getString("categorie"), 
						rs.getDate("date_ajout").toLocalDate()
				);
	          	            
				Adherent adherent = new Adherent(
						rs.getInt("adherent_id"), 
						rs.getString("nom"), 
						rs.getString("prenom"),
						rs.getString("email"), 
						rs.getString("telephone"), 
						rs.getString("adresse"),
						rs.getDate("date_inscription").toLocalDate()
				);
				
				Emprunt emprunt = new Emprunt(
					rs.getInt("emprunt_id"),
					livre,
					adherent,
					rs.getDate("date_emprunt").toLocalDate(),
					rs.getDate("date_retour_prevue").toLocalDate()
				);
				emprunts.add(emprunt);
			}
		}
		return emprunts;
	}

//	// Méthode pour voir un seul emprunt
//	public static Emprunt getEmpruntById(int empruntId) throws SQLException {
//		String query = "SELECT * FROM emprunts WHERE emprunt_id = ?";
//		try (Connection conn = DatabaseConnection.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(query)) {
//			pstmt.setInt(1, empruntId);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				Livre livre = Dao.getLivreById(rs.getInt("livre_id"));
//				Adherent adherent = Dao.getAdherentById(rs.getInt("adherent_id"));
//				return new Emprunt(
//						rs.getInt("emprunt_id"), 
//						livre, 
//						adherent, 
//						rs.getDate("date_emprunt").toLocalDate(), 
//						rs.getDate("date_retour_prevue").toLocalDate()
//				);
//			}
//		}
//		return null;
//	}

	// Méthode pour voir si un livre est emprunté
	/**
	 * récupère l'identifiant d'un emprunt à partir 
	 * du titre du livre si le livre est emprunté
	 * @param titre titre du livre dont on souhaite savoir s'il est emprunté
	 * @return identifiant de l'emprunt si le livre et emprunté, null sinon
	 * @throws SQLException erreur lors de la récupération 
	 */
	public static Integer getEmpruntIdByLivreTitre(String titre) throws SQLException {
		String query = """
			SELECT 
			e.emprunt_id 
			FROM emprunts e 
			JOIN livres l ON e.livre_id = l.livre_id 
			WHERE l.titre = ? 
			""";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, titre);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("emprunt_id");
			}
		}
		return null;
	}

	/**
	 * Emprunte un livre de la bibliothèque
	 * @param emprunt emprunt qu'on souhaite effectuer
	 * @throws SQLException erreur lors de l'emprunt d'un livre
	 */
	public static void addEmprunt(Emprunt emprunt) throws SQLException {
		String queryInsert = "INSERT INTO emprunts (livre_id, adherent_id, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";
		String queryUpdate = "UPDATE livres SET disponible = FALSE WHERE livre_id = ?"; 

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmtInsert = conn.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement pstmtUpdate = conn.prepareStatement(queryUpdate)) {
			
			pstmtInsert.setInt(1, emprunt.getLivre().getLivreId());
			pstmtInsert.setInt(2, emprunt.getAdherent().getAdherentId());
			pstmtInsert.setDate(3, java.sql.Date.valueOf(emprunt.getDateEmprunt()));
			pstmtInsert.setDate(4, java.sql.Date.valueOf(emprunt.getDateRetourPrevue()));
			pstmtInsert.executeUpdate();
			
			pstmtUpdate.setInt(1, emprunt.getLivre().getLivreId());
			pstmtUpdate.executeUpdate();

			// Récupération de l'ID généré
			ResultSet rs = pstmtInsert.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setEmpruntId(rs.getInt(1));
			}
		}
	}

	/**
	 * Retourne un emprunt d'un livre de la bibliothèque
	 * @param empruntId identifiant de l'emprunt qu'on souhaite retourner
	 * @throws SQLException erreur lors du retour d'un emprunt
	 */
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