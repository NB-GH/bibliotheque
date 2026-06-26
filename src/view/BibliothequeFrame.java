package view;

import javax.swing.*;
import java.awt.*;
import resources.Style;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * Fenêtre principale de l'application de gestion de bibliothèque
 * Contient les différentes vues organisées sous forme d'onglets :
 * livres, adhérents et emprunts.
 */
public class BibliothequeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L; 
	private JTabbedPane tabbedPane; 
	private LivreView livrePanel;
	private AdherentView adherentPanel;
	private EmpruntView empruntPanel;
	private AvisView avisPanel;
	/**
	 * Constructeur de la fenêtre principale
	 * Initialise l'interface graphique, les onglets 
	 * et les liens entre les différentes vues
	 */
	public BibliothequeFrame() {
		UIManager.put("OptionPane.background", Style.PANEL);
		UIManager.put("Panel.background", Style.PANEL);
		UIManager.put("OptionPane.messageForeground", Style.TEXT);
		UIManager.put("Button.background", Style.PRIMARY);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.font", Style.BUTTON_FONT);
		UIManager.put("OptionPane.messageFont", Style.FONT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("Gestion de la Bibliothèque");
		setLocationRelativeTo(null);
		
		getContentPane().setBackground(Style.BACKGROUND);
		tabbedPane = new JTabbedPane();
		Style.styleTabbedPane(tabbedPane);		
		
		livrePanel = new LivreView();
		adherentPanel = new AdherentView();
		empruntPanel = new EmpruntView();
		avisPanel = new AvisView();
		
		livrePanel.setEmpruntView(empruntPanel);
		adherentPanel.setEmpruntView(empruntPanel);
		empruntPanel.setLivreView(livrePanel);
		
		tabbedPane.addTab("Livres", livrePanel);
        tabbedPane.addTab("Adhérents", adherentPanel);
        tabbedPane.addTab("Emprunts", empruntPanel);
        tabbedPane.addTab("Avis", avisPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        if (!java.beans.Beans.isDesignTime()) {
            livrePanel.chargerVue();
            adherentPanel.chargerVue();
            empruntPanel.chargerVue();
            avisPanel.chargerVue();
        }
    }
}
