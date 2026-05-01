package view;

import javax.swing.*;
import java.awt.*;

public class BibliothequeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L; 
	private JTabbedPane tabbedPane; 
	private LivreView livrePanel;
	private AdherentView adherentPanel;
	private EmpruntView empruntPanel;
	
	public BibliothequeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("Gestion de la Bibliothèque");
		setLocationRelativeTo(null);
		
		tabbedPane = new JTabbedPane();
		livrePanel = new LivreView();
		adherentPanel = new AdherentView();
		empruntPanel = new EmpruntView();
		
		tabbedPane.addTab("Livres", livrePanel);
        tabbedPane.addTab("Adhérents", adherentPanel);
        tabbedPane.addTab("Emprunts", empruntPanel);

        add(tabbedPane, BorderLayout.CENTER);
	}
}
