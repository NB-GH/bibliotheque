import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.BibliothequeFrame;

public class Main {

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
