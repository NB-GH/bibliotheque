import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.BibliothequeFrame;

/**
 * Classe principale de l'application de gestion de bibliothèque
 * Cette classe lance l'interface graphique Swing de l'application MVC
 */
public class Main {

	/**
	 * Point d'entrée principal de l'application 
	 * Initialise et affiche la fenêtre principale
	 * @param args arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				BibliothequeFrame frame = new BibliothequeFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Erreur lors du démarrage : " + e.getMessage(),
						"Erreur", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();  
			}
		});

	}

}
